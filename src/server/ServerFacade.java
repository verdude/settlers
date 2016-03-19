package server;


import client.data.GameInfo;
import client.data.PlayerInfo;
import model.*;
import server.model.Game;
import server.model.ServerModel;
import server.model.User;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

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
	Converter converter;

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


	}

	@Override
	public String userLogin(String username, String password) {

		if(username == null || username.matches("\\s*") || password == null || password.equals("\\s*")){
			return "Invalid Request";
		}
		for (User user : users){
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
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
		Game newGame = new Game(games.size(),name,new Player[4],new ClientModel());

		boolean validInputTiles = false;
		boolean validIputNumbers = false;
		boolean validInputPorts = false;

		boolean randomTilesBool = randomTiles.matches("(T|t)(R|r)(U|u)(E|e)");
		newGame.setRandomTiles(randomTilesBool);
		if(randomTilesBool || randomTiles.matches("(F|f)(A|a)(L|l)(S|s)(E|e)")){
			validInputTiles = true;
		}


		boolean randomNumbersBool = randomNumbers.matches("(T|t)(R|r)(U|u)(E|e)");
		newGame.setRandomNumbers(randomNumbersBool);
		if(randomNumbersBool || randomNumbers.matches("(F|f)(A|a)(L|l)(S|s)(E|e)")){
			validIputNumbers = true;
		}

		boolean randomPortsBool = randomPorts.matches("(T|t)(R|r)(U|u)(E|e)");
		newGame.setRandomPorts(randomPortsBool);
		if(randomPortsBool || randomPorts.matches("(F|f)(A|a)(L|l)(S|s)(E|e)")){
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
		return null;
	}

	@Override
	public String gamesSave(int ID, String name) {
		return null;
	}

	@Override
	public String gamesLoad(String name) {
		return null;
	}

	@Override

	public String sendChat(int playerIndex, String message) {
		return null;
	}

	@Override
	public String acceptTrade(boolean willAccept) {
		return null;
	}

	@Override
	public String discardCards(List<ResourceType> discardedCards) {
		return null;
	}

	@Override
	public String rollNumber(int number) {
		return null;
	}


	//Need to finish implementing this one!!!!
	@Override
	public String buildRoad(EdgeLocation roadLocation, String free) {
		boolean isFree = free.equals("true");


		GameMap map = serverModel.getClientModel().getMap();

		map.getRoads().add(new Road(roadLocation));
		if(!isFree){
			try {
				serverModel.addResource("brick",1);
				serverModel.addResource("wood",1);

			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String buildSettlement(VertexObject vertexObject, String free) {
		return null;
	}

	@Override
	public String buildCity(VertexObject vertexObject) {
		return null;
	}

	@Override
	public String offerTrade(TradeOffer offer) {
		return null;
	}

	@Override
	public String maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) {
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
		return "Failure";
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
	
}
