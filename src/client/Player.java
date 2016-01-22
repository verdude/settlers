package client;

import java.util.List;


public class Player {
	
	
	private int cities; // Number of cities the player has left to play
	private String color;
	private boolean discarded; // Whether or not the player has discarded this discard phase
	private int monuments; // Number of monuments the player has played
	private String name;
	private List<Card> newDevCards; // New dev cards that the player has bought this turn
	private List<Card> oldDevCards; // Dev cards that the player had at the beginning of the turn
	private int playerIndex;
	private boolean playedDevCard;// Whether or not a player has played a dev card this turn
	private int playerID;
	private List<Card> resources;
	private int roads;
	private int settlements;
	private int soldiers;
	private int victoryPoints;
	
	
	
	
	/** Simulates the rolling of two dice
	 * 
	 * @pre  It has to be the beginning of the player's turn
	 * @post an int (1-12) with the same odds as that of rolling two dice
	 */
	public int rollNumber(){
		return 0;
	}
	
	/** Places a settlement for the player on a given vertex
	 * @param vertex the vertex where the player wants to place the settlement
	 * @pre The player has to have the resources (1 wheat, 1 brick, 1 lumber, 1 sheep) for a settlement and the vertex has to be connected to a player's road and at least 2 edges away from another settlement or city. And the player has a settlement left.
	 * @post a settlement will be placed at the desired vertex
	 */
	public void playSettlement(VertexObject vertex){}
	
	/** Places a city for the player on a given vertex
	 * @param vertex the vertex where the player wants to place the city
	 * @pre The player has to have the resources (2 wheat, 3 ore) for a city and the vertex has to be connected to a player's road and at least 2 edges away from another settlement or city. And the player has a city left.
	 * @post a city will be placed at the desired vertex
	 */
	public void playCity(VertexObject vertex){}
	
	/** Places a road for the player on a given edge
	 * @param edge the edge where the player wants to place a road
	 * @pre The player has to have the resources (1 lumber, 1 brick) for a road and the edge has to be connected to a player's road or municipality and the edge must not be occupied by another piece. And the player has a road left.
	 * @post a road will be placed at the desired edge
	 */
	public void playRoad(EdgeValue edge){}
	
	/**
	 * Buys a dev card and places it in the players newDevCards list 
	 * @pre the resources (1 ore, 1 wool, 1 wheat) required for a dev card and at least one dev card in the bank
	 * @post a dev card will be added to the player's newDevCard list
	 */
	public void buyDevCard(){}
	
	/** Plays a dev card for the player
	 * @param card dev card the player wants to play
	 * @pre the player has to have the dev card that he wants to play in oldDevCards
	 */
	public void useDevCard(Card card){}
	
	/** Offers a trade to another player
	 * @param offer Trade that is offered (TradeOffer)
	 * @pre the player has to have the resources that they want to offer
	 * @post the trade is offered to another player
	 */
	public void offerTrade(TradeOffer offer){}
	
	/** The player chooses whether or not to accept an offered trade 
	 * @param offer the offer that has been given (TradeOffer)
	 * @return true if trade is accepted, false otherwise
	 * @pre the player has to have the desired resources
	 * @post the trade is either accepted or denied
	 */
	public boolean decideTrade(TradeOffer offer){
		return false;
	}
	
	/**
	 * Ends the player's turn and
	 * @pre It's the player's turn
	 * @post moves newDevCards to oldDevCards. Checks to see if the player has won (10 vicotry points)
	 */
	public void endTurn(){}


	/**
	 * @return gets the number of cities that the player has left to play
	 */
	public int getCities() {
		return cities;
	}
	
	/**
	 * @param cities cities to set (int)
	 */
	public void setCities(int cities) {
		this.cities = cities;
	}
	
	/**
	 * @return the color of the player
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @param color color to set the player to
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * @return gets boolean that shows if the player has discarded this discard phase
	 */
	public boolean getDiscarded() {
		return discarded;
	}
	
	/**
	 * @param discarded discarded to set (boolean)
	 */
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}
	
	/**
	 * @return gets the number of monument cards played
	 */
	public int getMonuments() {
		return monuments;
	}
	
	/**
	 * @param monuments sets the number of monument cards played (int)
	 */
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}
	
	/**
	 * @return gets the name of the player (String)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name name to set (String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return gets the list of dev cards bought this turn
	 */
	public List<Card> getNewDevCards() {
		return newDevCards;
	}
	
	/**
	 * @param newDevCards newDevCards to set (List<Card>)
	 */
	public void setNewDevCards(List<Card> newDevCards) {
		this.newDevCards = newDevCards;
	}
	
	/**
	 * @return gets the list of dev cards owned by player not bought this turn
	 */
	public List<Card> getOldDevCards() {
		return oldDevCards;
	}
	
	/**
	 * @param oldDevCards oldDevCards to set (List<DevCard>)
	 */
	public void setOldDevCards(List<Card> oldDevCards) {
		this.oldDevCards = oldDevCards;
	}
	
	/**
	 * @return gets the index of the current player as stored in the player array (int from 0-3)
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	/**
	 * @param playerIndex playerIndex to set (int from 0-3)
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	/**
	 * @return gets the boolean that says if a player has played a dev card this turn
	 */
	public boolean getPlayedDevCard() {
		return playedDevCard;
	}
	
	/**
	 * @param playedDevCard playedDevCard to set (boolean)
	 */
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}
	
	/**
	 * @return gets the player ID that is for the server (int)
	 */
	public int getPlayerID() {
		return playerID;
	}
	
	/**
	 * @param playerID playerID to set (int)
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	/**
	 * @return gets the list of resource cards that the player has
	 */
	public List<Card> getResources() {
		return resources;
	}
	
	/**
	 * @param resources resources to set (List<Card>)
	 */
	public void setResources(List<Card> resources) {
		this.resources = resources;
	}
	
	/**
	 * @return the number of roads the player has left to play
	 */
	public int getRoads() {
		return roads;
	}
	/**
	 * @param roads roads to set (int)
	 */
	public void setRoads(int roads) {
		this.roads = roads;
	}
	
	/**
	 * @return the number of settlements the player has left to play
	 */
	public int getSettlements() {
		return settlements;
	}
	
	/**
	 * @param settlements settlements to set (int)
	 */
	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}
	
	/**
	 * @return number of soldiers the player has played (int)
	 */
	public int getSoldiers() {
		return soldiers;
	}
	
	/**
	 * @param soldiers soldiers to set (int)
	 */
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}
	
	/**
	 * @return the number of victory points the player has (int)
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}
	
	/**
	 * @param victoryPoints voctoryPoints to set (int)
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
