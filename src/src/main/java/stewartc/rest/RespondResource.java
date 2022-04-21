package stewartc.rest;

import jakarta.ws.rs.core.*;
import jakarta.ws.rs.*;
import java.io.*;


@Path("/respond")
public class RespondResource
{
	
	@POST
	public String doPost(String message)
	{
		return message;
	}
}

