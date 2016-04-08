package model;

import java.util.List;

import pluginInterfaces.IGameDAO;
import pluginInterfaces.IUserDAO;
import server.ICatanCommand;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public interface IFacade {
	/**
	 * Logs the caller in to the server, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is known and logged in to the server.
	 * @return Whether it was successful
	 */
	public abstract String userLogin(String username, String password);

	/**
	 * Gets the Game DAO
	 * @return IGameDAO
     */
	public IGameDAO getGameDAO();

	/**
	 * Get the User DAO
	 * @return IUserDAO
     */
	public IUserDAO getUserDAO();

	/**
	 * 1) Creates a new user account
	 * 2) Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user now exists in the database of users and can log in and perform anything a user can.
	 * @return Whether it was successful
	 */
	public abstract String userRegister(String username, String password);

	/**
	 * This asks the server for a list of the games
	 * @pre The corresponding "canDo" method returns true.
	 * @post A json containing all of the current games is returned
	 * @return The list of games
	 */
	public abstract String gamesList();

	/**
	 * This method creates a new game in the server called by the name given
	 * @param name The name of the game to be created
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game is created and can now be joined and referenced by the name passed in.
	 * @return The game information that was created
	 */
	public abstract String gamesCreate(String randomTiles,
			String randomNumbers, String randomPorts, String name);

	/**
	 * Joins a game of the given id on the server
	 * @param ID ID of the game to join
	 * @param color The color for the player. Must be all lower case.
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is added to the game queue and all other game components as a player.
	 * @return Whether it was successful
	 */
	public abstract String gamesJoin(int ID, String color);

	/**
	 * 
	 * This method is for testing and debugging purposes. 
	 * Game files are saved to the server's saves/ directory.
	 * @param name Name of the game to save
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state now exists on the server and can be loaded
	 * @return Whether it was successful
	 */
	public abstract String gamesSave(int ID, String name);

	/**
	 * This method is for testing and debugging purposes. 
	 * Game files are loaded from the server's saves/ directory.
	 * @param name Name of the game to load
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state from the server is cloned and put into action.
	 * @return the json representation of the clientModel
	 */
	public abstract String gamesLoad(String name);

	/**
	 * Sends a message to the other players
	 * @param message The message you want to send
	 * @pre The corresponding "canDo" method returns true.
	 * @post A message is sent to the other players.
	 * @return Whether it was attempted
	 */
	public abstract String sendChat(int playerIndex, String message);

	/**
	 * Gets the model.
	 * @param version
	 * @return
     */
	public abstract String gamesModel(int version);

	/**
	 * Accept a trade that has been presented
	 * @param willAccept Whether the player accepted the trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post The trade presented is executed if willAccept is true, not executed otherwise.
	 * @return Whether it was attempted
	 */
	public abstract String acceptTrade(boolean willAccept);

	/**
	 * Tells the server which cards you discarded
	 * @param discardedCards The collection of cards to be discarded
	 * @pre The corresponding "canDo" method returns true.
	 * @post discardedCards is 1 card bigger and contains the card that is no longer in the player's possession, as it was discarded.
	 * @return Whether it was attempted
	 */
	public abstract String discardCards(List<ResourceType> discardedCards);

	/**
	 * Tells the server which number the player rolled
	 * @pre The corresponding "canDo" method returns true.
	 * @post The result of rolling the current number is performed.
	 */
	public abstract String rollNumber(int number);

	/**
	 * Places a road on the map
	 * @param free Whether the piece was free of cost
	 * @param roadLocation The new vertexLocation for the road
	 * @pre The corresponding "canDo" method returns true.
	 * @post A road is placed on the roadLocation if free is true as well. Otherwise, no road was placed.
	 * @return Whether it was attempted
	 */
	public abstract String buildRoad(EdgeLocation roadLocation, String free);

	/**
	 * 
	 * Builds a new settlement on the map
	 * @param free Whether the settlement was free of cost
	 * @param vertexObject The new vertexLocation of the settlement
	 * @pre The corresponding "canDo" method returns true.
	 * @post A settlement is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return Whether it was attempted
	 */
	public abstract String buildSettlement(VertexLocation vertexLocation,
			String free);

	/**
	 * Builds a new city on the map
	 * @param vertexObject The vertexLocation of the city to be built
	 * @pre The corresponding "canDo" method returns true.
	 * @post A city is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return Whether it was attempted
	 */
	public abstract String buildCity(VertexLocation vertexLocation);

	/**
	 * Offers a trade from one player to the other for resources
	 * @param offer The cards to be offered in a trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post A trade aggreement is presented to the player corresponding to receiver. The trade is made if receiver accepts the trade. The receiver receives offer and the offering player receives the counter part of the trade.
	 * @return Whether it was attempted
	 */
	public abstract String offerTrade(TradeOffer offer);

	/**
	 * Performs a maritme/ocean trade of resources
	 * @param ratio What the exchange rate ratio is
	 * @param inputResource What resoucre you are giving
	 * @param outputResource What resource you are getting
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player is less inputResource and more outputResource according to the ratio.
	 * @return Whether it was attempted
	 */
	public abstract String maritimeTrade(int ratio,
			ResourceType inputResource, ResourceType outputResource);

	/**
	 * Steals a card from a player
	 * @param location The new vertexLocation of the robber
	 * @param victimIndex The playerIndex of the person from which the card will be stolen
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber's new vertexLocation is vertexLocation and the player at victimIndex is less one card, and that card is given to the robbing player.
	 * @return Whether it was attempted
	 */
	public abstract String robPlayer(int victimIndex, HexLocation location);

	/**
	 * Tells the server that this player has finished his turn
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player can no longer perform and turn-related actions and the next player in the queue receives the currentTurn that the finishing player lost.
	 * @return Whether it was attempted
	 */
	public abstract String finishTurn();

	/**
	 * At@pre Nonets to buy a develpoment card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The players resources are given to the bank in the amount required for a dev card. The player receives a dev card.
	 * @return Whether it was attempted
	 */
	public abstract String buyDevCard();

	/**
	 * A Soldier card is played
	 * @param location The new robber vertexLocation
	 * @param victimIndex The player from which you are stealing a card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber is placed in vertexLocation, receiving that as a new vertexLocation, and the victimIndex-player is less one card which is added to the player's hand who played the soldier card.
	 * @return Whether it was attempted
	 */
	public abstract String soldier(int victimIndex, HexLocation location);

	/**
	 * Plays the yearofplenty card
	 * @param resource1 the first resource you want to receive
	 * @param resource2 the second resource you want to receive
	 * @pre The corresponding "canDo" method returns true.
	 * @post You receive resource1 and resource2 of your choice. (Check rules)
	 * @return Whether it was attempted
	 */
	public abstract String yearOfPlenty(ResourceType resource1,
			ResourceType resource2);

	/**
	 * Plays the RoadBuilding card
	 * @param spot1 The first spot that is connected to one of your roads
	 * @param spot2 The second road spot that is connected to on of your roads or the first spot
	 * @pre The corresponding "canDo" method returns true.
	 * @post Two roads are placed that belong to the corresponding player. They are placed correctly.
	 * @return Whether it was attempted
	 */
	public abstract String roadBuilding(shared.locations.EdgeLocation spot1,
			shared.locations.EdgeLocation spot2);

	/**
	 * Plays the monopoly card
	 * @param resource The resource being taken from other players
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player receives resource from every one of the players. They no longer have the corresponding resource and the year of plenty-playing player has their cards. (Check rules)
	 * @return Whether it was attempted
	 */
	public abstract String monopoly(ResourceType resource, int playerIndex);

	/**
	 * Plays the monument card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The monument card is played for the corresponding player.
	 * @return Whether it was attempted
	 */
	public abstract String monument();
	
	/**
	 * Stores Catan Command into database
	 * @pre The given command has successfully been executed by server
	 * @post The given command is stored in the database
	 */
	public abstract void storeCommand(ICatanCommand command);

	/**
	 * Gets a stringified JSON object 
	 * @pre The given command has successfully been executed by server
	 * @post The given command is stored in the database
	 */
	public abstract String gamesModel();	
	
}