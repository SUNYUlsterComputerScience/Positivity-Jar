/**
*@author Emmanuel Cruz
*/

package ricepudding.auth;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Below is the connection to the database
 * it uses a .env file for the username and the password
 * This .env file will not be uploaded to Git but it will be
 * uploaded to the AWS server.
 */
@DataSourceDefinition(name = "java:global/jdbc/positivity_jar", className = "org.mariadb.jdbc.Driver", url = "jdbc:mariadb://localhost:3306/positivity_jar", user = "${env.DATABASE_USER}", password = "${env.DATABASE_PASSWORD}")
@Singleton
@Startup
public class DatabaseSetup {

    @Resource(lookup = "java:global/jdbc/positivity_jar")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash; //hashes the password

    @PostConstruct
    public void init() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);

        /**
         * creates a table if it does not exist
         * enters the name, email, and password (hashed) to the database
         * also enters a separate table the group the person belongs to
         */
        executeUpdate(dataSource,
                "CREATE TABLE IF NOT EXISTS user_accounts(name VARCHAR(64), email VARCHAR(100) UNIQUE KEY, password VARCHAR(255))");
        executeUpdate(dataSource,
                "CREATE TABLE IF NOT EXISTS user_groups(email VARCHAR(100), group_name VARCHAR(64))");

    }

    /**
     *Method that updates the database with the new user
     */
    private void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
