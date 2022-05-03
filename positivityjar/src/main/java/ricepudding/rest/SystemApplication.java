package ricepudding.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * From all the documentation I have read this class is needed for JAX-RS
 * It basically states that all the resources that are needed to run the application
 * are in the system
 */
@ApplicationPath("system")
public class SystemApplication extends Application {
}