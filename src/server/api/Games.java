package server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class represents all of the games endpoints
 * @author S Jacob Powell
 *
 */
@Path("games")
@Produces(MediaType.APPLICATION_JSON)
public class Games {

	/**
	 * Joins the current player to the game
	 * @pre The game exists
	 * @post The player is added to the game's players list
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/join")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response join(
			String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Gets a list of all of the current games
	 * @pre The server is running
	 * @post The list of games is returned
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The list of current games
	 */
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response list(
			String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Gets the latest model if there was an update
	 * @pre A game is joined
	 * @post The model or "true" is returned
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The model, if there is a newer version, "true" otherwise
	 */
	@POST
	@Path("/model")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response model(
			String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Creates a new game with the given options
	 * @pre A game doesn't exist with the given ID
	 * @post A game is created from the given information
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/create")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response create(
			String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

}
