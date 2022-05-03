package ricepudding.rest;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("loginapi")
public class LoginResource {

    @Resource(lookup = "java:global/jdbc/positivity_jar") //sets the DB data source
    private DataSource dataSource;

    @Inject
    private Pbkdf2PasswordHash passwordHash; //hashes the password

    @POST
    @Path("account/create") //creates the user in the DB
    public Response createAccount(@QueryParam("name") String name, @QueryParam("email") String email,
                                  @QueryParam("password") String password) {
        executeUpdate(dataSource,
                "INSERT INTO user_accounts VALUES('" + name + "', '" + email + "', '" + passwordHash.generate(
                        password.toCharArray()) + "')");
        executeUpdate(dataSource, "INSERT INTO user_groups VALUES('" + email + "', 'user')");
        return Response.ok().build();
    }
    //updates the DB with the new user
    protected void executeUpdate(DataSource dataSource, String query) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
