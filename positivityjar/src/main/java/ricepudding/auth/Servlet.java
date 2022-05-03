package ricepudding.auth;

import jakarta.annotation.security.DeclareRoles;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This sets the Realm (space) for all users that will be utilizing the application
 * I have set two user roles below; "user" and "admin"
 */
@BasicAuthenticationMechanismDefinition(
        realmName = "${'positivity jar'}"
)


@WebServlet("/servlet") //connects to the servlet class which is where a person would use to log into the app
@DeclareRoles({"user", "admin"})
@ServletSecurity(@HttpConstraint(rolesAllowed = "user")) //only allows for those with the roles for now
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     *The code below  allows a person to log and will redirect them to a page letting them know
     * that they have successfully logged in and shows their current role
     * this is mostly for testing purposes and can be access via http://localhost:9080/servlet
     *It uses HTTP requests to verify the information and allow the user to log in
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write("This is a servlet \n");

        String webName = null;
        if (request.getUserPrincipal() != null) {
            webName = request.getUserPrincipal().getName();
        }

        response.getWriter().write("web username: " + webName + "\n");

        response.getWriter().write("web user has role \"user\": " + request.isUserInRole("user") + "\n");
        response.getWriter().write("web user has role \"admin\": " + request.isUserInRole("admin") + "\n");
    }

}

