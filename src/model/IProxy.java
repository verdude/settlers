package model;

import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.HexLocation;

/**
 * @author S Jacob Powell
 *	This is the Interface which will be implemented by the ServerProxy
 *	and provides all of the canDo methods. These provide precondition checking
 *	for each method that are required for the game and which correspond with the
 *	server's API
 */
public interface IProxy {
	/**
	 * Logs the caller in to the server, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is known and logged in to the server.
	 * @return The model
	 */
	public String userLogin(String username, String password);

	/**
	 * 1) Creates a new user account
	 * 2) Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user now exists in the database of users and can log in and perform anything a user can.
	 * @return The model
	 */
	public String userRegister(String username, String password);

	/**
	 * This asks the server for a list of the games
	 * @pre The corresponding "canDo" method returns true.
	 * @post A json containing all of the current games is returned
	 * @return The model
	 */
	public String gamesList();

	/**
	 * This method creates a new game in the server called by the name given
	 * @param name The name of the game to be created
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game is created and can now be joined and referenced by the name passed in.
	 * @return The model
	 */
	public String gamesCreate(String randomTiles, String randomNumbers, String randomPorts, String name);

	/**
	 * Joins a game of the given name on the server
	 * @param name Name of the game to join
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is added to the game que and all other game components as a player.
	 * @return The model
	 */
	public String gamesJoin(int ID, String color);	

	/**
	 * 
	 * This method is for testing and debugging purposes. 
	 * Game files are saved to the server's saves/ directory.
	 * @param name Name of the game to save
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state now exists on the server and can be loaded
	 * @return The model
	 */
	public String gamesSave(int ID, String name);

	/**
	 * This method is for testing and debugging purposes. 
	 * Game files are loaded from the server's saves/ directory.
	 * @param name Name of the game to load
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state from the server is cloned and put into action.
	 * @return The model
	 */
	public String gamesLoad(String name);

	/**
	 * Returns the current state of the game in JSON format.
	 * @param version The version number from the previously retrieved model
	 * @pre The corresponding "canDo" method returns true.
	 * @post The server's model of the current state of the game is cloned and set as the current game model locally.
	 * @return The model
	 */
	public String gamesModel(String version);

	/**
	 * Clears out the command history of the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post The current game is reverted back to the equivalent that no commands had been called.
	 * @return The model
	 */
	public String gamesReset();

	/**
	 * Returns a list of commands that have been executed in the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post A list of all the commands that have been executed is returned.
	 * @return The model
	 */
	public String gamesCommandsGet();

	/**
	 * Executes the specified command list in the current game
	 * @param commandList The list of commands to be executed on the server in the current game
	 * @pre The corresponding "canDo" method returns true.
	 * @post The list of commands that is passed in are executed in order on the server.
	 * @return The model
	 */
	public String gamesCommandsPost(String commandList);

	/**
	 * The body contains a JSON string array enumerating the different types of AI players.
	 * These are the values that may be passed to the /game/addAI method.
	 * @pre The corresponding "canDo" method returns true.
	 * @post A list of the AI players on the server is returned.
	 * @return The model
	 */
	public String gamesListAI();

	/**
	 * Adds an AI player to the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post An AI player is appended to the list of AI players found on the server and will be in the AI list.
	 * @return The model
	 */
	public String gamesAddAI(String AIType);

	/**
	 * Sets the server's logging level.
	 * @param logLevel The loglevel to set the server to
	 * @pre The corresponding "canDo" method returns true.
	 * @post The logging level on the server is set equal to logLevel
	 * @return The model
	 */
	public String utilChangeLogLevel(String logLevel);

	/**
	 * Sends a message to the other players
	 * @param message The message you want to send
	 * @pre The corresponding "canDo" method returns true.
	 * @post A message is sent to the other players.
	 * @return The model
	 */
	String sendChat(int playerIndex, String message);

	/**
	 * Accept a trade that has been presented
	 * @param willAccept Whether the player accepted the trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post The trade presented is executed if willAccept is true, not executed otherwise.
	 * @return The model
	 */
	public String acceptTrade(int playerIndex, boolean willAccept);

	/**
	 * Tells the server which cards you discarded
	 * @param discardedCards The collection of cards to be discarded
	 * @pre The corresponding "canDo" method returns true.
	 * @post discardedCards is 1 card bigger and contains the card that is no longer in the player's possession, as it was discarded.
	 * @return The model
	 */
	public String discardCards(int playerIndex, List<ResourceType> discardedCards);

	/**
	 * Tells the server which number the player rolled
	 * @param number Tells the server that you rolled this number
	 * @pre The corresponding "canDo" method returns true.
	 * @post The result of rolling the current number is performed.
	 * @return The model
	 */
	public String rollNumber(int playerIndex, int number);

	/**
	 * Places a road on the map
	 * @param free Whether the piece was free of cost
	 * @param roadLocation The new vertexLocation for the road
	 * @pre The corresponding "canDo" method returns true.
	 * @post A road is placed on the roadLocation if free is true as well. Otherwise, no road was placed.
	 * @return The model
	 */
	public String buildRoad(int playerIndex, EdgeValue roadLocation, String free);

	/**
	 * 
	 * Builds a new settlement on the map
	 * @param free Whether the settlement was free of cost
	 * @param vertexObject The new vertexLocation of the settlement
	 * @pre The corresponding "canDo" method returns true.
	 * @post A settlement is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return The model
	 */
	public String buildSettlement(int playerIndex, VertexObject vertexObject, String free);

	/**
	 * Builds a new city on the map
	 * @param vertexObject The vertexLocation of the city to be built
	 * @pre The corresponding "canDo" method returns true.
	 * @post A city is built on vertexObject if free is true. Otherwise, it is not built.
	 * @return The model
	 */
	public String buildCity(int playerIndex, VertexObject vertexObject);

	/**
	 * Offers a trade from one player to the other for resources
	 * @param offer The cards to be offered in a trade
	 * @param receiver The player with which this player wants to trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post A trade aggreement is presented to the player corresponding to receiver. The trade is made if receiver accepts the trade. The receiver receives offer and the offering player receives the counter part of the trade.
	 * @return The model
	 */
	public String offerTrade(int playerIndex, TradeOffer offer);

	/**
	 * Performs a maritme/ocean trade of resources
	 * @param ratio What the exchange rate ratio is
	 * @param inputResource What resoucre you are giving
	 * @param outputResource What resource you are getting
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player is less inputResource and more outputResource according to the ratio.
	 * @return The model
	 */
	public String maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource);

	/**
	 * Steals a card from a player
	 * @param location The new vertexLocation of the robber
	 * @param victimIndex The playerIndex of the person from which the card will be stolen
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber's new vertexLocation is vertexLocation and the player at victimIndex is less one card, and that card is given to the robbing player.
	 * @return The model
	 */
	public String robPlayer(int playerIndex, int victimIndex, HexLocation location);

	/**
	 * Tells the server that this player has finished his turn
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player can no longer perform and turn-related actions and the next player in the queue receives the currentTurn that the finishing player lost.
	 * @return The model
	 */
	public String finishTurn(int playerIndex);

	/**
	 * At@pre Nonets to buy a develpoment card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The players resources are given to the bank in the amount required for a dev card. The player receives a dev card.
	 * @return The model
	 */
	public String buyDevCard(int playerIndex);

	/**
	 * A Soldier card is played
	 * @param location The new robber vertexLocation
	 * @param victimIndex The player from which you are stealing a card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber is placed in vertexLocation, receiving that as a new vertexLocation, and the victimIndex-player is less one card which is added to the player's hand who played the soldier card.
	 * @return The model
	 */
	public String soldier(int playerIndex, int victimIndex, HexLocation location);

	/**
	 * Plays the yearofplenty card
	 * @param resource1 the first resource you want to receive
	 * @param resource2 the second resource you want to receive
	 * @pre The corresponding "canDo" method returns true.
	 * @post You receive resource1 and resource2 of your choice. (Check rules)
	 * @return The model
	 */
	public String yearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2);

	/**
	 * Plays the RoadBuilding card
	 * @param spot1 The first spot that is connected to one of your roads
	 * @param spot2 The second road spot that is connected to on of your roads or the first spot
	 * @pre The corresponding "canDo" method returns true.
	 * @post Two roads are placed that belong to the corresponding player. They are placed correctly.
	 * @return The model
	 */
	public String roadBuilding(int playerIndex, shared.locations.EdgeLocation spot1, shared.locations.EdgeLocation spot2);

	/**
	 * Plays the monopoly card
	 * @param resource The resource being taken from other players
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player receives resource from every one of the players. They no longer have the corresponding resource and the year of plenty-playing player has their cards. (Check rules)
	 * @return The model
	 */
	public String monopoly(ResourceType resource, int playerIndex);

	/**
	 * Plays the monument card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The monument card is played for the corresponding player.
	 * @return The model
	 */
	public String monument(int playerIndex);

	
}
