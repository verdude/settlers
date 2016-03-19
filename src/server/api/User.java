package server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class represents all of the user endpoints
 * @author S Jacob Powell
 *
 */
@Path("user")
public class User {
	/**
	 * Logs the user in setting cookies etc.
	 * @pre The user exists in the server
	 * @post The user is logged in
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/login")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response login(
			String request
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Registers the new user into to the database
	 * @pre The user does not exist on the server and the credentials are valid
	 * @post The user is created
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this sString
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/register")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response register(
			String request
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}
}
