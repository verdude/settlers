package model;

import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

public class ClientFacade {
	
	private static ClientModel  clientModel;
	private static ClientFacade singleton;
	private static ServerProxy proxy;
	

	/**
	 * Default Constructor
	 * 
	 */
	private ClientFacade() {
		clientModel	= new ClientModel();
		ServerPoller.getSingleton(proxy);
	}

	/**
	 * Creates and returns the ClientFacade singleton if it doesn't already exist
	 * @return The client facade singleton
	 */
	public static ClientFacade getSingleton() {
		if (singleton == null) {
			singleton = new ClientFacade();
		}
		return singleton;
	}
	
	public void updateModel(ClientModel newModel) {
		clientModel = newModel;
	}

	/**
	 * Logs the caller in to the server, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is known and logged in to the server.
	 * @return The model
	 */
	public boolean userLogin(String username, String password) {
		String response = proxy.userLogin(username, password);
		return response.contains("Success");
	}

	/**
	 * 1) Creates a new user account
	 * 2) Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user now exists in the database of users and can log in and perform anything a user can.
	 * @return The model
	 */
	public boolean userRegister(String username, String password) {
		String response = proxy.userRegister(username, password);
		return response.contains("Success");
	}

	/**
	 * This asks the server for a list of the games
	 * @pre The corresponding "canDo" method returns true.
	 * @post A json containing all of the current games is returned
	 * @return The model
	 */
	public String gamesList() {
		return proxy.gamesList();
	}
	

	/**
	 * This method creates a new game in the server called by the name given
	 * @param name The name of the game to be created
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game is created and can now be joined and referenced by the name passed in.
	 * @return The model
	 */
	public String gamesCreate(String randomTiles, String randomNumbers, String randomPorts, String name) {
		return proxy.gamesCreate(randomTiles, randomNumbers, randomPorts, name);
	}

	/**
	 * Joins a game of the given name on the server
	 * @param name Name of the game to join
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is added to the game que and all other game components as a player.
	 * @return The model
	 */
	public boolean gamesJoin(int ID, String color) {
		String response = proxy.gamesJoin(ID, color);
		return response.contains("Success");
	}	

	/**
	 * 
	 * This method is for testing and debugging purposes. 
	 * Game files are saved to the server's saves/ directory.
	 * @param name Name of the game to save
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state now exists on the server and can be loaded
	 * @return The model
	 */
	public boolean gamesSave(int ID, String name) {
		String response = proxy.gamesSave(ID, name);
		return response.contains("Success");
		}

	/**
	 * This method is for testing and debugging purposes. 
	 * Game files are loaded from the server's saves/ directory.
	 * @param name Name of the game to load
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state from the server is cloned and put into action.
	 * @return The model
	 */
	public boolean gamesLoad(String name) {
		String response = proxy.gamesLoad(name);
		return response.contains("Success");
	}

	public boolean sendChat(int playerIndex, String message) {
		boolean canDo = clientModel.canSendChat(message);
		if(canDo)
		{
			String model = proxy.sendChat(playerIndex, message);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Accept a trade that has been presented
	 * @param willAccept Whether the player accepted the trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post The trade presented is executed if willAccept is true, not executed otherwise.
	 * @return The model
	 */
	public boolean acceptTrade(int playerIndex, boolean willAccept) {
		boolean canDo = clientModel.canAcceptTrade(playerIndex);
		if(canDo)
		{
			String model = proxy.acceptTrade(playerIndex, willAccept);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Tells the server which cards you discarded
	 * @param discardedCards The collection of cards to be discarded
	 * @pre The corresponding "canDo" method returns true.
	 * @post discardedCards is 1 card bigger and contains the card that is no longer in the player's possession, as it was discarded.
	 * @return The model
	 */
	public boolean discardCards(int playerIndex, List<ResourceType> discardedCards) {
		boolean canDo = clientModel.canDiscardCards(playerIndex);
		if(canDo)
		{
			String model = proxy.discardCards(playerIndex, discardedCards);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Tells the server which number the player rolled
	 * @param number Tells the server that you rolled this number
	 * @pre The corresponding "canDo" method returns true.
	 * @post The result of rolling the current number is performed.
	 * @return The model
	 */
	public boolean rollNumber(int playerIndex, int number) {
		boolean canDo = clientModel.canRollNumber(playerIndex);
		if(canDo)
		{
			String model = proxy.rollNumber(playerIndex, number);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Places a road on the map
	 * @param free Whether the piece was free of cost
	 * @param roadLocation The new location for the road
	 * @pre The corresponding "canDo" method returns true.
	 * @post A road is placed on the roadLocation if free is true as well. Otherwise, no road was placed.
	 * @return The model
	 */
	public boolean buildRoad(int playerIndex, EdgeValue roadLocation, boolean free) {
		boolean canDo = clientModel.canBuildRoad(playerIndex, roadLocation);
		if(canDo)
		{
			String model = proxy.buildRoad(playerIndex, roadLocation, free);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * 
	 * Builds a new settlement on the map
	 * @param free Whether the settlement was free of cost
	 * @param vertexObject The new location of the settlement
	 * @pre The corresponding "canDo" method returns true.
	 * @post A settlement is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return The model
	 */
	public boolean buildSettlement(int playerIndex, VertexObject vertexObject, String free) {
		boolean canDo = clientModel.canBuildSettlement(playerIndex, vertexObject);
		if(canDo)
		{
			String model = proxy.buildSettlement(playerIndex, vertexObject, free);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Builds a new city on the map
	 * @param vertexObject The location of the city to be built
	 * @pre The corresponding "canDo" method returns true.
	 * @post A city is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return The model
	 */
	public boolean buildCity(int playerIndex, VertexObject vertexObject, String free) {
		boolean canDo = clientModel.canBuildCity(playerIndex, vertexObject);
		if(canDo)
		{
			String model = proxy.buildCity(playerIndex, vertexObject, free);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Offers a trade from one player to the other for resources
	 * @param offer The cards to be offered in a trade
	 * @param receiver The player with which this player wants to trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post A trade aggreement is presented to the player corresponding to receiver. The trade is made if receiver accepts the trade. The receiver receives offer and the offering player receives the counter part of the trade.
	 * @return The model
	 */
	public boolean offerTrade(int playerIndex, TradeOffer offer) {
		boolean canDo = clientModel.canOfferTrade(playerIndex);
		if(canDo)
		{
			String model = proxy.offerTrade(playerIndex, offer);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Performs a maritme/ocean trade of resources
	 * @param ratio What the exchange rate ratio is
	 * @param inputResource What resoucre you are giving
	 * @param outputResource What resource you are getting
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player is less inputResource and more outputResource according to the ratio.
	 * @return The model
	 */
	public boolean maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) {
		boolean canDo = clientModel.canMaritimeTrade(playerIndex, inputResource);
		if(canDo)
		{
			String model = proxy.maritimeTrade(playerIndex, ratio, inputResource, outputResource);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Steals a card from a player
	 * @param location The new location of the robber
	 * @param victimIndex The playerIndex of the person from which the card will be stolen
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber's new location is location and the player at victimIndex is less one card, and that card is given to the robbing player.
	 * @return The model
	 */
	public boolean robPlayer(int playerIndex, int victimIndex, HexLocation location) {
		boolean canDo = clientModel.canRobPlayer(playerIndex);
		if(canDo)
		{
			String model = proxy.robPlayer(playerIndex, victimIndex, location);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Tells the server that this player has finished his turn
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player can no longer perform and turn-related actions and the next player in the queue receives the currentTurn that the finishing player lost.
	 * @return The model
	 */
	public boolean finishTurn(int playerIndex) {
		boolean canDo = clientModel.canFinishTurn(playerIndex);
		if(canDo)
		{
			String model = proxy.finishTurn(playerIndex);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * At@pre Nonets to buy a develpoment card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The players resources are given to the bank in the amount required for a dev card. The player receives a dev card.
	 * @return The model
	 */
	public boolean buyDevCard(int playerIndex) {
		boolean canDo = clientModel.canBuyDevCard(playerIndex);
		if(canDo)
		{
			String model = proxy.buyDevCard(playerIndex);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * A Soldier card is played
	 * @param location The new robber location
	 * @param victimIndex The player from which you are stealing a card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber is placed in location, receiving that as a new location, and the victimIndex-player is less one card which is added to the player's hand who played the soldier card.
	 * @return The model
	 */
	public boolean soldier(int playerIndex, int victimIndex, HexLocation location) {
		boolean canDo = clientModel.canSoldier(playerIndex);
		if(canDo)
		{
			String model = proxy.soldier(playerIndex, victimIndex, location);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Plays the yearofplenty card
	 * @param resource1 the first resource you want to receive
	 * @param resource2 the second resource you want to receive
	 * @pre The corresponding "canDo" method returns true.
	 * @post You receive resource1 and resource2 of your choice. (Check rules)
	 * @return The model
	 */
	public boolean yearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
		boolean canDo = clientModel.canYearOfPlenty(playerIndex);
		if(canDo)
		{
			String model = proxy.yearOfPlenty(playerIndex, resource1, resource2);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Plays the RoadBuilding card
	 * @param spot1 The first spot that is connected to one of your roads
	 * @param spot2 The second road spot that is connected to on of your roads or the first spot
	 * @pre The corresponding "canDo" method returns true.
	 * @post Two roads are placed that belong to the corresponding player. They are placed correctly.
	 * @return The model
	 */
	public boolean roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		boolean canDo = clientModel.canRoadBuilding(playerIndex);
		if(canDo)
		{
			String model = proxy.roadBuilding(playerIndex, spot1, spot2);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Plays the monopoly card
	 * @param resource The resource being taken from other players
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player receives resource from every one of the players. They no longer have the corresponding resource and the year of plenty-playing player has their cards. (Check rules)
	 * @return The model
	 */
	public boolean monopoly(ResourceType resource, int playerIndex) {
		boolean canDo = clientModel.canMonopoly(playerIndex);
		if(canDo)
		{
			String model = proxy.monopoly(resource, playerIndex);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}

	/**
	 * Plays the monument card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The monument card is played for the corresponding player.
	 * @return The model
	 */
	public boolean monument(int playerIndex) {
		boolean canDo = clientModel.canMonument(playerIndex);
		if(canDo)
		{
			String model = proxy.monument(playerIndex);
			updateModel((ClientModel) Converter.deserialize(model));
		}
		return canDo;
	}
	
}
