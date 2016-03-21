package server.api;


import org.json.JSONObject;
import server.ICatanCommand;
import server.ServerFacade;
import server.commands.UserLoginCommand;
<<<<<<< HEAD
import server.commands.UserRegisterCommand;
import server.ServerFacade;
=======
>>>>>>> work on endpoints

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;

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
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/login")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response login(
			String request
			) {
		String setUserCookie = "";
		JSONObject body = new JSONObject(request);
		String username = body.get("username").toString();
		String password = body.get("password").toString();
		if(username.isEmpty() || password.isEmpty()) {
			return Response.serverError().build();
		}
		ICatanCommand login = new UserLoginCommand(username, password);
		String response = login.execute(ServerFacade.getSingleton());
		if(response.contains("error")) {
			return Response.serverError().build();
		}
		setUserCookie = ServerFacade.getSingleton().getUsers().size() + "";
		return Response.ok().header("Set-cookie", setUserCookie).entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Registers the new user into to the database
	 * @pre The user does not exist on the server and the credentials are valid
	 * @post The user is created
	 * @param request The request body is injected into this String
	 * @return Whether the action was successful
	 */
	//The register command simply creates a new user, it does not log the user in and therefore does not set a cookie
	@POST
	@Path("/register")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response register(
			String request
			) {
		JSONObject body = new JSONObject(request);
		String username = body.get("username").toString();
		String password = body.get("password").toString();
		String repeatedPassword = body.get("repeatPassword").toString();
		if (!password.equals(repeatedPassword)) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Unimplemented\"}").build();
		} else {
			ICatanCommand register = new UserRegisterCommand(username, password);
			String response = register.execute(ServerFacade.getSingleton());
			if (response.contains("Success")) {
				return Response.ok().entity(response).build();
			} else {
				return Response.serverError().entity(response).build();
			}
		}
	}
}
