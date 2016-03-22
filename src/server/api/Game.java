package server.api;

import server.commands.GameAddAICommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class represents all of the game endpoints
 * @author S Jacob Powell
 *
 */
@Path("game")
@Produces(MediaType.APPLICATION_JSON)
public class Game {

	/**
	 * Adds an AI player to the game
	 * @pre There are less than four players and the game exists
	 * @post An AI player is joined to the game
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return Whether the action was successful
	 */
	@POST
	@Path("/addAI")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response addAI(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Gets the list of current AIs in the current game
	 * @pre A game exists from which to get the list
	 * @post The list of AI players is returned
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The lise of AI players
	 */
	@GET
	@Path("/listAI")
	@Produces({MediaType.APPLICATION_JSON})
	public Response listAI(
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Unimplemented\"}").build();
	}
}
