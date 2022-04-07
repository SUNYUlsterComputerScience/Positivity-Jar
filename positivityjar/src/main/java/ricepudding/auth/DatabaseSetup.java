package ricepudding.auth;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

@DataSourceDefinition(name = "java:global/jdbc/positivity_jar", className = "org.mariadb.jdbc.Driver", url = "jdbc:mariadb://localhost:3306/positivity_jar", user = "USER", password = "PASSWORD")
@Singleton
@Startup
public class DatabaseSetup {

    @Resource(lookup = "java:global/jdbc/positivity_jar")
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {

        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3072");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHash.initialize(parameters);

        executeUpdate(dataSource, "DROP TABLE IF EXISTS user_accounts");
        executeUpdate(dataSource, "DROP TABLE IF EXISTS user_groups");

        executeUpdate(dataSource,
                "CREATE TABLE IF NOT EXISTS user_accounts(name VARCHAR(64), email VARCHAR(100) UNIQUE KEY, password VARCHAR(255))");
        executeUpdate(dataSource,
                "CREATE TABLE IF NOT EXISTS user_groups(name VARCHAR(64), group_name VARCHAR(64))");

        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('user', 'user@user.com', '" + passwordHash.generate("password".toCharArray()) + "')");
        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('reza', 'reza@reza.net', '" + passwordHash.generate("secret1".toCharArray()) + "')");
        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('alex', 'alex@alex.org', '" + passwordHash.generate("secret2".toCharArray()) + "')");
        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('arjan', 'arjan@arjan.me', '" + passwordHash.generate("secret2".toCharArray()) + "')");
        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('werner', 'werner@werner.co.uk', '" + passwordHash.generate("secret2".toCharArray()) + "')");

        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('user', 'admin')");

        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('reza', 'user')");

        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('alex', 'user')");

        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('arjan', 'admin')");

        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('werner', 'user')");

    }

    @PreDestroy
    public void destroy() {
        try {
            executeUpdate(dataSource, "DROP TABLE IF EXISTS user_accounts");
            executeUpdate(dataSource, "DROP TABLE IF EXISTS user_groups");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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