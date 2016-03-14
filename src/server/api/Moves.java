package server.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This class represents all of the moves endpoints
 * @author S Jacob Powell
 *
 */
@Path("moves")
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
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
			@Context String request,
			@CookieParam(value = "catan.user") String userCookieString
			) {
		return Response.ok().entity("{\"error\" : \"Unimplemented\"}").build();
	}

}
