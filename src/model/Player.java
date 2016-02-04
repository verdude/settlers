package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;

public class Player {
	
	
	private int cities; // Number of cities the player has left to play
	private int settlements;
	private CatanColor color;
	private boolean discarded; // Whether or not the player has discarded this discard phase
	private int monuments; // Number of monuments the player has played
	private String name;
	private List<DevCard> newDevCards; // New dev cards that the player has bought this turn
	private List<DevCard> oldDevCards; // Dev cards that the player had at the beginning of the turn
	private int playerIndex;
	private boolean playedDevCard;// Whether or not a player has played a dev card this turn
	private int playerID;
	private ResourceList resources;
	public boolean isHasRolled() {
		return hasRolled;
	}

	public void setHasRolled(boolean hasRolled) {
		this.hasRolled = hasRolled;
	}

	private int roads;
	private int soldiers;
	private int victoryPoints;
	private boolean hasRolled;
	
	
	public Player(String name, CatanColor color, int playerIndex){
		
		cities = 4;
		settlements = 5;
		newDevCards = new ArrayList<DevCard>();
		oldDevCards = new ArrayList<DevCard>();
		this.color = color;
		discarded = false;
		monuments = 0;
		this.name = name;
		this.playerIndex = playerIndex;
		playedDevCard = false;
		playerID = 0;
		resources = new ResourceList(0);
		roads = 15;
		soldiers = 0;
		victoryPoints = 0;
		hasRolled = false;
	}
	
	/**Checks to see if a player can roll.
	 * It has to be the player's turn and they can only roll once per turn.
	 * @return true if the player can roll, false otherwise
	 * @pre none
	 * @post true if the player can roll, false otherwise
	 */
	public boolean canRollNumber(){
		
		if(!hasRolled){ // need to check if it is the player's turn as well
			return true;
		}else{
			return false;
		}
		
	}
	
	/** Simulates the rolling of two dice
	 * @return The number rolled (sum of both dice)
	 * @pre  canRollNumber() returns true 
	 * @post an int (1-12) with the same odds as that of rolling two dice
	 * @throws ClientException when the precondition is not met
	 */
	public int rollNumber() throws ClientException{
		if(canRollNumber()){
			hasRolled = true;
			Random rand = new Random();
			int roll = rand.nextInt(6)+1;
			roll += rand.nextInt(6)+1;
			return roll;
		}else{
			throw new ClientException();
		}
		
		
	}
	
	/**
	 * Checks if a player can play a settlement.
	 * The player has to have the resources (1 wheat, 1 brick, 1 lumber, 1 sheep) for a settlement
	 * and the vertex has to be connected to a player's road and at least 2 edges away from another settlement or city.
	 * And the player has a settlement left.
	 * It also has to be the player's turn
	 * @param vertex The object on the vertex
	 * @return true if the player can play a settlement, false otherwise
	 * @pre none
	 * @post whether or not a player can play a settlement
	 */
	public boolean canPlaySettlement(){
		if(settlements > 0 	&& resources.getWheat() >= 1 && resources.getSheep() >= 1
				&& resources.getBrick() >= 1 && resources.getWood() >= 1){ 
			return true;
			
		}else{
			return false;
		}
	}
	
	/** Places a settlement for the player on a given vertex
	 * @param vertex the vertex where the player wants to place the settlement
	 * @pre canPlaySettlement() returns true
	 * @post a settlement will be placed at the desired vertex and settlements will be decremented by 1
	 * @throws ClientException when the precondition is not met
	 */
	public void playSettlement()throws ClientException{
		if(canPlaySettlement()){
			settlements--;
			//vertex.setOwner(this.playerIndex);
			resources.setBrick(resources.getBrick() - 1);
			resources.setWood(resources.getWood() - 1);
			resources.setWheat(resources.getWheat() - 1);
			resources.setSheep(resources.getSheep() - 1);

			
		}else{
			throw new ClientException();
		}
	}
	
	/**
	 * Checks if a player can play a city.
	 * The player has to have the resources (2 wheat, 3 ore) for a city 
	 * and the vertex has to be connected to a player's road 
	 * and at least 2 edges away from another settlement or city. 
	 * And the player has a city left.
	 * @return true if the player can play a city, false otherwise
	 * @pre none
	 * @post whether or not a player can play a city
	 */
	public boolean canPlayCity(){
		if(cities > 0 && resources.getOre() >= 3 && resources.getWheat() >= 2){
			return true;
		}else{
			return false;
		}
	}
	/** Places a city for the player on a given vertex
	 * @param vertex the vertex where the player wants to place the city
	 * @pre canPlayCity() returns true
	 * @post a city will be placed at the desired vertex
	 */
	public void playCity() throws ClientException {
		if(canPlayCity()){
			settlements++;
			cities--;
			resources.setOre(resources.getOre() - 3);
			resources.setWheat(resources.getWheat() - 2);
			// how do we determine if the vertex has a city or a settlement?
			
		}else{
			throw new ClientException();
		}
	}
	
	/** The player has to have the resources (1 lumber, 1 brick) for a road 
	 * and the edge has to be connected to a player's road or municipality 
	 * and the edge must not be occupied by another piece. 
	 * And the player has a road left.
	 * @return true if the player can play a road, false otherwise
	 * @pre none
	 * @post whether or not a player can play a road
	 */
	public boolean canPlayRoad(){
		if(resources.getBrick() >= 1 && resources.getWood() >= 1
				 && roads > 0){
			// have to see if it's the player's turn
			return true;
		}else{
			return false;
		}
	}
	
	/** Places a road for the player on a given edge
	 * @param edge the edge where the player wants to place a road
	 * @pre canPlayRoad() returns true
	 * @post a road will be placed at the desired edge
	 * @throws ClientException if the precondition isn't met
	 */
	public void playRoad() throws ClientException{
		if(canPlayRoad()){
			roads--;
			resources.setBrick(resources.getBrick() - 1);
			resources.setWood(resources.getWood() - 1);
//			edge.setOwner(this.playerIndex);
			
		}else{
			throw new ClientException();
		}
		
	}
	
	
	/**
	 * Checks to see if a player can buy a dev card.
	 * If the player has the resources (1 ore, 1 sheep, 1 wheat) required for a dev card 
	 * and there is at least one dev card in the bank
	 * @return true if the player can buy a dev card, false otherwise
	 * @pre none
	 * @post true if the player can buy a dev card, false otherwise
	 */
	public boolean canBuyDevCard(){
		if(resources.getOre() > 0 && resources.getSheep() > 0 && resources.getWheat() > 0){
			//How can I check if there is a dev card in the bank?
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * Buys a dev card and places it in the players newDevCards list 
	 * @pre the resources (1 ore, 1 wool, 1 wheat) required for a dev card and at least one dev card in the bank
	 * @post a dev card will be added to the player's newDevCard list
	 * @throws ClientException
	 */
	public void buyDevCard() throws ClientException{
		if(canBuyDevCard()){
			//add devCard to newDevCards
			resources.setOre(resources.getOre() -1 );
			resources.setWheat(resources.getWheat() -1 );
			resources.setSheep(resources.getSheep() -1 );


			
		}else{
			throw new ClientException();
		}
	}
	
	
	/**
	 * @return true if the player can use a dev card, false otherwise
	 * @param the dev card the player wants to play
	 * @pre none
	 * @post true if the player can use a dev card, false otherwise
	 */
	public boolean canUseDevCard(DevCard card){
		if(!playedDevCard){
			for(DevCard tempCard : oldDevCards){ //Go through old devCards and see if you have the dev card in there
				if(tempCard.equals(card)){
					return true;
				}
			}
			return false;
		}else{
			return false;
		}
	}
	
	/** Plays a dev card for the player
	 * @param card dev card the player wants to play
	 * @pre the player has to have the dev card that he wants to play in oldDevCards
	 * @post the effects of the dev card take effect in the game
	 * @throws ClientException if the preconditions aren't met
	 */
	public void useDevCard(DevCard card) throws ClientException{
		if(canUseDevCard(card)){
			for(int i = 0; i < oldDevCards.size(); i++){ 
				if(oldDevCards.get(i).equals(card)){
					oldDevCards.remove(i); // Get rid of the Dev Card
					// How can I tell what type of dev card it is?
					
				}
			}
		}
		else{
			throw new ClientException();
		}
	}
	
	/**
	 * Sees if the player can or cannot offer the trade.
	 * @return true if the trade can be offered, false otherwise
	 * @pre None
	 * @post true if the trade can be offered, false otherwise
	 */
	public boolean canOfferTrade(TradeOffer offer){
		
		return false;
	}
	
	/** Offers a trade to another player
	 * @param offer Trade that is offered (TradeOffer)
	 * @pre the player has to have the resources that they want to offer
	 * @post the trade is offered to another player
	 * @throws ClientException If the function runs, but the trade cannot be offered.
	 */
	public void offerTrade(TradeOffer offer) throws ClientException{
		//I need to know more about how Resources is going to be implemented to make this work
		if(canOfferTrade(offer)){
			
		}else{
			throw new ClientException();
		}
	}
	
//	public enum ResourceBarElement
//	{
//		WOOD, BRICK, SHEEP, WHEAT, ORE, ROAD, SETTLEMENT, CITY, BUY_CARD, PLAY_CARD, SOLDIERS
//	}
	/**
	 * @param offer trade that is being decided (TradeOffer)
	 * @return true if the trade can be offered, false otherwise
	 * @pre None
	 * @post true if the trade can be offered, false otherwise
	 */
	public boolean canDecideTrade(TradeOffer offer){
		if(offer.getReceiver() == this.playerIndex){
			List<Integer> offeredResources = new ArrayList<Integer>();
			List<Integer> requestedResources = new ArrayList<Integer>();
			
			for(Integer i : offer.getOffer()){// Sort resources offered and those being requested from the offer
				if(i > 0){
					offeredResources.add(i);
				}else{
					requestedResources.add(i);
				}
			}
			 
			//needs more implementation, How to tell what numbers are what resource
			for(Integer i : requestedResources){
				
			}
			

			return true;
		}else{
			return false;
		}
	}
	
	/** The player chooses whether or not to accept an offered trade 
	 * @param offer the offer that has been given (TradeOffer)
	 * @return true if trade is accepted, false otherwise
	 * @pre the player has to have the desired resources
	 * @post the trade is either accepted or denied
	 * @throws ClientException when the preconditions aren't met
	 */
	public boolean decideTrade(TradeOffer offer) throws ClientException{
		if(canDecideTrade(offer)){
			return false;
		}else{
			throw new ClientException();
		}
	}
		
	/**
	 * Checks to see if the player can end his turn.
	 * @return true if the player can end his turn, false otherwise
	 * @pre None
	 * @post true if the player can end his turn, false otherwise
	 */
	public boolean canFinishTurn() {
		//It just has to be the player's turn which is already assumed to have been checked
		return true;
	}
	
	/**
	 * Ends the player's turn and
	 * @pre It's the player's turn
	 * @post moves newDevCards to oldDevCards. Checks to see if the player has won (10 vicotry points)
	 * @throws ClientException If this function tries to run when the player can't end his turn. 
	 */
	public void endTurn() throws ClientException{
		if(canFinishTurn()){
			hasRolled = false;
			playedDevCard = false;
			for(DevCard card : newDevCards){ // move bought dev cards to the usable list of dev cards (oldDevCards)
				oldDevCards.add(card);
			}
			
		}else{
			throw new ClientException();
		}
	}


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
	public CatanColor getColor() {
		return color;
	}
	
	/**
	 * @param color color to set the player to
	 */
	public void setColor(CatanColor color) {
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
	public List<DevCard> getNewDevCards() {
		return newDevCards;
	}
	
	/**
	 * @param newDevCards newDevCards to set (List[DevCard])
	 */
	public void setNewDevCards(List<DevCard> newDevCards) {
		this.newDevCards = newDevCards;
	}
	
	/**
	 * @return gets the list of dev cards owned by player not bought this turn
	 */
	public List<DevCard> getOldDevCards() {
		return oldDevCards;
	}
	
	/**
	 * @param oldDevCards oldDevCards to set (List[DevCard])
	 */
	public void setOldDevCards(List<DevCard> oldDevCards) {
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
	public ResourceList getResources() {
		return resources;
	}
	
	/**
	 * @param resources resources to set (List[Card])
	 */
	public void setResources(ResourceList resources) {
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
