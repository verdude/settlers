package model;


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
		bank = new ResourceList(ResourceList.max);
		chat = new MessageList();
		log = new MessageList();
		map = new GameMap();
		players = new Player[4];
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
	public void removeResource(String resource, int amount) throws ClientException {
		resource = resource.toLowerCase();
		switch(resource){
			case "brick":
				bank.setBrick(bank.getBrick() - amount);
				if(bank.getBrick() < ResourceList.min) {
					bank.setBrick(ResourceList.min);
				}
				break;
			case "ore":
				bank.setOre(bank.getOre() - amount);
				if(bank.getOre() < ResourceList.min) {
					bank.setOre(ResourceList.min);
				}
				break;
			case "sheep":
				bank.setSheep(bank.getSheep() - amount);
				if(bank.getSheep() < ResourceList.min) {
					bank.setSheep(ResourceList.min);
				}
				break;
			case "wheat":
				bank.setWheat(bank.getWheat() - amount);
				if(bank.getWheat() < ResourceList.min) {
					bank.setWheat(ResourceList.min);
				}
				break;
			case "wood":
				bank.setWood(bank.getWood() - amount);
				if(bank.getWood() < ResourceList.min) {
					bank.setWood(ResourceList.min);
				}
				break;
			default:
				throw new ClientException("Exception thrown in removeResource");
		}
	}

	/**
	 * Increase the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to add to the bank
	 * @pre The resource must exist and there must be at least `amount` less than 19 of that resource in the bank
	 * @post There will be `amount` more of that resource in the bank
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addResource(String resource, int amount) throws ClientException {
		resource = resource.toLowerCase();
		switch(resource){
			case "brick":
				bank.setBrick(bank.getBrick() + amount);
				if(bank.getBrick() > ResourceList.max) {
					bank.setBrick(ResourceList.max);
				}
				break;
			case "ore":
				bank.setOre(bank.getOre() + amount);
				if(bank.getOre() > ResourceList.max) {
					bank.setOre(ResourceList.max);
				}
				break;
			case "sheep":
				bank.setSheep(bank.getSheep() + amount);
				if(bank.getSheep() > ResourceList.max) {
					bank.setSheep(ResourceList.max);
				}
				break;
			case "wheat":
				bank.setWheat(bank.getWheat() + amount);
				if(bank.getWheat() > ResourceList.max) {
					bank.setWheat(ResourceList.max);
				}
				break;
			case "wood":
				bank.setWood(bank.getWood() + amount);
				if(bank.getWood() > ResourceList.max) {
					bank.setWood(ResourceList.max);
				}
				break;
			default:
				throw new ClientException("Exception thrown in addResource");
		}
	}

	/**
	 * Adds a MessageLine to the chat and log
	 * @param line: String, The line to be added to the chat/log
	 * @post The line will be added to both the chat and the log lists
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addChatMessage(MessageLine line) throws ClientException {
		try {
			chat.addMessage(line);
			log.addMessage(line);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClientException();
		}
	}
	
	/**
	 * Switches to the next player; player 0 if currently on player 3
	 * @throws ClientException 
	 * @pre None
	 * @post Calls Player.endTurn(), updates the version and the if there is one. Updates the turn tracker with the status. Updates the winner if the player has won.
	 */
	public void endTurn() throws ClientException {
		int currentTurn = turnTracker.getCurrentTurn();
		players[currentTurn].endTurn();
		if(currentTurn + 1 > players.length) {
			turnTracker.setCurrentTurn(0);
		} else {
			turnTracker.setCurrentTurn(currentTurn + 1);
		}
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
	 * Checks the model to see if the client can send a chat message
	 * @pre None
	 * @post True if client can perform sendChat
	 * @return Whether the action is possible
	 */
	public boolean canSendChat() {
		//TO-DO
		return false;
	}	
	/**
	 * Checks the model to see if the client can accept a trade
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canAcceptTrade() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can discard cards
	 * @pre None
	 * @post True if client can perform discardCards
	 * @return Whether the action is possible
	 */
	public boolean canDiscardCards() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can build a road
	 * @pre None
	 * @post True if client can perform buildRoad
	 * @return Whether the action is possible
	 */
	public boolean canBuildRoad() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can build a settlement
	 * @pre None
	 * @post True if client can perform buildSettlement
	 * @return Whether the action is possible
	 */
	public boolean canBuildSettlement() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can build a city
	 * @pre None
	 * @post True if client can perform buildCity
	 * @return Whether the action is possible
	 */
	public boolean canBuildCity() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can offer a trade
	 * @pre None
	 * @post True if client can perform offerTrade
	 * @return Whether the action is possible
	 */
	public boolean canOfferTrade() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can perform a maritime trade
	 * @pre None
	 * @post True if client can perform maritimeTrade
	 * @return Whether the action is possible
	 */
	public boolean canMaritimeTrade() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can rob a player
	 * @pre None
	 * @post True if client can perform robPlayer
	 * @return Whether the action is possible
	 */
	public boolean canRobPlayer() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can finish their turn
	 * @pre None
	 * @post True if client can perform finishTurn
	 * @return Whether the action is possible
	 */
	public boolean canFinishTurn() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can play a soldier
	 * @pre None
	 * @post True if client can perform soldier
	 * @return Whether the action is possible
	 */
	public boolean canSoldier() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can play a year of plenty card
	 * @pre None
	 * @post True if client can perform yearOfPlenty
	 * @return Whether the action is possible
	 */
	public boolean canYearOfPlenty() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can play a road building card
	 * @pre None
	 * @post True if client can perform roadBuilding
	 * @return Whether the action is possible
	 */
	public boolean canRoadBuilding() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monument card
	 * @pre None
	 * @post True if client can perform monument
	 * @return Whether the action is possible
	 */
	public boolean canMonument() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monopoly card
	 * @pre None
	 * @post True if client can perform monopoly
	 * @return Whether the action is possible
	 */
	public boolean canMonopoly() {
		//TO-DO
		return false;
	}
}
