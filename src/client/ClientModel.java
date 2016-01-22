package client;

import java.util.List;

public class ClientModel {
	
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
	 */
	public void removeResource(String resource, int amount) {}

	/**
	 * Increase the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to add to the bank
	 * @pre The resource must exist and there must be at least `amount` less than 19 of that resource in the bank
	 * @post There will be `amount` more of that resource in the bank
	 */
	public void addResource(String resource, int amount) {}

	/**
	 * Adds a MessageLine to the chat and log
	 * @param line: String, The line to be added to the chat/log
	 * @post The line will be added to both the chat and the log lists
	 */
	public void addChatMessage(MessageLine line){}

	/**
	 * Sender makes a trade offer for any number of items to the receiver
	 * @param offer: List<Integer>, posotive numbers are resources being offered, negative are resources being asked for
	 * @param sender: int, The index of the sender in the player array
	 * @param reveiver: int, The index of the receiver in the player array
	 * @pre The offer array must not be empty
	 * @pre All offered resources must be owned by the sender
	 * @pre All requested resources must be owned by the receiver
	 * @pre It must be the sender's turn
	 * @pre The sender and reveiver numbers must map to a player in the player array
	 * @pre The sender &ne; receiver
	 * @post If the receiver accepts, then the posotive resources will be given to the receiver and the negative will be 
	 * given to the sender
	 */
	public List<TradeOffer> offerTrade(List<Integer> offer, int sender, int receiver) {return null;}
	
	/**
	 * Switches to the next player; player 0 if currently on player 3
	 * @pre None
	 * @post Calls Player.endTurn(), updates the version and the if there is one. Updates the turn tracker with the status
	 */
	public int endTurn() {return 0;}

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
	
}
