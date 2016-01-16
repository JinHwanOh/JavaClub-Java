/**
    Created on : Jun 12, 2015, 1:44:19 PM
    @autor     : Jin Hwan Oh
*/
package JavaClub.db;

import JavaClub.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDb {

    private JdbcHelper helper = new JdbcHelper();

    private void connect() {
        // Database information
        String url = "jdbc:mysql://localhost/dev";
        String user = "root";
        String pass = "PROG32758";

        // Establish connection
        boolean connected = helper.connect(url, user, pass);

        // Check for connectivity
        if (!connected) {
            System.out.println("Connection not established");
            return;
        }
    }

    public int addUser(User user) {
        // connect first
        connect();
        int result = -1;

        // get attributes from user
        String id = user.getId();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();

        // Add to ArrayList
        ArrayList<Object> userArray = new ArrayList<>();
        userArray.add(id);
        userArray.add(password);
        userArray.add(firstName);
        userArray.add(lastName);
        userArray.add(email);

        // create a sql statement
        String sql = "INSERT INTO USER(id, password, firstName, lastName, email)"
                + "VALUES(?, ?, ?, ?, ?)";

        // call update
        int update = helper.update(sql, userArray);

        return update;
    }

    /**
     * Returns User with given ID
     * @param id
     * @return User, null if user does not exist
     */
    public User getUser(String id) {
        connect();

        // Create ArrayList
        ArrayList<Object> userIdArray = new ArrayList<>();
        userIdArray.add(id);

        // Create sql statement
        String sql = "SELECT * FROM USER WHERE ID=?";

        ResultSet resultSet = helper.query(sql, userIdArray);
        
        // Initialize variables
        String userId;
        String password;
        String firstName;
        String lastName;
        String email;
        User user = null;
        
        try {
            // Curser to next resultSet
            resultSet.next();
            // Get attributes of found user
            userId = resultSet.getString("ID");
            password = resultSet.getString("PASSWORD");
            firstName = resultSet.getString("FIRSTNAME");
            lastName = resultSet.getString("LASTNAME");
            email = resultSet.getString("EMAIL");
            
            // Create new User with found attributes
            user = new User(userId, password, firstName, lastName, email);
        } catch (SQLException ex) {
            System.out.println(helper.getErrorMessage());
        }
        return user;
    }
    
    // Returns if the user exists with given id and password
    public boolean isCorrectPassword(String id, String password){
        connect();
        // Add ArrayList with value of id and password
        ArrayList<Object> user = new ArrayList<>();
        user.add(id);
        user.add(password);
        
        // Create sql statement
        String sql = "SELECT count(*) FROM USER WHERE id=? AND password=?";
        ResultSet resultSet = helper.query(sql, user);
        
        try{
            // Check if the user account exists
            resultSet.next();
            if(resultSet.getInt(1) == 1){
                return true;
            }
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return false;
    }
}
