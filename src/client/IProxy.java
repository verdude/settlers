/**
 * 
 */
package client;

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
	 * @return True if the log in was successful
	 */
	public boolean userLogin(String username, String password);
	
	/**
	 * 1) Creates a new user account
	 * 2) Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @return True if the registration and log in were successful
	 */
	public boolean userRegister(String username, String password);
	
	/**
	 * This asks the server for a list of the games
	 * @return The list of games in json form
	 */
	public String gamesList();
	
	/**
	 * This method creates a new game in the server called by the name given
	 * @param name The name of the game to be created
	 * @return True if the game was successfully created
	 */
	public boolean gamesCreate(String name);
	
	/**
	 * Joins a game of the given name on the server
	 * @param name Name of the game to join
	 * @return Whether the join was successful
	 */
	public boolean gamesJoin(String name);	
	
	/**
	 * 
	 * This method is for testing and debugging purposes. When a bug is found, you can use the
/games/save method to save the state of the game to a file, and attach the file to a bug report.
A developer can later restore the state of the game when the bug occurred by loading the
previously saved file using the /games/load method. Game files are saved to and loaded from
the server's saves/ directory.

	 * @param name Name of the game to save
	 * @return Whether the save was successful
	 */
	public boolean gamesSave(String name);
	
	/**
	 * This method is for testing and debugging purposes. When a bug is found, you can use the
/games/save method to save the state of the game to a file, and attach the file to a bug report.
A developer can later restore the state of the game when the bug occurred by loading the
previously saved file using the /games/load method. Game files are saved to and loaded from
the server's saves/ directory.

	 * @param name Name of the game to load
	 * @return Whether the load was successful
	 */
	public boolean gamesLoad(String name);
	
	/**
	 * Returns the current state of the game in JSON format.
	 * @param version The version nember from the previously retrieved model
	 * @return The current Model ID on the server
	 */
	public String gamesModel(String version);
	
	/**
	 * Clears out the command history of the current game.
	 * @return Whether the reset was successful
	 */
	public boolean gamesReset();
	
	/**
	 * Returns a list of commands that have been executed in the current game.
	 * @return The list of commands
	 */
	public String gamesCommandsGet();
	
	/**
	 * Executes the specified command list in the current game
	 * @param commandList The list of commands to be executed on the server in the current game
	 * @return Whether the list of commands successfully executed
	 */
	public boolean gamesCommandsPost(String commandList);
	
	/**
	 * The body contains a JSON string array enumerating the different types of AI players.
These are the values that may be passed to the /game/addAI method.
	 * @return Returns a list of supported AI player types
	 */
	public String gamesListAI();
	
	/**
	 * Adds an AI player to the current game.
	 * @return Whether the AI player was added
	 */
	public boolean gamesAddAI();
	
	/**
	 * Sets the server’s logging level.
	 * @param logLevel The loglevel to set the server to
	 * @return Whether the logLevel was set in the server
	 */
	public boolean utilChangLogLevel(String logLevel);
	
	/**
	 * Sends a message to the other players
	 * @param message The message you want to send
	 * @return Whether the message was sent
	 */
	public boolean sendChat(String message);
	
	/**
	 * Accept a trade that has been presented
	 * @param willAccept Whether the player accepted the trade
	 * @return Whether the trade was accepted or not on the server
	 */
	public boolean acceptTrade(boolean willAccept);
	
	/**
	 * Tells the server which cards you discarded
	 * @param discardedCards The collection of cards to be discarded
	 * @return Whether the action was successful on the server
	 */
	public boolean discardCards(ResourceHand discardedCards);
	
	/**
	 * Tells the server which number the player rolled
	 * @param number Tells the server that you rolled this number
	 * @return Whether the roll was prcessed on the server correctly
	 */
	public boolean rollNumber(int number);
	
	/**
	 * Places a road on the map
	 * @param free Whether the piece was free of cost
	 * @param roadLocation The new location for the road
	 * @return Whether the road was placed
	 */
	public boolean buildRoad(boolean free, EdgeLocation roadLocation);

	/**
	 * 
	 * Builds a new settlement on the map
	 * @param free Whether the settlement was free of cost
	 * @param vertexLocation The new location of the settlement
	 * @return Whether the settlement was built
	 */
	public boolean buildSettlement(boolean free, VertexLocation vertexLocation);

	/**
	 * Builds a new city on the map
	 * @param vertexLocation The location of the city to be built
	 * @return Whether the city was built
	 */
	public boolean buildCity(VertexLocation vertexLocation);
	
	/**
	 * Offers a trade from one player to the other for resources
	 * @param offer The cards to be offered in a trade
	 * @param receiver The player with which this player wants to trade
	 * @return Whether the offer was communicated correctly
	 */
	public boolean offerTrade(ResourceHand offer, playerIndex receiver);
	
	/**
	 * Performs a maritme/ocean trade of resources
	 * @param ratio What the exchange rate ratio is
	 * @param inputResource What resoucre you are giving
	 * @param outputResource What resource you are getting
	 * @return Whether the maritime trade was successful
	 */
	public boolean maritimeTrade(int ratio, Resource inputResource, Resource outputResource);
	
	/**
	 * Steals a card from a player
	 * @param location The new location of the robber
	 * @param victimIndex The playerIndex of the person from which the card will be stolen
	 * @return Whether the thievery was successful
	 */
	public boolean robPlayer(HexLocation location, playerIndex victimIndex);
	
	/**
	 * Tells the server that this player has finished his turn
	 * @return Whether the communication was successful
	 */
	public boolean finishTurn();

	/**
	 * Attempts to buy a develpoment card
	 * @return Whether the action was successful
	 */
	public boolean buyDevCard();

	/**
	 * A Soldier card is played
	 * @param location The new robber location
	 * @param victimIndex The player from which you are stealing a card
	 * @return Whether the action was successful
	 */
	public boolean soldier(HexLocation location, playerIndex victimIndex);
	
	/**
	 * Plays the yearofplenty card
	 * @param resource1 the first resource you want to receive
	 * @param resource2 the second resource you want to receive
	 * @return Whether the action was successful, playing the card
	 */
	public boolean yearOfPlenty(Resource resource1, Resource resource2);
	
	/**
	 * Plays the RoadBuilding card
	 * @param spot1 The first spot that is connected to one of your roads
	 * @param spot2 The second road spot that is connected to on of your roads or the first spot
	 * @return Whether the RoadBilding card was played
	 */
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2);
	
	/**
	 * Plays the monopoly card
	 * @param resource The resource being taken from other players
	 * @return Whether the card was played successfully
	 */
	public boolean monopoly(Resource resource);
	
	/**
	 * Plays the monument card
	 * @return Whether the card was played
	 */
	public boolean monument();
	
}
