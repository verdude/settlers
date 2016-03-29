package server.resources;

import model.Converter;
import model.TradeOffer;
import org.json.JSONObject;
import server.ICatanCommand;
import server.ServerFacade;
import server.commands.*;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;
import java.util.ArrayList;

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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);
		ICatanCommand acceptTrade = new MovesAcceptTradeCommand(Integer.parseInt(userCookieString), body.get("willAccept").toString().toLowerCase().contains("true"));
		String response = acceptTrade.execute(ServerFacade.getSingleton());
		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		System.out.println(gameCookieString);
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesBuildCityCommand buildCityCommand = new MovesBuildCityCommand(body.getInt("playerIndex"),
				Converter.deserialize(body.getString("vertexLocation"), VertexLocation.class));

		String response = buildCityCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesBuildRoadCommand buildRoadCommand = new MovesBuildRoadCommand(body.getInt("playerIndex"),
				Converter.deserialize(body.getJSONObject("roadLocation").toString(), EdgeLocation.class), body.getBoolean("free"));

		String response = buildRoadCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesBuildSettlementCommand buildSettlementCommand = new MovesBuildSettlementCommand(body.getInt("playerIndex"),
		Converter.deserialize(body.getJSONObject("vertexLocation").toString(), VertexLocation.class), body.getBoolean("free"));


		String response = buildSettlementCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesBuyDevCardCommand buyDevCardCommand = new MovesBuyDevCardCommand(body.getInt("playerIndex"));

		String response = buyDevCardCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		ResourceType[] resources = Converter.deserialize(body.getString("discardedCards"), ResourceType[].class);

		ArrayList<ResourceType> convertedResources = new ArrayList<ResourceType>();

		for (ResourceType curr : resources) {
			convertedResources.add(curr);
		}

		MovesDiscardCardsCommand discardCardsCommand = new MovesDiscardCardsCommand(body.getInt("playerIndex"), convertedResources);

		String response = discardCardsCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesFinishTurnCommand finishTurnCommand = new MovesFinishTurnCommand(body.getInt("playerIndex"));

		String response = finishTurnCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesMaritimeTradeCommand movesMaritimeTradeCommand = new MovesMaritimeTradeCommand(body.getInt("playerIndex"),
				body.getInt("ratio"), Converter.deserialize(body.getString("inputResource"), ResourceType.class),
				Converter.deserialize(body.getString("outputResource"), ResourceType.class));

		String response = movesMaritimeTradeCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesMonopolyCommand monopolyCommand = new MovesMonopolyCommand(Converter.deserialize(body.getString("resource"),
				ResourceType.class), body.getInt("playerIndex"));

		String response = monopolyCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesMonumentCommand monumentCommand = new MovesMonumentCommand(body.getInt("playerIndex"));

		String response = monumentCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesOfferTradeCommand offerTradeCommand = new MovesOfferTradeCommand(
				Converter.deserialize(body.getString("offer"), TradeOffer.class)
		);

		String response = offerTradeCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesRoadBuildingCommand roadBuildingCommand = new MovesRoadBuildingCommand(body.getInt("playerIndex"),
				Converter.deserialize(body.getString("spot1"), EdgeLocation.class),
				Converter.deserialize(body.getString("spot2"), EdgeLocation.class));

		String response = roadBuildingCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesRobPlayerCommand robPlayerCommand = new MovesRobPlayerCommand(body.getInt("playerIndex"), body.getInt("victimIndex"),
				Converter.deserialize(body.getString("location"), HexLocation.class));

		String response = robPlayerCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesRollNumberCommand rollNumberCommand = new MovesRollNumberCommand(body.getInt("playerIndex"),
				body.getInt("number"));

		ServerFacade.getSingleton();
		String response = rollNumberCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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

		System.out.println(gameCookieString);
		ServerFacade.getSingleton().setPlayerIdAndUserIndex(cookie.getInt("playerID"));
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesSendChatCommand sendChatCommand = new MovesSendChatCommand(body.getInt("playerIndex"), body.getString("content"));

		String response = sendChatCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesSoldierCommand soldierCommand = new MovesSoldierCommand(body.getInt("playerIndex"), body.getInt("victimIndex"),
				Converter.deserialize(body.getString("location"), HexLocation.class));

		String response = soldierCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
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
		ServerFacade.getSingleton().setGameIdAndIndex(Integer.parseInt(gameCookieString));

		JSONObject body = new JSONObject(request);

		MovesYearOfPlentyCommand yearOfPlentyCommand = new MovesYearOfPlentyCommand(body.getInt("playerIndex"),
				Converter.deserialize(body.getString("resource1"), ResourceType.class),
				Converter.deserialize(body.getString("resource2"), ResourceType.class)
		);

		String response = yearOfPlentyCommand.execute(ServerFacade.getSingleton());

		if(response.contains("error")) {
			return Response.serverError().entity("\""+response+"\"").build();
		}
		return Response.ok().entity(response).build();
	}

}

