

package JavaClub;

/**
    Created on : Jun 12, 2015, 1:44:19 PM
    Author     : Jin Hwan Oh
*/

/**
 * 
 * User class defines attributes of an user.
 * User has attributes of id, password, first name, last name, and email
 */
public class User {
    
    // Initialize variables
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructor
     * @param id
     * @param password
     * @param firstName
     * @param lastName
     * @param email 
     */
    public User(String id, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters
    
    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    // Setters
    /**
     * Change id
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Change password
     * @param  password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Change firstName
     * @param  firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Change lastName
     * @param  lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Change email
     * @param  email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return information of this user
     */
    @Override
    public String toString(){
        String format = "Id: %s\nPassword: %s\nName: %s %s, email:%s";
        return String.format(format, id, password, firstName, lastName, email);
    }
}
