package stewartc.rest;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.*;
import java.io.*;

@Path("/createaccount")
public class AccountResource
{
	@GET
	public String doGet()
	{
		return "you should not GET from this endpoint";
	}

	@POST
	public String doPost(String message)
	{
		String username;
		String password;
		String[] divided = message.split("\\s+");
		username = divided[1].substring(3, divided[1].length()-1);
		password = divided[2].substring(3, divided[2].length()-1);
		return String.format("%s%n%s", username, password);
	}
}
