package client;

import java.util.List;

public class ClientModel {
	
	private ClientFacade clientFacade;
	private ResourceList bank;
	private MessageList chat;
	private MessageList log;
	private GameMap map;
	private Player[] players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;

	/**
	 * Default Constructor
	 * 
	 */
	public ClientModel() {
		bank = new ResourceList();
		chat = new MessageList();
		log = new MessageList();
		map = new GameMap();
		players = new Player[4];
		tradeOffer = new TradeOffer();
		turnTracker = new TurnTracker();
		version = 0;
		winner = -1;
	}

	/**
	 * Decrease the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to remove from the bank
	 * @pre The resource must exist and there must be at least `amount` amount of that resource in the bank
	 * @post There will be `amount` less of that resource in the bank
	 * @throws ClientException if this function runs and dies when it shouldn't
	 */
	public void removeResource(String resource, int amount) throws ClientException{}

	/**
	 * Increase the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to add to the bank
	 * @pre The resource must exist and there must be at least `amount` less than 19 of that resource in the bank
	 * @post There will be `amount` more of that resource in the bank
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addResource(String resource, int amount) throws ClientException {}

	/**
	 * Adds a MessageLine to the chat and log
	 * @param line: String, The line to be added to the chat/log
	 * @post The line will be added to both the chat and the log lists
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addChatMessage(MessageLine line) throws ClientException{}

	/**
	 * Sender makes a trade offer for any number of items to the receiver
	 * @param offer: List[Integer], positive numbers are resources being offered, negative are resources being asked for
	 * @param sender: int, The index of the sender in the player array
	 * @param receiver: int, The index of the receiver in the player array
	 * @return Whether the trade was successful.
	 * @pre The offer array must not be empty
	 * @pre All offered resources must be owned by the sender
	 * @pre All requested resources must be owned by the receiver
	 * @pre It must be the sender's turn
	 * @pre The sender and receiver numbers must map to a player in the player array
	 * @pre The sender &ne; receiver
	 * @post If the receiver accepts, then the positive resources will be given to the receiver and the negative will be 
	 * given to the sender
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public boolean offerTrade(List<Integer> offer, int sender, int receiver) throws ClientException{return false;}
	
	/**
	 * Switches to the next player; player 0 if currently on player 3
	 * @pre None
	 * @post Calls Player.endTurn(), updates the version and the if there is one. Updates the turn tracker with the status. Updates the winner if the player has won.
	 */
	public void endTurn() {
		
	}

	/**
	 * @return the bank
	 */
	public ResourceList getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(ResourceList bank) {
		this.bank = bank;
	}

	/**
	 * @return the chat
	 */
	public MessageList getChat() {
		return chat;
	}

	/**
	 * @param chat the chat to set
	 */
	public void setChat(MessageList chat) {
		this.chat = chat;
	}

	/**
	 * @return the log
	 */
	public MessageList getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(MessageList log) {
		this.log = log;
	}

	/**
	 * @return the map
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * @return the players
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}

	/**
	 * @return the tradeOffer
	 */
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}

	/**
	 * @param tradeOffer the tradeOffer to set
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	/**
	 * @return the turnTracker
	 */
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	/**
	 * @param turnTracker the turnTracker to set
	 */
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * Checks the model to see if the client can log in
	 * @pre None
	 * @post True if client can perform userLogin
	 * @return Whether the action is possible
	 */
	public boolean canUserLogin() {
		return false;
	}
	/**
	 * Checks the model to see if the client can register a new user
	 * @pre None
	 * @post True if client can perform userRegister
	 * @return Whether the action is possible
	 */
	public boolean canUserRegister() {
		return false;
	}
	/**
	 * Checks the model to see if the client can get a list of games
	 * @pre None
	 * @post True if client can perform gamesList
	 * @return Whether the action is possible
	 */
	public boolean canGamesList() {
		return false;
	}
	/**
	 * Checks the model to see if the client can create a game
	 * @pre None
	 * @post True if client can perform gamesCreate
	 * @return Whether the action is possible
	 */
	public boolean canGamesCreate() {
		return false;
	}
	/**
	 * Checks the model to see if the client can join a game
	 * @pre None
	 * @post True if client can perform gamesJoin
	 * @return Whether the action is possible
	 */
	public boolean canGamesJoin() {
		return false;
	}
	/**
	 * Checks the model to see if the client can save a game
	 * @pre None
	 * @post True if client can perform gamesSave
	 * @return Whether the action is possible
	 */
	public boolean canGamesSave() {
		return false;
	}
	/**
	 * Checks the model to see if the client can load a game
	 * @pre None
	 * @post True if client can perform gamesLoad
	 * @return Whether the action is possible
	 */
	public boolean canGamesLoad() {
		return false;
	}
	/**
	 * Checks the model to see if the client can get the current model ID
	 * @pre None
	 * @post True if client can perform gamesModel
	 * @return Whether the action is possible
	 */
	public boolean canGamesModel() {
		return false;
	}
	/**
	 * Checks the model to see if the client can reset a game
	 * @pre None
	 * @post True if client can perform gamesReset
	 * @return Whether the action is possible
	 */
	public boolean canGamesReset() {
		return false;
	}
	/**
	 * Checks the model to see if the client can get a list of commands executed for the game
	 * @pre None
	 * @post True if client can perform gamesCommandsGet
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommandsGet() {
		return false;
	}
	/**
	 * Checks the model to see if the client can post a list of command for the current game
	 * @pre None
	 * @post True if client can perform gamesCommandsPost
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommansPost() {
		return false;
	}
	/**
	 * Checks the model to see if the client can list AI players
	 * @pre None
	 * @post True if client can perform gamesListAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesListAI() {
		return false;
	}
	/**
	 * Checks the model to see if the client can add an AI
	 * @pre None
	 * @post True if client can perform gamesAddAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesAddAI() {
		return false;
	}
	/**
	 * Checks the model to see if the client can change the logging level
	 * @pre None
	 * @post True if client can perform utilChangeLogLevel
	 * @return Whether the action is possible
	 */
	public boolean canUtilChangeLogLevel() {
		return false;
	}
	/**
	 * Checks the model to see if the client can send a chat message
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canSendChat() {
		return false;
	}
	/**
	 * Checks the model to see if the client can accept a trade
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canAcceptTrade() {
		return false;
	}
	/**
	 * Checks the model to see if the client can discard cards
	 * @pre None
	 * @post True if client can perform discardCards
	 * @return Whether the action is possible
	 */
	public boolean canDiscardCards() {
		return false;
	}
	/**
	 * Checks the model to see if the client can roll
	 * @pre None
	 * @post True if client can perform rollNumber
	 * @return Whether the action is possible
	 */
	public boolean canRollNumber() {
		return false;
	}
	/**
	 * Checks the model to see if the client can build a road
	 * @pre None
	 * @post True if client can perform buildRoad
	 * @return Whether the action is possible
	 */
	public boolean canBuildRoad() {
		return false;
	}
	/**
	 * Checks the model to see if the client can build a settlement
	 * @pre None
	 * @post True if client can perform buildSettlement
	 * @return Whether the action is possible
	 */
	public boolean canBuildSettlement() {
		return false;
	}
	/**
	 * Checks the model to see if the client can build a city
	 * @pre None
	 * @post True if client can perform buildCity
	 * @return Whether the action is possible
	 */
	public boolean canBuildCity() {
		return false;
	}
	/**
	 * Checks the model to see if the client can offer a trade
	 * @pre None
	 * @post True if client can perform offerTrade
	 * @return Whether the action is possible
	 */
	public boolean canOfferTrade() {
		return false;
	}
	/**
	 * Checks the model to see if the client can perform a maritime trade
	 * @pre None
	 * @post True if client can perform maritimeTrade
	 * @return Whether the action is possible
	 */
	public boolean canMaritimeTrade() {
		return false;
	}
	/**
	 * Checks the model to see if the client can rob a player
	 * @pre None
	 * @post True if client can perform robPlayer
	 * @return Whether the action is possible
	 */
	public boolean canRobPlayer() {
		return false;
	}
	/**
	 * Checks the model to see if the client can finish their turn
	 * @pre None
	 * @post True if client can perform finishTurn
	 * @return Whether the action is possible
	 */
	public boolean canFinishTurn() {
		return false;
	}
	/**
	 * Checks the model to see if the client can buy a dev card
	 * @pre None
	 * @post True if client can perform buyDevCard
	 * @return Whether the action is possible
	 */
	public boolean canBuyDevCard() {
		return false;
	}
	/**
	 * Checks the model to see if the client can play a soldier
	 * @pre None
	 * @post True if client can perform soldier
	 * @return Whether the action is possible
	 */
	public boolean canSoldier() {
		return false;
	}
	/**
	 * Checks the model to see if the client can play a year of plenty card
	 * @pre None
	 * @post True if client can perform yearOfPlenty
	 * @return Whether the action is possible
	 */
	public boolean canYearOfPlenty() {
		return false;
	}
	/**
	 * Checks the model to see if the client can play a road building card
	 * @pre None
	 * @post True if client can perform roadBuilding
	 * @return Whether the action is possible
	 */
	public boolean canRoadBuilding() {
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monument card
	 * @pre None
	 * @post True if client can perform monument
	 * @return Whether the action is possible
	 */
	public boolean canMonument() {
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monopoly card
	 * @pre None
	 * @post True if client can perform monopoly
	 * @return Whether the action is possible
	 */
	public boolean canMonopoly() {
		return false;
	}
}
