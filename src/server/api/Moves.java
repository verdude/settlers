package server.api;

import org.json.JSONObject;
import server.ICatanCommand;
import server.ServerFacade;
import server.commands.MovesAcceptTradeCommand;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;

/**
 * This class represents all of the moves endpoints
 * @author S Jacob Powell
 *
 */
@Path("/moves")
@Produces(MediaType.APPLICATION_JSON)
public class Moves {

	/**
	 * Accepts the trade from the given player to the other player
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/acceptTrade")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response acceptTrade(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		JSONObject body = new JSONObject(request);
		ICatanCommand acceptTrade = new MovesAcceptTradeCommand(Integer.parseInt(userCookieString), body.get("willAccept").toString().toLowerCase().contains("true"));
		String response = acceptTrade.execute(ServerFacade.getSingleton());
		if(response.contains("error")) {
			return Response.serverError().build();
		}
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Builds a city in the given location if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/buildCity")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response buildCity(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Builds a road on the given location if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/buildRoad")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response buildRoad(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Builds a settlement on the given location if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/buildSettlement")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response buildSettlement(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Adds a dev card to the given player if possible/valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/buyDevCard")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response buyDevCard(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Removes given cards from given player if they exist
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/discardCards")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response discardCards(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Finishes the turn for the given player
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/finishTurn")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response finishTurn(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Performs a maritime trade for the given player if possible
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/maritimeTrade")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response maritimeTrade(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Plays the Monopoly card for the given player if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/Monopoly")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response Monopoly(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Plays the Monument card for the given player if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/Monument")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response Monument(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Creates a trade offer with the given information from one player to another
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/offerTrade")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response offerTrade(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Plays the Road_Building card for the given player if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/Road_Building")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response roadBuilding(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Robs the given player
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/robPlayer")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response robPlayer(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Rolls the given number for the given player
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/rollNumber")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response rollNumber(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Sends a chat from the given player, adding the message to the message list
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/sendChat")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response sendChat(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Plays the Soldier card for the given player if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/Soldier")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response Soldier(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

	/**
	 * Plays the Year_Of_Plenty card for the given player if valid
	 * @pre The player is allowed to perform this move, according to the catan rules and the current state of the game
	 * @post The move is performed
	 * @param request The request body is injected into this String
	 * @param userCookieString The cookie is placed in this string
	 * @return The current game model
	 */
	@POST
	@Path("/Year_Of_Plenty")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response YearOfPlenty(
			String request,
			@CookieParam(value = "catan.user") String userCookieString,
			@CookieParam(value = "catan.game") String gameCookieString
			) {
		String decodedCookie = URLDecoder.decode(userCookieString);
		JSONObject cookie = new JSONObject(decodedCookie);

		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));

		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

}
