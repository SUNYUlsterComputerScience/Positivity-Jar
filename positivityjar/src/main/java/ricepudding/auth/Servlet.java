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


@BasicAuthenticationMechanismDefinition(
        realmName="${'test realm'}" // Doesn't need to be expression, just for example
)

@WebServlet("/servlet")
@DeclareRoles({ "user", "admin"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user", "admin"}))
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
