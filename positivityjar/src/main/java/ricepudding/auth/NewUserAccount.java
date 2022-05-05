/**
*@author Emmanuel Cruz
*/

package ricepudding.auth;
import java.sql.SQLException;
import java.util.Scanner;

public class NewUserAccount {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    /**
     * Constructor to for gathering the information required
     * @param firstName
     * @param lastName
     * @param email
     * @param username
     * @param password
     */
    public NewUserAccount(String firstName, String lastName, String email, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    //First Name
    public String getFirstName(String firstName) {
        return firstName;
    }
    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }
    //Last Name
    public String getLastName(String lastName) {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //Email Address
    public String getEmail(String email) {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //Username
    public String getUsername(String username) {
        return username;
    }
    public void Username(String username) {
        this.username = username;
    }
    //Password
    public String getPassword(String password) {
        return password;
    }
    public void Password(String password) {
        this.password = password;
    }
    public static void main(String[] args) throws SQLException {
        /*
        The code below, with the prompts for the user to enter
        information will not be used on the AWS Server. It is for
        testing purposes only. The information will be retrieved
        from the Account Creation page.
         */
        Scanner in = new Scanner(System.in);
        System.out.printf("%s","First Name: ");
        String firstName = in.next();

        System.out.printf("%s","Last Name: ");
        String lastName = in.next();

        System.out.printf("%s","Email: ");
        String email = in.next();

        System.out.printf("%s","Username: ");
        String username = in.next();

        System.out.printf("%s","Password: ");
        String password = in.next();

        /**
         * Creating the object and setting the values below it
         */
        NewUserAccount add = new NewUserAccount(firstName, lastName, email, username, password);

        add.setFirstName(firstName);
        add.setFirstName(lastName);
        add.setFirstName(email);
        add.setFirstName(username);
        add.setFirstName(password);

        /**
         * Executes methods from the UserAccountDB class
         * openDatabaseConnection establishes a connection with the DB
         * addNewUser gathers the information and writes it to the DB
         * closeDatabaseConnection will close the DB connection whether
         * it is successful in writing to the DB or not.
         */
        try{
            UserAccountDB.openDatabaseConnection();
            UserAccountDB.addNewUser(add.getFirstName(firstName), add.getLastName(lastName), add.getEmail(email),
                    add.getUsername(username), add.getPassword(password));
        }finally {
            UserAccountDB.closeDatabaseConnection();
        }
    }
}
