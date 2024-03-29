package server.resources;

import org.json.JSONObject;
import server.ICatanCommand;
import server.ServerFacade;
import server.commands.GamesModelCommand;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;

/**
 * This class represents all of the game endpoints
 * @author S Jacob Powell
 *
 */
@Path("/game")
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
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.status(Response.Status.BAD_REQUEST).entity("{\"Failure\" : \"Unimplemented\"}").build();
	}

	/**
	 * Gets the list of current AIs in the current game
	 * @pre A game exists from which to get the list
	 * @post The list of AI players is returned
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
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.status(Response.Status.BAD_REQUEST).entity("{\"Failure\" : \"Unimplemented\"}").build();
	}


	@GET
	@Path("/model")
	@Produces({MediaType.APPLICATION_JSON})
	public Response model(
			@QueryParam(value = "version") String version,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		ICatanCommand modelCommand = new GamesModelCommand(Integer.parseInt(version));
		String response = modelCommand.execute(ServerFacade.getSingleton());
		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		} else if (response.contains("true")) {
			return Response.ok().entity("\"" + response + "\"").build();
		} else {
			return Response.ok().entity(response).build();
		}
	}
}
