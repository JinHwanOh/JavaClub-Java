/**
    Created on : Jun 12, 2015, 1:44:19 PM
    @autor     : Jin Hwan Oh
*/
package JavaClub.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JdbcHelper {

    // To store the connection
    private Connection connection;

    // To store the last used PreparedStatement
    private PreparedStatement activeStatement;

    // To store the last sql statement
    private String activeSql = "";

    // To store reference of last used ResultSet
    private ResultSet resultSet;

    private String errorMessage = "";

    /**
     *
     */
    public boolean connect(String url, String user, String pass) {
        errorMessage = "";
        try {
            //TomCat requires Class.forName() to lad driver properly
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the given url
            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException ex) {
            errorMessage = ex.getSQLState() + ": " + ex.getMessage();
            System.out.println(errorMessage);
            return false;
        } catch (ClassNotFoundException ex) {
            errorMessage = ex.getMessage();
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    public void disconnect() {
        activeSql = "";
        try {
            resultSet.close();
        } catch (Exception ignore) {
        }
        try {
            activeStatement.close();
        } catch (Exception ignore) {
        }
        try {
            connection.close();
        } catch (Exception ignore) {
        }
    }

    public ResultSet query(String sql, ArrayList<Object> params) {
        try {
            // Clear error message
            errorMessage = "";

            if (!sql.equals(activeSql)) {
                activeStatement = connection.prepareStatement(sql);
                activeSql = sql;
            }

            // set all parameter values of prepared statement
            if (params != null) {
                setParameters(params);
            }

            // execute the prepared statement
            resultSet = activeStatement.executeQuery();
        } catch (SQLException ex) {
            errorMessage = ex.getSQLState() + ": " + ex.getMessage();
            System.out.println(errorMessage);
            return null;
        }
        return resultSet;
    }

    private void setParameters(ArrayList<Object> params) throws SQLException {
        for (int i = 0; i < params.size(); ++i) {
            Object param = params.get(i);
            if (param instanceof Integer) {
                activeStatement.setInt(i + 1, (int) param);
            }
            else if (param instanceof Double) {
                activeStatement.setDouble(i + 1, (double) param);
            }
            else if (param instanceof String) {
                activeStatement.setString(i + 1, (String) param);
            }

        }
    }
    
    public String getErrorMessage(){
        return errorMessage;
    }

    public int update(String sql, ArrayList<Object> params) {
        int result = -1;
        try {
            errorMessage = "";

            if (!sql.equals(activeSql)) {
                activeStatement = connection.prepareStatement(sql);
                activeSql = sql;
            }

            if (params != null) {
                setParameters(params);
            }

            // execute the preparedStatement
            result = activeStatement.executeUpdate();
        } catch (SQLException ex) {
            errorMessage = ex.getSQLState() + ": " + ex.getMessage();
            System.out.println(errorMessage);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
            System.out.println(errorMessage);
        }
        return result;
    }
}
