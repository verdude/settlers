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
	 * Asks the server if the client can log in
	 * @pre None
	 * @post True if client can perform userLogin
	 * @return Whether the action is possible
	 */
	public boolean canUserLogin();
	/**
	 * Logs the caller in to the server, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is known and logged in to the server.
	 * @return True if the log in was successful
	 */
	public boolean userLogin(String username, String password);

	/**
	 * Asks the server if the client can register a new user
	 * @pre None
	 * @post True if client can perform userRegister
	 * @return Whether the action is possible
	 */
	public boolean canUserRegister();
	/**
	 * 1) Creates a new user account
	 * 2) Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie
	 * @param username The Username
	 * @param password The Password
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user now exists in the database of users and can log in and perform anything a user can.
	 * @return True if the registration and log in were successful
	 */
	public boolean userRegister(String username, String password);

	/**
	 * Asks the server if the client can get a list of games
	 * @pre None
	 * @post True if client can perform gamesList
	 * @return Whether the action is possible
	 */
	public boolean canGamesList();
	/**
	 * This asks the server for a list of the games
	 * @pre The corresponding "canDo" method returns true.
	 * @post A json containing all of the current games is returned
	 * @return The list of games in json form
	 */
	public String gamesList();

	/**
	 * Asks the server if the client can create a game
	 * @pre None
	 * @post True if client can perform gamesCreate
	 * @return Whether the action is possible
	 */
	public boolean canGamesCreate();
	/**
	 * This method creates a new game in the server called by the name given
	 * @param name The name of the game to be created
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game is created and can now be joined and referenced by the name passed in.
	 * @return True if the game was successfully created
	 */
	public boolean gamesCreate(String name);

	/**
	 * Asks the server if the client can join a game
	 * @pre None
	 * @post True if client can perform gamesJoin
	 * @return Whether the action is possible
	 */
	public boolean canGamesJoin();
	/**
	 * Joins a game of the given name on the server
	 * @param name Name of the game to join
	 * @pre The corresponding "canDo" method returns true.
	 * @post The user is added to the game que and all other game components as a player.
	 * @return Whether the join was successful
	 */
	public boolean gamesJoin(String name);	

	/**
	 * Asks the server if the client can save a game
	 * @pre None
	 * @post True if client can perform gamesSave
	 * @return Whether the action is possible
	 */
	public boolean canGamesSave();
	/**
	 * 
	 * This method is for testing and debugging purposes. 
	 * Game files are saved to the server's saves/ directory.
	 * @param name Name of the game to save
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state now exists on the server and can be loaded
	 * @return Whether the save was successful
	 */
	public boolean gamesSave(String name);

	/**
	 * Asks the server if the client can load a game
	 * @pre None
	 * @post True if client can perform gamesLoad
	 * @return Whether the action is possible
	 */
	public boolean canGamesLoad();
	/**
	 * This method is for testing and debugging purposes. 
	 * Game files are loaded from the server's saves/ directory.
	 * @param name Name of the game to load
	 * @pre The corresponding "canDo" method returns true.
	 * @post A game state from the server is cloned and put into action.
	 * @return Whether the load was successful
	 */
	public boolean gamesLoad(String name);

	/**
	 * Asks the server if the client can get the current model ID
	 * @pre None
	 * @post True if client can perform gamesModel
	 * @return Whether the action is possible
	 */
	public boolean canGamesModel();
	/**
	 * Returns the current state of the game in JSON format.
	 * @param version The version number from the previously retrieved model
	 * @pre The corresponding "canDo" method returns true.
	 * @post The server's model of the current state of the game is cloned and set as the current game model locally.
	 * @return The current Model ID on the server
	 */
	public String gamesModel(String version);

	/**
	 * Asks the server if the client can reset a game
	 * @pre None
	 * @post True if client can perform gamesReset
	 * @return Whether the action is possible
	 */
	public boolean canGamesReset();
	/**
	 * Clears out the command history of the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post The current game is reverted back to the equivalent that no commands had been called.
	 * @return Whether the reset was successful
	 */
	public boolean gamesReset();

	/**
	 * Asks the server if the client can get a list of commands executed for the game
	 * @pre None
	 * @post True if client can perform gamesCommandsGet
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommandsGet();
	/**
	 * Returns a list of commands that have been executed in the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post A list of all the commands that have been executed is returned.
	 * @return The list of commands
	 */
	public String gamesCommandsGet();

	/**
	 * Asks the server if the client can post a list of command for the current game
	 * @pre None
	 * @post True if client can perform gamesCommandsPost
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommansPost();
	/**
	 * Executes the specified command list in the current game
	 * @param commandList The list of commands to be executed on the server in the current game
	 * @pre The corresponding "canDo" method returns true.
	 * @post The list of commands that is passed in are executed in order on the server.
	 * @return Whether the list of commands successfully executed
	 */
	public boolean gamesCommandsPost(String commandList);

	/**
	 * Asks the server if the client can list AI players
	 * @pre None
	 * @post True if client can perform gamesListAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesListAI();
	/**
	 * The body contains a JSON string array enumerating the different types of AI players.
	 * These are the values that may be passed to the /game/addAI method.
	 * @pre The corresponding "canDo" method returns true.
	 * @post A list of the AI players on the server is returned.
	 * @return Returns a list of supported AI player types
	 */
	public String gamesListAI();

	/**
	 * Asks the server if the client can add an AI
	 * @pre None
	 * @post True if client can perform gamesAddAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesAddAI();
	/**
	 * Adds an AI player to the current game.
	 * @pre The corresponding "canDo" method returns true.
	 * @post An AI player is appended to the list of AI players found on the server and will be in the AI list.
	 * @return Whether the AI player was added
	 */
	public boolean gamesAddAI();

	/**
	 * Asks the server if the client can change the logging level
	 * @pre None
	 * @post True if client can perform utilChangeLogLevel
	 * @return Whether the action is possible
	 */
	public boolean canUtilChangeLogLevel();
	/**
	 * Sets the server’s logging level.
	 * @param logLevel The loglevel to set the server to
	 * @pre The corresponding "canDo" method returns true.
	 * @post The logging level on the server is set equal to logLevel
	 * @return Whether the logLevel was set in the server
	 */
	public boolean utilChangeLogLevel(String logLevel);

	/**
	 * Asks the server if the client can send a chat message
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canSendChat();
	/**
	 * Sends a message to the other players
	 * @param message The message you want to send
	 * @pre The corresponding "canDo" method returns true.
	 * @post A message is sent to the other players.
	 * @return Whether the message was sent
	 */
	public boolean sendChat(String message);

	/**
	 * Asks the server if the client can accept a trade
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canAcceptTrade();
	/**
	 * Accept a trade that has been presented
	 * @param willAccept Whether the player accepted the trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post The trade presented is executed if willAccept is true, not executed otherwise.
	 * @return Whether the trade was accepted or not on the server
	 */
	public boolean acceptTrade(boolean willAccept);

	/**
	 * Asks the server if the client can discard cards
	 * @pre None
	 * @post True if client can perform discardCards
	 * @return Whether the action is possible
	 */
	public boolean canDiscardCards();
	/**
	 * Tells the server which cards you discarded
	 * @param discardedCards The collection of cards to be discarded
	 * @pre The corresponding "canDo" method returns true.
	 * @post discardedCards is 1 card bigger and contains the card that is no longer in the player's possession, as it was discarded.
	 * @return Whether the action was successful on the server
	 */
	public boolean discardCards(ResourceHand discardedCards);

	/**
	 * Asks the server if the client can roll
	 * @pre None
	 * @post True if client can perform rollNumber
	 * @return Whether the action is possible
	 */
	public boolean canRollNumber();
	/**
	 * Tells the server which number the player rolled
	 * @param number Tells the server that you rolled this number
	 * @pre The corresponding "canDo" method returns true.
	 * @post The result of rolling the current number is performed.
	 * @return Whether the roll was prcessed on the server correctly
	 */
	public boolean rollNumber(int number);

	/**
	 * Asks the server if the client can build a road
	 * @pre None
	 * @post True if client can perform buildRoad
	 * @return Whether the action is possible
	 */
	public boolean canBuildRoad();
	/**
	 * Places a road on the map
	 * @param free Whether the piece was free of cost
	 * @param roadLocation The new location for the road
	 * @pre The corresponding "canDo" method returns true.
	 * @post A road is placed on the roadLocation if free is true as well. Otherwise, no road was placed.
	 * @return Whether the road was placed
	 */
	public boolean buildRoad(boolean free, EdgeLocation roadLocation);

	/**
	 * Asks the server if the client can build a settlement
	 * @pre None
	 * @post True if client can perform buildSettlement
	 * @return Whether the action is possible
	 */
	public boolean canBuildSettlement();
	/**
	 * 
	 * Builds a new settlement on the map
	 * @param free Whether the settlement was free of cost
	 * @param vertexLocation The new location of the settlement
	 * @pre The corresponding "canDo" method returns true.
	 * @post A settlement is built on vertexLocation if free is true. Otherwise, it is not built.
	 * @return Whether the settlement was built
	 */
	public boolean buildSettlement(boolean free, VertexLocation vertexLocation);

	/**
	 * Asks the server if the client can build a city
	 * @pre None
	 * @post True if client can perform buildCity
	 * @return Whether the action is possible
	 */
	public boolean canBuildCity();
	/**
	 * Builds a new city on the map
	 * @param vertexLocation The location of the city to be built
	 * @pre The corresponding "canDo" method returns true.
	 * @post A city is built on vertexLocation if free is true. Otherwise, it is not built.
	 * @return Whether the city was built
	 */
	public boolean buildCity(VertexLocation vertexLocation);

	/**
	 * Asks the server if the client can offer a trade
	 * @pre None
	 * @post True if client can perform offerTrade
	 * @return Whether the action is possible
	 */
	public boolean canOfferTrade();
	/**
	 * Offers a trade from one player to the other for resources
	 * @param offer The cards to be offered in a trade
	 * @param receiver The player with which this player wants to trade
	 * @pre The corresponding "canDo" method returns true.
	 * @post A trade aggreement is presented to the player corresponding to receiver. The trade is made if receiver accepts the trade. The receiver receives offer and the offering player receives the counter part of the trade.
	 * @return Whether the offer was communicated correctly
	 */
	public boolean offerTrade(ResourceHand offer, playerIndex receiver);

	/**
	 * Asks the server if the client can perform a maritime trade
	 * @pre None
	 * @post True if client can perform maritimeTrade
	 * @return Whether the action is possible
	 */
	public boolean canMaritimeTrade();
	/**
	 * Performs a maritme/ocean trade of resources
	 * @param ratio What the exchange rate ratio is
	 * @param inputResource What resoucre you are giving
	 * @param outputResource What resource you are getting
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player is less inputResource and more outputResource according to the ratio.
	 * @return Whether the maritime trade was successful
	 */
	public boolean maritimeTrade(int ratio, Resource inputResource, Resource outputResource);

	/**
	 * Asks the server if the client can rob a player
	 * @pre None
	 * @post True if client can perform robPlayer
	 * @return Whether the action is possible
	 */
	public boolean canRobPlayer();
	/**
	 * Steals a card from a player
	 * @param location The new location of the robber
	 * @param victimIndex The playerIndex of the person from which the card will be stolen
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber's new location is location and the player at victimIndex is less one card, and that card is given to the robbing player.
	 * @return Whether the thievery was successful
	 */
	public boolean robPlayer(HexLocation location, playerIndex victimIndex);

	/**
	 * Asks the server if the client can finish their turn
	 * @pre None
	 * @post True if client can perform finishTurn
	 * @return Whether the action is possible
	 */
	public boolean canFinishTurn();
	/**
	 * Tells the server that this player has finished his turn
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player can no longer perform and turn-related actions and the next player in the queue receives the currentTurn that the finishing player lost.
	 * @return Whether the communication was successful
	 */
	public boolean finishTurn();

	/**
	 * Asks the server if the client can buy a dev card
	 * @pre None
	 * @post True if client can perform buyDevCard
	 * @return Whether the action is possible
	 */
	public boolean canBuyDevCard();
	/**
	 * At@pre Nonets to buy a develpoment card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The players resources are given to the bank in the amount required for a dev card. The player receives a dev card.
	 * @return Whether the action was successful
	 */
	public boolean buyDevCard();

	/**
	 * Asks the server if the client can play a soldier
	 * @pre None
	 * @post True if client can perform soldier
	 * @return Whether the action is possible
	 */
	public boolean canSoldier();
	/**
	 * A Soldier card is played
	 * @param location The new robber location
	 * @param victimIndex The player from which you are stealing a card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The robber is placed in location, receiving that as a new location, and the victimIndex-player is less one card which is added to the player's hand who played the soldier card.
	 * @return Whether the action was successful
	 */
	public boolean soldier(HexLocation location, playerIndex victimIndex);

	/**
	 * Asks the server if the client can play a year of plenty card
	 * @pre None
	 * @post True if client can perform yearOfPlenty
	 * @return Whether the action is possible
	 */
	public boolean canYearOfPlenty();
	/**
	 * Plays the yearofplenty card
	 * @param resource1 the first resource you want to receive
	 * @param resource2 the second resource you want to receive
	 * @pre The corresponding "canDo" method returns true.
	 * @post You receive resource1 and resource2 of your choice. (Check rules)
	 * @return Whether the action was successful, playing the card
	 */
	public boolean yearOfPlenty(Resource resource1, Resource resource2);

	/**
	 * Asks the server if the client can play a road building card
	 * @pre None
	 * @post True if client can perform roadBuilding
	 * @return Whether the action is possible
	 */
	public boolean canRoadBuilding();
	/**
	 * Plays the RoadBuilding card
	 * @param spot1 The first spot that is connected to one of your roads
	 * @param spot2 The second road spot that is connected to on of your roads or the first spot
	 * @pre The corresponding "canDo" method returns true.
	 * @post Two roads are placed that belong to the corresponding player. They are placed correctly.
	 * @return Whether the RoadBilding card was played
	 */
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2);

	/**
	 * Asks the server if the client can play a monopoly card
	 * @pre None
	 * @post True if client can perform monopoly
	 * @return Whether the action is possible
	 */
	public boolean canMonopoly();
	/**
	 * Plays the monopoly card
	 * @param resource The resource being taken from other players
	 * @pre The corresponding "canDo" method returns true.
	 * @post The player receives resource from every one of the players. They no longer have the corresponding resource and the year of plenty-playing player has their cards. (Check rules)
	 * @return Whether the card was played successfully
	 */
	public boolean monopoly(Resource resource);

	/**
	 * Asks the server if the client can play a monument card
	 * @pre None
	 * @post True if client can perform monument
	 * @return Whether the action is possible
	 */
	public boolean canMonument();
	/**
	 * Plays the monument card
	 * @pre The corresponding "canDo" method returns true.
	 * @post The monument card is played for the corresponding player.
	 * @return Whether the card was played
	 */
	public boolean monument();
	
}
