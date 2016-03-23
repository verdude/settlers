package server.api;

import org.json.JSONObject;
import server.ServerFacade;
import server.commands.GamesCreateCommand;
import server.commands.GamesJoinCommand;
import server.commands.GamesListCommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;

/**
 * This class represents all of the games endpoints
 * @author S Jacob Powell
 *
 */
@Path("/games")
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
	@Produces({MediaType.TEXT_PLAIN})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response join(
			String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		String setGameCookie = "";
		JSONObject body = new JSONObject(request);
		int id = body.getInt("id");
		String color = body.getString("color");
		GamesJoinCommand joinCommand = new GamesJoinCommand(id, color);
		String result = joinCommand.execute(ServerFacade.getSingleton());
		// TODO need to get the game id from the joinCommand result and then update the facade.
		if (result.contains("The player could not be added to the specified game.")) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
		}
		return Response.ok().header("Set-cookie", setGameCookie).entity(result).build();
	}

	/**
	 * Gets a list of all of the current games
	 * @pre The server is running
	 * @post The list of games is returned
	 * @param userCookieString The cookie is placed in this string
	 * @return The list of current games
	 */
	@GET
	@Path("/list")
	@Produces({MediaType.APPLICATION_JSON})
	public Response list(
			@CookieParam(value = "catan.user") String userCookieString
			) {
		GamesListCommand list = new GamesListCommand();
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));
		String result = list.execute(ServerFacade.getSingleton());
		return Response.ok().entity(result).build();
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
		JSONObject body = new JSONObject(request);
		boolean randomTiles = body.getBoolean("randomTiles");
		boolean randomNumbers = body.getBoolean("randomNumbers");
		boolean randomPorts = body.getBoolean("randomPorts");
		String gameName = body.getString("name");

		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		GamesCreateCommand createCommand = new GamesCreateCommand(randomTiles, randomNumbers, randomPorts, gameName);
		String result = createCommand.execute(ServerFacade.getSingleton());
		if (result.equals("Invalid request")) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
		}
		return Response.ok().entity(result).build();
	}

}
