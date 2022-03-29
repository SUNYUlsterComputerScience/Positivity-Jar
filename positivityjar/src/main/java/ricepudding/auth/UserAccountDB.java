package ricepudding.auth;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * The DB has been set with two unique keys.
 * Those are email and username. It means if a person
 * attempts to use an email address or username that
 * is already in the DB it will be rejected and a SQLException error will be thrown.
 */

public class UserAccountDB {

    private static Connection connection;

    /**
     * Establishing the connection to the DB
     * @throws SQLException
     */
    public static void openDatabaseConnection() throws SQLException {
        System.out.println("Connecting to the database...");
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/positivity_jar", "ENTER USERNAME HERE", "ENTER PASSWORD HERE");
        System.out.println("Connection valid: " + connection.isValid(10));
    }

    /**
     * Closing the connection to the DB
     * @throws SQLException
     */
    public static void closeDatabaseConnection() throws SQLException {
        System.out.println("Closing database connection...");
        connection.close();
        System.out.println("Connection valid: " + connection.isValid(10));
    }

    /**
     * Creating a user on the DB
     * @param firstName
     * @param lastName
     * @param email
     * @param username
     * @param password
     * @throws SQLException
     */
    public static void addNewUser(String firstName, String lastName, String email, String username, String password) throws SQLException{
        System.out.println("Creating data...");
        PreparedStatement addNewUser = connection.prepareStatement(
                "INSERT INTO user_accounts (first_name, last_name, email, username, password) VALUES (?,?,?,?,?)");
            addNewUser.setString(1, firstName);
            addNewUser.setString(2, lastName);
            addNewUser.setString(3, email);
            addNewUser.setString(4, username);
            addNewUser.setString(5, password);
            int rowsInserted = addNewUser.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);
    }
}
