package server;


import client.data.GameInfo;
import client.data.PlayerInfo;
import model.*;
import pluginInterfaces.IGameDAO;
import pluginInterfaces.IUserDAO;
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
	private int playerIndex;



	private IUserDAO userDAO;
	private IGameDAO gameDAO;
	private Factory factory;

	private int commandsToStore;

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
		factory = Factory.getSingleton("");
		userDAO = factory.getUserDAO();
		gameDAO = factory.getGameDAO();

		System.out.println(userDAO.getUsers());

		User[] tempUserArray =  Converter.deserialize(userDAO.getUsers(), User[].class);
		for(User user : tempUserArray){
			users.add(user);
		}
		users.sort((user1, user2) -> user1.getUserID() < user2.getUserID() ? -1:1);
		initializeGames();
	}

	private void initializeGames() {
		// get the commands
		ICatanCommand[] commands = Converter.deserialize(gameDAO.getCommands(), ICatanCommand[].class);
		// Get the games
		Game[] tempGameArray =  Converter.deserialize(gameDAO.getGames(), Game[].class);
		for(Game game : tempGameArray){
			games.add(game);
		}
		games.sort((game1, game2) -> game1.getGameID() < game2.getGameID() ? -1:1);

	}

	public void getContext() {
		try {
			ClientFacade.getSingleton().getContext();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IGameDAO getGameDAO() {
		return gameDAO;
	}

	@Override
	public IUserDAO getUserDAO() {
		return userDAO;
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
		userDAO.startTransaction();
		try {
			userDAO.addUser(username, password, users.size() - 1);
		}catch (Exception e){
			e.printStackTrace();
			userDAO.endTransaction(false);
			return "Failed to persist!!!";
		}
		userDAO.endTransaction(true);
		return "Success";
}

	@Override
	public String gamesList() {
		List<GameInfo> gamesInfo = new ArrayList<>();
		List<PlayerInfo> playersInfo = new ArrayList<>();


		if (games.size() == 0) {
			return "[]";
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

		gameDAO.startTransaction();
		try{
			gameDAO.storeGame(Converter.serialize(games.get(games.size()-1)), games.size()-1);
		}catch (Exception e){
			e.printStackTrace();
			gameDAO.endTransaction(false);
			return "Invalid, failed to persist!!!";
		}
		gameDAO.endTransaction(true);
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
				int loggedInIndex = -1;
				if(currentPlayer.getName().equals(users.get(playerIdAndUserIndex).getUsername())) {
					for(Player player : games.get(ID).getServerModel().getClientModel().getPlayers()){
						if( player != null && player.getPlayerID() == playerIdAndUserIndex ){
							loggedInIndex = player.getPlayerIndex();
							games.get(ID).getServerModel().getClientModel().getPlayers()[count].setColor(CatanColor.BLUE.fromString(color.toLowerCase()));


							response = "Success";
							games.get(ID).getServerModel().getClientModel().setVersion(games.get(ID).getServerModel().getClientModel().getVersion()+1);
							Player newPplayer = new Player(users.get(count).getUsername(), CatanColor.BLUE.fromString(color.toLowerCase()), loggedInIndex);
							return response;
						}

					}


					Player loggedInPlayer = games.get(ID).getServerModel().getClientModel().getPlayers()[playerIndex];
					for(Player player : games.get(ID).getServerModel().getClientModel().getPlayers()){
						//Rejects if the player is trying to be the color of another player

						if(player != null && player.getColor().equals(CatanColor.BLUE.fromString(color))  && player.getPlayerID() != loggedInPlayer.getPlayerID()){
							response = "The player could not be added to the specified game.";
							return response;
						}
						//Rejects incorrect spellings of colors
						if(CatanColor.BROWN.fromString(color) == null){
							response = "The player could not be added to the specified game.";
							return response;						}
					}
					games.get(ID).getServerModel().getClientModel().getPlayers()[count].setColor(CatanColor.BLUE.fromString(color.toLowerCase()));
				//	games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].setColor(CatanColor.BLUE.fromString(color.toLowerCase()));

					response = "Success";
					games.get(ID).getServerModel().getClientModel().setVersion(games.get(ID).getServerModel().getClientModel().getVersion()+1);
					Player player = new Player(users.get(count).getUsername(), CatanColor.BLUE.fromString(color.toLowerCase()), count);
					player.setPlayerID(playerIdAndUserIndex);
					games.get(ID).getServerModel().getClientModel().getPlayers()[count] = player;
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
				System.out.println("saves directory is created!");
			} else {
				System.out.println("Failed to create saves directory!");
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
		String source = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getName();

		MessageLine messageLine = new MessageLine(message,source);
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

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canAcceptTrade(playerIndex)){
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


		Player tempPlayer = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex];
		String source = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getName();

		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getLog().addMessage(new MessageLine(tempPlayer.getName() + " just rolled a " + number, source));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(number == 7){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Robbing");
			try {
				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
			} catch (ClientException e) {
				e.printStackTrace();
			}

		}

		Game currentGame = games.get(gameIdAndIndex);



		List<Hex> hexList = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getHexes();

		for(VertexObject setttlement : games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getSettlements()){
			Player player  = currentGame.getServerModel().getClientModel().getPlayers()[setttlement.getOwner()];
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

			currentGame.getServerModel().getClientModel().getPlayers()[player.getPlayerIndex()] = player;

		}

		for(VertexObject city : games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getCities()){
			Player player  = currentGame.getServerModel().getClientModel().getPlayers()[city.getOwner()];

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
			currentGame.getServerModel().getClientModel().getPlayers()[player.getPlayerIndex()] = player;

		}


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
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuildRoad(playerIndex,roadLocation,isFree)){
			return "Failure";
		}
		Player tempPlayer = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex];
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getLog().addMessage(new MessageLine(tempPlayer.getName() + " just placed a road!", tempPlayer.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Road> roads = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getRoads();
		List<VertexObject> settlements = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getSettlements();
		if(isFree && settlements.size() <= 5 && roads.size() < 5){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("FirstRound");
		}else if(isFree && settlements.size() <= 8 && roads.size() < 8){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("SecondRound");
		}else{
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Playing");

		}

		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();

		Road newRoad = new Road(roadLocation);
		newRoad.setOwner(playerIndex);
		map.getRoads().add(newRoad);

		games.get(gameIdAndIndex).getServerModel().getClientModel().setMap(map);
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].playRoad();

			if(!isFree){

				games.get(gameIdAndIndex).getServerModel().addResource("brick",1);
				games.get(gameIdAndIndex).getServerModel().addResource("wood",1);
				//games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIdAndUserIndex].playRoad();

			}
		} catch (ClientException e) {
			e.printStackTrace();
		}

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
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setLongestRoad(longestRoadIndex);

			return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());

		}catch (ClientException e){
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String buildSettlement(VertexLocation vertexLocation, String free) {

		free = free.toLowerCase();
		boolean isFree = free.equals("true");
		List<Road> roads = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getRoads();
		List<VertexObject> settlements = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().getSettlements();
		if(isFree && settlements.size() <= 5 && roads.size() < 5){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("FirstRound");
		}else if(isFree && settlements.size() <= 8 && roads.size() < 8){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("SecondRound");
		}else if(games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("SecondRound") && playerIndex == 0){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("SecondRound");

		}
		else{
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Playing");

		}
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuildSettlement(playerIndex,vertexLocation,isFree)){
			return "Failure";
		}
		Player tempPlayer = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex];
		try {
			games.get(gameIdAndIndex).getServerModel().getClientModel().getLog().addMessage(new MessageLine(tempPlayer.getName() + " just placed a settlement!", tempPlayer.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].playSettlement();

		if(!isFree){
				games.get(gameIdAndIndex).getServerModel().addResource("wheat",1);
				games.get(gameIdAndIndex).getServerModel().addResource("brick",1);
				games.get(gameIdAndIndex).getServerModel().addResource("wood",1);
				games.get(gameIdAndIndex).getServerModel().addResource("sheep",1);

			}
		}catch (ClientException e) {
			e.printStackTrace();
		}

		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(vertexLocation);
		vertexObject.setOwner(playerIndex);

		map.getSettlements().add(vertexObject);
		games.get(gameIdAndIndex).getServerModel().getClientModel().setMap(map);

			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
		try {


			return converter.serialize(this.games.get(gameIdAndIndex).getServerModel().getClientModel());

		}catch (ClientException e){
			e.printStackTrace();
		}


		return "Failure";
	}

	@Override
	public String buildCity(VertexLocation vertexLocation) {

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuildCity(playerIndex,vertexLocation)){
			return "Failure";
		}
		try {
			games.get(gameIdAndIndex).getServerModel().addResource("wheat",2);
			games.get(gameIdAndIndex).getServerModel().addResource("ore",3);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].playCity();


		} catch (ClientException e) {
			e.printStackTrace();
		}


		GameMap map = games.get(gameIdAndIndex).getServerModel().getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
		vertexObject.setOwner(playerIndex);
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

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canOfferTrade(playerIndex)){
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

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canMaritimeTrade(playerIndex,inputResource)){
			return "Failure";
		}
		try {
			games.get(gameIdAndIndex).getServerModel().addResource(inputResource.toString(),ratio);
			games.get(gameIdAndIndex).getServerModel().removeResource(outputResource.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

			return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return "Failure";
	}

	@Override
	public String robPlayer(int victimIndex, HexLocation location) {

		games.get(gameIdAndIndex).getServerModel().getClientModel().getMap().setRobber(location);
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canRobPlayer(victimIndex)){
			return "Failure";
		}

		if (games.get(gameIdAndIndex).getServerModel().getClientModel().canRobPlayer(victimIndex)) {

			if(games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[victimIndex].robPlayer() == null){
				games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
						games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
				games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Playing");
				try {
					return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
				} catch (ClientException e) {
					e.printStackTrace();
				}
			}
			switch (games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[victimIndex].robPlayer()) {

				case WOOD:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].
							getResources().addResource("wood",1);
					break;
				case BRICK:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].
							getResources().addResource("brick",1);
					break;
				case SHEEP:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].
							getResources().addResource("sheep",1);
					break;
				case WHEAT:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].
							getResources().addResource("wheat",1);
					break;
				case ORE:
					games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].
							getResources().addResource("ore",1);
					break;
			}


			try {
				games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
						games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
				games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Playing");
				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return "Failure";

	}

	@Override
	public String finishTurn() {

		if(games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("SecondRound") && playerIndex == 0){
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setCurrentTurn(0);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Rolling");


		}
		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canFinishTurn(playerIndex)){
			return "Failure";
		}

		Game currentGame = games.get(gameIdAndIndex);
		int nextPlayer = playerIndex;
		if(playerIndex == 3){
			nextPlayer = 0;
		}else{
			if(!games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("SecondRound")) {
				nextPlayer = playerIndex + 1;
			}


		}
		try {
			if (games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("FirstRound")) {
				if (playerIndex != 3) {
					games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setCurrentTurn((playerIndex + 1) % 4);
				}
				games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
						games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);

				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());

			}

			if (games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("SecondRound") && playerIndex != 0) {
				games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setCurrentTurn((playerIndex - 1) % 4);
//				if(playerIndex == 0) {
//					games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().setStatus("Rolling");
//				}
				games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
						games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
				return converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());

			} else {

				if(!games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("Rolling") && playerIndex != 0) {

					currentGame.getServerModel().getClientModel().getTurnTracker().setCurrentTurn(nextPlayer);
				}

			}
		}catch (ClientException e){
			e.printStackTrace();
		}
		if(games.get(gameIdAndIndex).getServerModel().getClientModel().getTurnTracker().getStatus().equals("Playing")) {
			currentGame.getServerModel().getClientModel().getTurnTracker().setStatus("Rolling");

		}

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

		if(!games.get(gameIdAndIndex).getServerModel().getClientModel().canBuyDevCard(playerIndex)){
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
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards().setYearOfPlenty(newDevs.getYearOfPlenty()+1);

		}else if(devCardList.getRoadBuilding() > 0){
			devCardList.setRoadBuilding(devCardList.getRoadBuilding() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards().setRoadBuilding(newDevs.getRoadBuilding()+1);

		}else if(devCardList.getMonument() > 0){
			devCardList.setMonument(devCardList.getMonument() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards().setMonument(newDevs.getMonument()+1);

		}else if(devCardList.getMonopoly() > 0){
			devCardList.setMonopoly(devCardList.getMonopoly() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards().setMonopoly(newDevs.getMonopoly()+1);

		}else if(devCardList.getSoldier() > 0){
			devCardList.setSoldier(devCardList.getSoldier() -1);
			DevCardList newDevs = games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards();
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getNewDevCards().setSoldier(newDevs.getSoldier()+1);

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

		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].removeDevCard(DevCardType.SOLDIER);

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
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getResources().addResource(resource1.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getResources().addResource(resource2.toString(),1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().setVersion(
					games.get(gameIdAndIndex).getServerModel().getClientModel().getVersion() +1);
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].removeDevCard(DevCardType.YEAR_OF_PLENTY);
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}


		return "Failure";
	}

	@Override
	public String roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {


		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].removeDevCard(DevCardType.ROAD_BUILD);
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
			games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].removeDevCard(DevCardType.MONOPOLY);
			return Converter.serialize(games.get(gameIdAndIndex).getServerModel().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public String monument() {

		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].setMonuments(
				games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].getMonuments() +1);
		games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[playerIndex].removeDevCard(DevCardType.MONUMENT);
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
		gameDAO.startTransaction();
		List<ICatanCommand> commandListArray = new ArrayList<>();

		try{
			ICatanCommand[] commandList = Converter.deserialize(gameDAO.getCommands(),ICatanCommand[].class);
			for(ICatanCommand tempCommand : commandList){
				commandListArray.add(tempCommand);
			}
			commandListArray.add(command);
			if(commandListArray.size() >= commandsToStore){
				gameDAO.storeCommands("[]", gameIdAndIndex);
				gameDAO.storeGame(Converter.serialize(games.get(gameIdAndIndex)),gameIdAndIndex);
			} else {
				gameDAO.storeCommands(Converter.serialize(commandListArray.toArray()),gameIdAndIndex);
			}
		}catch (Exception e){
			e.printStackTrace();
			gameDAO.endTransaction(false);
			return;
		}
		gameDAO.endTransaction(true);
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

	public void setPlayerIdAndUserIndex(int newPlayerIdAndUserIndex) {
		playerIdAndUserIndex = newPlayerIdAndUserIndex;
		System.out.println(playerIdAndUserIndex);
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}



	public int getCommandsToStore() {
		return commandsToStore;
	}

	public void setCommandsToStore(int commandsToStore) {
		this.commandsToStore = commandsToStore;
	}

//	public String getPersistenceType() {
//		return persistenceType;
//	}
//
//	public void setPersistenceType(String persistenceType) {
//		this.persistenceType = persistenceType;
//	}

//	public void updateColors(){
//		for(Player player : games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()){
//			player.setColor((games.get(gameIdAndIndex).getServerModel().getClientModel().getPlayers()[player.getPlayerIndex()].getColor()));
//		}
//	}


}
