package server;


import client.data.GameInfo;
import client.data.PlayerInfo;
import model.*;
import server.model.Game;
import server.model.ServerModel;
import server.model.User;
import shared.definitions.ResourceType;
import shared.locations.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	//The gameId will be the integer that points to a particular game
	private List<Game> games;
	private List<User> users;
	private ServerModel serverModel;
	private Converter converter;
	private List<User> loggedInUsers;


	private int currentGameID;


	private int playerIndex;


	public static ServerFacade getSingleton() {
		if (singleton == null) {
			singleton = new ServerFacade();
		}
		return singleton;
	}
	private ServerFacade(){
		this.games = new ArrayList<>();
		this.users = new ArrayList<>();
		this.serverModel = new ServerModel();
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
		loggedInUsers.add(newUser);

		return "Success";
}

	@Override
	public String gamesList() {
		List<GameInfo> gamesInfo = new ArrayList<>();
		List<PlayerInfo> playersInfo = new ArrayList<>();
		int playerIndex = 0;

		for(Player player : serverModel.getClientModel().getPlayers()){
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
		boolean validIputNumbers = false;
		boolean validInputPorts = false;

		boolean randomTilesBool = randomTiles.matches("true");
		newGame.setRandomTiles(randomTilesBool);
		if(randomTilesBool || randomTiles.matches("false")){
			validInputTiles = true;
		}


		boolean randomNumbersBool = randomNumbers.matches("true");
		newGame.setRandomNumbers(randomNumbersBool);
		if(randomNumbersBool || randomNumbers.matches("false")){
			validIputNumbers = true;
		}

		boolean randomPortsBool = randomPorts.matches("true");
		newGame.setRandomPorts(randomPortsBool);
		if(randomPortsBool || randomPorts.matches("false")){
			validInputTiles = true;
		}



		GameInfo gameInfo = new GameInfo();
		gameInfo.setTitle(name);
		gameInfo.setId(games.size());
		gameInfo.setPlayers(new ArrayList<PlayerInfo>());

		String response = "Invalid request";
		if(validInputPorts && validInputTiles && validIputNumbers){
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

		Game desiredGame = null;
		for (int i = 0; i < games.size(); i++){
			if(games.get(i).getGameID() == ID){
				desiredGame = games.get(i);
			}
		}
		//How can I know who the player is that is logged in right now so I can properly add them to the game

		return response;
	}

	@Override
	public String gamesSave(int ID, String name) {
		String response = "Invalid request";
		if(ID >= 0 && ID < games.size() && name != null){
			response = "Success";
		}

		PrintWriter out = null;
		String model = null;
		String fileName = name + ".txt";
		try {
			out = new PrintWriter("/saves/" + fileName);
			model =converter.serialize(this.serverModel);
			out.println(model);
			out.close();

		} catch (FileNotFoundException | ClientException e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public String gamesLoad(String name) {
		//need to figure this out
		return null;
	}

	@Override

	public String sendChat(int playerIndex, String message) {
		MessageLine messageLine = new MessageLine(message,Integer.toString(playerIndex));
		this.serverModel.getClientModel().getChat().getLines().add(messageLine);
		try {
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);
			return converter.serialize(this.serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String acceptTrade(boolean willAccept) {
		//didn't implement in our  client
		return null;
	}

	@Override
	public String discardCards(List<ResourceType> discardedCards) {
		//didn't implement in our client
		return null;
	}

	@Override
	public String rollNumber(int number) {
		List<Hex> hexList = serverModel.getClientModel().getMap().getHexes();

		for(VertexObject setttlement : serverModel.getClientModel().getMap().getSettlements()){
			VertexDirection settDirec = setttlement.getVertexLocation().getNormalizedLocation().getDirection();
			HexLocation settHexLoc = setttlement.getVertexLocation().getNormalizedLocation().getHexLoc();
			HexLocation northNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.North);
			HexLocation northWestNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
			HexLocation northEastNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthEast);


			switch (settDirec){
				case NorthWest:
					for(Hex hex : hexList){
						if(settHexLoc.equals(hex.getLocation()) && hex.getNumber() == number){

						}
						if(hex.getLocation().equals(northNeighbor) && hex.getNumber() == number){

						}
						if(hex.getLocation().equals(northWestNeighbor) && hex.getNumber() == number){

						}
					}
					break;
				case NorthEast:
					break;
			}
		}

		for(VertexObject city : serverModel.getClientModel().getMap().getCities()){

		}


		return null;
	}


	@Override
	public String buildRoad(EdgeLocation roadLocation, String free) {
		free = free.toLowerCase();
		boolean isFree = free.equals("true");


		GameMap map = serverModel.getClientModel().getMap();

		map.getRoads().add(new Road(roadLocation));
		serverModel.getClientModel().setMap(map);
		if(!isFree){
			try {
				serverModel.addResource("brick",1);
				serverModel.addResource("wood",1);

			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		try {
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);

			return converter.serialize(this.serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String buildSettlement(VertexLocation vertexLocation, String free) {
		free = free.toLowerCase();
		boolean isFree = free.equals("true");

		if(!isFree){
			try {
				serverModel.addResource("wheat",1);
				serverModel.addResource("brick",1);
				serverModel.addResource("wood",1);
				serverModel.addResource("sheep",1);
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}

		GameMap map = serverModel.getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(vertexLocation);
		map.getSettlements().add(vertexObject);
		serverModel.getClientModel().setMap(map);

		try {
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);

			return converter.serialize(serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String buildCity(VertexLocation vertexLocation) {


		try {
			serverModel.addResource("wheat",2);
			serverModel.addResource("ore",3);

		} catch (ClientException e) {
			e.printStackTrace();
		}


		GameMap map = serverModel.getClientModel().getMap();
		VertexObject vertexObject = new VertexObject();
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
		serverModel.getClientModel().setMap(map);

		try {
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);

			return converter.serialize(serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String offerTrade(TradeOffer offer) {
		serverModel.getClientModel().getTradeOffer().setOffer(offer.getOffer());
		serverModel.getClientModel().getTradeOffer().setReceiver(offer.getReceiver());
		serverModel.getClientModel().getTradeOffer().setSender(offer.getSender());


		try {
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);

			return converter.serialize(serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) {

		try {
			serverModel.addResource(inputResource.toString(),ratio);
			serverModel.removeResource(outputResource.toString(),1);
			serverModel.getClientModel().setVersion(serverModel.getClientModel().getVersion() +1);

			return converter.serialize(serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String robPlayer(int victimIndex, HexLocation location) {
		return null;
	}

	@Override
	public String finishTurn() {
		return null;
	}

	@Override
	public String buyDevCard() {
		return null;
	}

	@Override
	public String soldier(int victimIndex, HexLocation location) {
		return null;
	}

	@Override
	public String yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		return null;
	}

	@Override
	public String roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		return null;
	}

	@Override
	public String monopoly(ResourceType resource, int playerIndex) {
		return null;
	}

	@Override
	public String monument() {
		return null;
	}


	public String getModel(){
		try {

			return converter.serialize(this.serverModel.getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void storeCommand(ICatanCommand command){
		//TODO: Auto-generated method stub
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


	public ServerModel getServerModel() {
		return serverModel;
	}

	public void setServerModel(ServerModel serverModel) {
		this.serverModel = serverModel;
	}

	@Override
	public String gamesModel() {
		// TODO Auto-generated method stub
		return null;
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



	public int getCurrentGameID() {
		return currentGameID;
	}

	public void setCurrentGameID(int currentGameID) {
		this.currentGameID = currentGameID;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
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
