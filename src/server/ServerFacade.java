package server;


import client.data.GameInfo;
import client.data.PlayerInfo;
import model.*;
import server.model.Game;
import server.model.ServerModel;
import server.model.User;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that contains all of the commands which are to be done on the server model.
 * View the javadoc for IFacade for descriptions.
 * @author S. Jacob Powell
 *
 */

public class ServerFacade implements IFacade{

	private static ServerFacade singleton;
	private List<Game> games;
	private List<User> users;
	private Converter converter;
	private List<User> loggedInUsers;


	private int gameIdAndIndex;
	private int playerIdAndUserIndex;


	public static ServerFacade getSingleton() {
		if (singleton == null) {
			singleton = new ServerFacade();
		}
		return singleton;
	}
	private ServerFacade(){
		this.games = new ArrayList<>();
		this.users = new ArrayList<>();
		this.converter = new Converter();
		this.loggedInUsers = new ArrayList<>();


	}

	@Override
	public String userLogin(String username, String password) {

		if(username == null || username.matches("\\s*") || password == null || password.equals("\\s*")){
			return "Invalid Request";
		}
		for (User user : users){
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				loggedInUsers.add(user);
				return "Success";
			}
		}

		return "Failed to login - bad username or password.";

	}

	@Override
	public String userRegister(String username, String password) {

		for(User user : users){
			if(user.getUsername().equals(username)){
				return "Failed to register - someone already has that username.";
			}
		}

		User newUser = new User(username,password,users.size());
		users.add(newUser);

		return "Success";
}

	@Override
	public String gamesList() {
		List<GameInfo> gamesInfo = new ArrayList<>();
		List<PlayerInfo> playersInfo = new ArrayList<>();


		if (games.size() == 0) {
			return "{}";
		}
		int playerIndex = 0;
		for(Player player : games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()){
			if (player == null) {
				continue;
			}
			PlayerInfo playerInfo = new PlayerInfo();
			playerInfo.setPlayerIndex(playerIndex);
			playerIndex++;
			playerInfo.setId(player.getPlayerID());
			playerInfo.setName(player.getName());
			playerInfo.setColor(player.getColor());
			playersInfo.add(playerInfo);

		}

		for(Game game : games){
			GameInfo gameInfo = new GameInfo();
			gameInfo.setId(game.getGameID());
			gameInfo.setPlayers(playersInfo);
			gameInfo.setTitle(game.getTitle());
			gamesInfo.add(gameInfo);
		}

		String json = null;
		try {
			json =  converter.serialize(gamesInfo);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String gamesCreate(String randomTiles, String randomNumbers,
			String randomPorts, String name) {
		Game newGame = new Game(games.size(),name,new Player[4],new ServerModel());

		randomTiles = randomTiles.toLowerCase();
		randomNumbers = randomNumbers.toLowerCase();
		randomPorts = randomPorts.toLowerCase();

		boolean validInputTiles = false;
		boolean validInputNumbers = false;
		boolean validInputPorts = false;

		boolean randomTilesBool = randomTiles.matches("true");
		newGame.setRandomTiles(randomTilesBool);
		if(randomTilesBool){
			newGame.getServerModel().getClientModel().getMap().shuffleHexes();
		}
		if(randomTilesBool || randomTiles.matches("false")){
			validInputTiles = true;
		}


		boolean randomNumbersBool = randomNumbers.matches("true");
		newGame.setRandomNumbers(randomNumbersBool);
		if(randomNumbersBool || randomNumbers.matches("false")){
			validInputNumbers = true;
		}

		boolean randomPortsBool = randomPorts.matches("true");
		newGame.setRandomPorts(randomPortsBool);
		if(randomTilesBool){
			newGame.getServerModel().getClientModel().getMap().shufflePorts();
		}
		if(randomPortsBool || randomPorts.matches("false")){
			validInputPorts = true;
		}



		GameInfo gameInfo = new GameInfo();
		gameInfo.setTitle(name);
		gameInfo.setId(games.size());
		gameInfo.setPlayers(new ArrayList<PlayerInfo>());

		String response = "Invalid request";
		if(validInputPorts && validInputTiles && validInputNumbers){
			try {
				response =  converter.serialize(gameInfo);
				games.add(newGame);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	public String gamesJoin(int ID, String color) {
		String response = "The player could not be added to the specified game.";
		if(ID >= games.size()){
			return response;
		}
		color = color.toLowerCase();
		int count = 0;
		for(Player currentPlayer : games.get(ID).getServerModel().getClientModel().getPlayers()) {
			if(currentPlayer == null) {
				break;
			} else {

				if(currentPlayer.getName().equals(users.get(playerIdAndUserIndex).getUsername())) {

					Player loggedInPlayer = games.get(ID).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex];
					for(Player player : games.get(ID).getServerModel().getClientModel().getPlayers()){
						//Rejects if the player is trying to be the color of another player
						if(player.getColor().equals(CatanColor.BLUE.fromString(color))  && player.getPlayerID() != loggedInPlayer.getPlayerID()){
							response = "The player could not be added to the specified game.";
							return response;
						}
						//Rejects incorrect spellings of colors
						if(CatanColor.BROWN.fromString(color) == null){
							response = "The player could not be added to the specified game.";
							return response;						}
					}
					games.get(ID).getServerModel().getClientModel().getPlayers()[count].setColor(CatanColor.BLUE.fromString(color.toLowerCase()));

					response = "Success";
					return response;
				}
				count++;
			}
			if(count >= 4) {
				return response;
			}
		}
		Player player = new Player(users.get(count).getUsername(), CatanColor.BLUE.fromString(color.toLowerCase()), count);
		player.setPlayerID(playerIdAndUserIndex);
		games.get(ID).getServerModel().getClientModel().getPlayers()[count] = player;

		response = "Success";
		return response;
	}

	@Override
	public String gamesSave(int ID, String name) {


		File file = new File( System.getProperty("user.dir") + "/saves/");
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		String response = "Invalid request";
		if(ID >= 0 && ID < games.size() && name != null){
			response = "Success";
		}

		PrintWriter out = null;
		String model = null;
		String fileName = name + ".txt";
		try {
			out = new PrintWriter( System.getProperty("user.dir") + "/saves/" + fileName);
			model = converter.serialize(this.games.get(gameIdAndIndex).getServerModel());
			out.println(model);
			out.close();

		} catch (FileNotFoundException | ClientException e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public String gamesLoad(String name) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/saves/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File( System.getProperty("user.dir") + "/saves/" + name);
		if (file.exists()) {
			return lines.get(0);

		}else{
			return "Failure";
		}

	}

	@Override

	public String sendChat(int playerIndex, String message) {
		MessageLine messageLine = new MessageLine(message,Integer.toString(playerIndex));
		this.games.get(gameIdAndIndex).getServerModel().getClientModel().getChat().getLines().add(messageLine);
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String acceptTrade(boolean willAccept) {

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canAcceptTrade(playerIdAndUserIndex)){
			return "Failure";
		}
		TradeOffer tradeOffer = games.get(gameIdAndIndex).getServerModel().getClientModel().getTradeOffer();
		if(!willAccept){

		}

		return "Not Implemented";
	}

	@Override
	public String discardCards(List<ResourceType> discardedCards) {
		//didn't implement in our client
		return "Not Implemented";
	}

	@Override
	public String rollNumber(int number) {
		if(number == 7){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Robbing");
			try {
				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
			} catch (ClientException e) {
				e.printStackTrace();
			}

		}

		Game currentGame = games.get(gameIdAndIndex);
		Player player  = currentGame.getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex];


		List<Hex> hexList = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getHexes();

		for(VertexObject setttlement : games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getSettlements()){
			VertexDirection settDirec = setttlement.getVertexLocation().getNormalizedLocation().getDirection();
			HexLocation settHexLoc = setttlement.getVertexLocation().getNormalizedLocation().getHexLoc();
			HexLocation northNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.North);
			HexLocation northWestNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
			HexLocation northEastNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthEast);


			switch (settDirec){
				case NorthWest:
					for(Hex hex : hexList) {

						try {
							if (settHexLoc.equals(hex.getLocation()) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);
							}
							if (hex.getLocation().equals(northNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);

							}
							if (hex.getLocation().equals(northWestNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);

							}
						}catch (ClientException e){
							e.getStackTrace();
						}
					}
					break;
				case NorthEast:
					for(Hex hex : hexList){

						try {
							if (settHexLoc.equals(hex.getLocation()) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);
							}
							if (hex.getLocation().equals(northNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);

							}
							if (hex.getLocation().equals(northEastNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 1);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 1);

							}
						}catch (ClientException e){
							e.getStackTrace();
						}
					}
					break;
			}


		}

		for(VertexObject city : games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getCities()){
			VertexDirection cityDirec = city.getVertexLocation().getNormalizedLocation().getDirection();
			HexLocation cityHexLoc = city.getVertexLocation().getNormalizedLocation().getHexLoc();
			HexLocation northNeighbor = cityHexLoc.getNeighborLoc(EdgeDirection.North);
			HexLocation northWestNeighbor = cityHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
			HexLocation northEastNeighbor = cityHexLoc.getNeighborLoc(EdgeDirection.NorthEast);

			switch (cityDirec){
				case NorthWest:
					for(Hex hex : hexList){

						try {
							if (cityHexLoc.equals(hex.getLocation()) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);
							}
							if (hex.getLocation().equals(northNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);

							}
							if (hex.getLocation().equals(northWestNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);

							}
						}catch (ClientException e){
							e.getStackTrace();
						}
					}
					break;
				case NorthEast:
					for(Hex hex : hexList){

						try {
							if (cityHexLoc.equals(hex.getLocation()) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);
							}
							if (hex.getLocation().equals(northNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);

							}
							if (hex.getLocation().equals(northEastNeighbor) && hex.getNumber() == number) {
								player.getResources().addResource(hex.getResource(), 2);
								games.get(gameIdAndIndex).getServerModel().removeResource(hex.getResource(), 2);

							}
						}catch (ClientException e){
							e.getStackTrace();
						}
					}
					break;
			}
		}


		currentGame.getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex] = player;
		games.set(gameIdAndIndex,currentGame);


		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Playing");
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}


	@Override
	public String buildRoad(EdgeLocation roadLocation, String free) {

		free = free.toLowerCase();
		boolean isFree = free.equals("true");
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuildRoad(playerIdAndUserIndex,roadLocation,isFree)){
			return "Failure";
		}
		List<Road> roads = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getRoads();
		List<VertexObject> settlements = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getSettlements();
		if(isFree && settlements.size() <= 4 && roads.size() < 4){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("FirstRound");
		}else if(isFree && settlements.size() <= 8 && roads.size() < 8){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("SecondRound");
		}

		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();

		Road newRoad = new Road(roadLocation);
		newRoad.setOwner(playerIdAndUserIndex);
		map.getRoads().add(newRoad);

		games.get(gameIdAndIndex).getServerModel().getClientModel().setMap(map);
		if(!isFree){
			try {
				games.get(gameIdAndIndex).getServerModel().addResource("brick",1);
				games.get(gameIdAndIndex).getServerModel().addResource("wood",1);

			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			int longestRoad = 4;
			int longestRoadIndex = -1;
			for(Player player : games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()){
				if(15 -player.getRoads() > longestRoad){
					longestRoadIndex = player.getPlayerIndex();
					longestRoad = 15 -player.getRoads();
				}
			}
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setLongestRoad(longestRoadIndex);
			return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String buildSettlement(VertexLocation vertexLocation, String free) {
		free = free.toLowerCase();
		boolean isFree = free.equals("true");

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().
				canBuildSettlement(playerIdAndUserIndex,vertexLocation,isFree)){
			return "Failure";
		}
		if(!isFree){
			try {
				games.get(gameIdAndIndex).getServerModel().addResource("wheat",1);
				games.get(gameIdAndIndex).getServerModel().addResource("brick",1);
				games.get(gameIdAndIndex).getServerModel().addResource("wood",1);
				games.get(gameIdAndIndex).getServerModel().addResource("sheep",1);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}

		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(vertexLocation);
		vertexObject.setOwner(playerIdAndUserIndex);

		map.getSettlements().add(vertexObject);
		games.get(gameIdAndIndex).getServerModel().getClientModel().setMap(map);

		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String buildCity(VertexLocation vertexLocation) {

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuildCity(playerIdAndUserIndex,vertexLocation)){
			return "Failure";
		}
		try {
			games.get(gameIdAndIndex).getServerModel().addResource("wheat",2);
			games.get(gameIdAndIndex).getServerModel().addResource("ore",3);

		} catch (ClientException e) {
			e.printStackTrace();
		}


		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
		vertexObject.setOwner(playerIdAndUserIndex);
		vertexObject.setVertexLocation(vertexLocation);
		map.getCities().add(vertexObject);
		//Removes the settlement from the settlement list since it is being replaced with a city in the same spot
		for(int i = 0; i < map.getSettlements().size(); i++){
			VertexObject settlement = map.getSettlements().get(i);
			if(settlement.getVertexLocation().getNormalizedLocation().getHexLoc().equals(vertexLocation.getNormalizedLocation().getHexLoc())
					&& settlement.getVertexLocation().getNormalizedLocation().getDirection().equals(vertexLocation.getNormalizedLocation().getDirection())){
				map.getSettlements().remove(i);
				break;
			}
		}
		games.get(gameIdAndIndex).getServerModel().getClientModel().setMap(map);

		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String offerTrade(TradeOffer offer) {
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canOfferTrade(playerIdAndUserIndex)){
			return "Failure";
		}
		games.get(gameIdAndIndex).getServerModel().getClientModel().getTradeOffer().setOffer(offer.getOffer());
		games.get(gameIdAndIndex).getServerModel().getClientModel().getTradeOffer().setReceiver(offer.getReceiver());
		games.get(gameIdAndIndex).getServerModel().getClientModel().getTradeOffer().setSender(offer.getSender());


		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) {

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canMaritimeTrade(playerIdAndUserIndex,inputResource)){
			return "Failure";
		}
		try {
			games.get(gameIdAndIndex).getServerModel().addResource(inputResource.toString(),ratio);
			games.get(gameIdAndIndex).getServerModel().removeResource(outputResource.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return "Failure";
	}

	@Override
	public String robPlayer(int victimIndex, HexLocation location) {
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canRobPlayer(victimIndex)){
			return "Failure";
		}

		if (games.get(gameIdAndIndex).getServerModel().getClientModel().canRobPlayer(victimIndex)) {

			switch (games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[victimIndex].robPlayer()) {

				case WOOD:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].
							getResources().addResource("wood",1);
					break;
				case BRICK:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].
							getResources().addResource("brick",1);
					break;
				case SHEEP:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].
							getResources().addResource("sheep",1);
					break;
				case WHEAT:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].
							getResources().addResource("wheat",1);
					break;
				case ORE:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].
							getResources().addResource("ore",1);
					break;
			}


			try {
				games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
						games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
				games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().setRobber(location);
				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return "Failure";

	}

	@Override
	public String finishTurn() {

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canFinishTurn(playerIdAndUserIndex)){
			return "Failure";
		}
		Game currentGame = games.get(gameIdAndIndex);
		int nextPlayer;
		if(playerIdAndUserIndex == 3){
			nextPlayer = 0;
		}else{
			nextPlayer = playerIdAndUserIndex + 1;
		}
		currentGame.getServerModel().getClientModel().getTurnTracker().setCurrentTurn(nextPlayer);
		currentGame.getServerModel().getClientModel().getTurnTracker().setStatus("Rolling");

		games.set(gameIdAndIndex,currentGame);

		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String buyDevCard() {
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuyDevCard(playerIdAndUserIndex)){
			return "Failure";
		}

		try {
			games.get(gameIdAndIndex).getServerModel().addResource("ore",1);
			games.get(gameIdAndIndex).getServerModel().addResource("sheep",1);
			games.get(gameIdAndIndex).getServerModel().addResource("wheat",1);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		DevCardList devCardList = games.get(gameIdAndIndex).getServerModel().getDevCards();
		if(devCardList.getTotal() < 1){
			return "Failure";
		}else if(devCardList.getYearOfPlenty() > 0){
			devCardList.setYearOfPlenty(devCardList.getYearOfPlenty() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards().setYearOfPlenty(newDevs.getYearOfPlenty()+1);

		}else if(devCardList.getRoadBuilding() > 0){
			devCardList.setRoadBuilding(devCardList.getRoadBuilding() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards().setRoadBuilding(newDevs.getRoadBuilding()+1);

		}else if(devCardList.getMonument() > 0){
			devCardList.setMonument(devCardList.getMonument() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards().setMonument(newDevs.getMonument()+1);

		}else if(devCardList.getMonopoly() > 0){
			devCardList.setMonopoly(devCardList.getMonopoly() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards().setMonopoly(newDevs.getMonopoly()+1);

		}else if(devCardList.getSoldier() > 0){
			devCardList.setSoldier(devCardList.getSoldier() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getNewDevCards().setSoldier(newDevs.getSoldier()+1);

		}

		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";

	}

	@Override
	public String soldier(int victimIndex, HexLocation location) {
		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].removeDevCard(DevCardType.SOLDIER);

		int largestArmy = 2;
		int largestArmyIndex = -1;
		for(Player player : games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()){
			if(player.getSoldiers() > largestArmy){
				largestArmyIndex = player.getPlayerIndex();
				largestArmy = player.getSoldiers();
			}
		}

		games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setLargestArmy(largestArmyIndex);

		return robPlayer(victimIndex,location);
	}

	@Override
	public String yearOfPlenty(ResourceType resource1, ResourceType resource2) {


		try {
			games.get(gameIdAndIndex).getServerModel().removeResource(resource1.toString(),1);
			games.get(gameIdAndIndex).getServerModel().removeResource(resource2.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getResources().addResource(resource1.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getResources().addResource(resource2.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].removeDevCard(DevCardType.YEAR_OF_PLENTY);
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}


		return "Failure";
	}

	@Override
	public String roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {


		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].removeDevCard(DevCardType.ROAD_BUILD);
		buildRoad(spot1,"true");

		return buildRoad(spot2,"true");
	}

	@Override
	public String monopoly(ResourceType resource, int playerIndex) {
		Player evilPlayer = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex];
		ResourceList evilResources = evilPlayer.getResources();
		for(Player betrayedPlayer : games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()) {
			ResourceList betrayedResources = betrayedPlayer.getResources();

			switch (resource) {
				case WOOD:
					evilResources.addResource("wood",betrayedResources.getWood());
					betrayedResources.setWood(0);
					evilPlayer.setResources(evilResources);
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex] = evilPlayer;
					betrayedPlayer.setResources(betrayedResources);
					break;
				case BRICK:
					evilResources.addResource("brick",betrayedResources.getBrick());
					betrayedResources.setBrick(0);
					evilPlayer.setResources(evilResources);
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex] = evilPlayer;
					betrayedPlayer.setResources(betrayedResources);
					break;
				case SHEEP:
					evilResources.addResource("sheep",betrayedResources.getSheep());
					betrayedResources.setSheep(0);
					evilPlayer.setResources(evilResources);
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex] = evilPlayer;
					betrayedPlayer.setResources(betrayedResources);
					break;
				case WHEAT:
					evilResources.addResource("wheat",betrayedResources.getWheat());
					betrayedResources.setWheat(0);
					evilPlayer.setResources(evilResources);
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex] = evilPlayer;
					betrayedPlayer.setResources(betrayedResources);
					break;
				case ORE:
					evilResources.addResource("ore",betrayedResources.getOre());
					betrayedResources.setOre(0);
					evilPlayer.setResources(evilResources);
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex] = evilPlayer;
					betrayedPlayer.setResources(betrayedResources);
					break;
			}

		}
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].removeDevCard(DevCardType.MONOPOLY);
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String monument() {
		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].setMonuments(
				games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].getMonuments() +1);
		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].removeDevCard(DevCardType.MONUMENT);
		games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
				games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

		try {
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}




	public void storeCommand(ICatanCommand command){
		//TODO: Auto-generated method stub
	}

	public String gamesModel(int version) {
		try {
			if (version < games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion()) {
				return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());
			} else {
				return "true";
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String gamesModel() {
		try {

			return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public List<User> getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(List<User> loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}



	public int getGameIdAndIndex() {
		return gameIdAndIndex;
	}

	public void setGameIdAndIndex(int gameIdAndIndex) {
		this.gameIdAndIndex = gameIdAndIndex;
	}

	public int getPlayerIdAndUserIndex() {
		return playerIdAndUserIndex;
	}

	public void setPlayerIdAndUserIndex(int playerIdAndUserIndex) {
		this.playerIdAndUserIndex = playerIdAndUserIndex;
	}

	public Game getCurrentGame(int gameID){
		for(Game game : games){
			if(game.getGameID() == gameID){
				return game;
			}
		}
		return null;
	}


}
