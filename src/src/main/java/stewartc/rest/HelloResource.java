package stewartc.rest;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.*;

@Path("/hello")
public class HelloResource {

	@GET
	public String doGet() {
		return "Hello World From Cole";
	}

	@POST
	public String doPost() {
		return "Poopfart";
	}
}
