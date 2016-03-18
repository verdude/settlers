package server;

import model.*;
import server.model.Game;
import server.model.ServerModel;
import server.model.User;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the class that contains all of the commands which are to be done on the server model.
 * View the javadoc for IFacade for descriptions.
 * @author S. Jacob Powell
 *
 */

public class ServerFacade implements IFacade{

	private static ServerFacade singleton;
	//The gameId will be the integer that points to a particular game
	private Map<Integer,Game> games;
	private List<User> users;
	private ServerModel serverModel;

	public static ServerFacade getSingleton() {
		if (singleton == null) {
			singleton = new ServerFacade();
		}
		return singleton;
	}
	private ServerFacade(){
		this.games = new HashMap<>();
		this.users = new ArrayList<>();
		this.serverModel = new ServerModel();

	}

	@Override
	public boolean userLogin(String username, String password) {

		for (User user : users){
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean userRegister(String username, String password) {

		for(User user : users){
			if(user.getUsername().equals(username)){
				return false;
			}
		}

		User newUser = new User(username,password,users.size());
		users.add(newUser);

		return true;
	}

	@Override
	public String gamesList() {



		return null;
	}

	@Override
	public String gamesCreate(String randomTiles, String randomNumbers,
			String randomPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean gamesJoin(int ID, String color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gamesSave(int ID, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gamesLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChat(String playerName, String message) {



		return false;
	}

	@Override
	public boolean acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(List<ResourceType> discardedCards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int rollNumber(int number) {
		// Have to add correct resources for each settlement or city to the correct player
		serverModel.getClientModel().setRoll(number);
		return number;
	}

	@Override
	public boolean buildRoad(EdgeValue roadLocation, String free) {
		boolean isFree = free.equals("true");


		GameMap map = serverModel.getClientModel().getMap();

		map.getRoads().add(new Road(roadLocation.getLocation()));
		if(!isFree){
			try {
				serverModel.addResource("brick",1);
				serverModel.addResource("wood",1);

			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean buildSettlement(VertexObject vertexObject, String free) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(VertexObject vertexObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(TradeOffer offer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean robPlayer(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soldier(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean monopoly(ResourceType resource, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean monument() {
		// TODO Auto-generated method stub
		return false;
	}



	public Map<Integer,Game> getGames() {
		return games;
	}

	public void setGames(Map<Integer,Game> games) {
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
