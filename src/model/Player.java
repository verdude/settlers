package model;

import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import state.FirstRoundState;
import state.SecondRoundState;

import java.util.Random;

public class Player {
	
	private int cities; 				// Number of cities the player has left to play
	private int settlements;
	private CatanColor color;
	private boolean discarded; 			// Whether or not the player has discarded this discard phase
	private int monuments; 				// Number of monuments the player has played
	private String name;
	private DevCardList newDevCards; // New dev cards that the player has bought this turn
	private DevCardList oldDevCards; // Dev cards that the player had at the beginning of the turn
	private int playerIndex;
	private boolean playedDevCard;		// Whether or not a player has played a dev card this turn
	private int playerID;
	private ResourceList resources;
	public boolean getHasRolled() {
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
		newDevCards = new DevCardList(0);
		oldDevCards = new DevCardList(0);
		this.color = color;
		discarded = false;
		monuments = 0;
		this.name = name;
		this.playerIndex = playerIndex;
		playedDevCard = false;
		playerID = 0;
		resources = new ResourceList(ResourceList.min);
		roads = 15;
		soldiers = 0;
		victoryPoints = 0;
		hasRolled = false;
	}

	public Player(String name){

		cities = 4;
		settlements = 5;
		newDevCards = new DevCardList(0);
		oldDevCards = new DevCardList(0);
		this.color = null;
		discarded = false;
		monuments = 0;
		this.name = name;
		this.playerIndex = -1;
		playedDevCard = false;
		playerID = 0;
		resources = new ResourceList(ResourceList.min);
		roads = 15;
		soldiers = 0;
		victoryPoints = 0;
		hasRolled = false;
	}
	
	
	
	/** Simulates the rolling of two dice
	 * @return The number rolled (sum of both dice)
	 * @pre  canRollNumber() returns true 
	 * @post an int (1-12) with the same odds as that of rolling two dice
	 * @throws ClientException when the precondition is not met
	 */
	public int rollNumber() throws ClientException{
		//if(canRollNumber()){
			hasRolled = true;
			Random rand = new Random();
			int roll = rand.nextInt(6)+1;
			roll += rand.nextInt(6)+1;
			//TEMP
			roll = 7;
			//TEMP
			return roll;
		
	}
	
	
	
	/** Places a settlement for the player on a given vertexArray
	 * @pre canBuildSettlement() returns true
	 * @post a settlement will be placed at the desired vertex and settlements will be decremented by 1
	 * @throws ClientException when the precondition is not met
	 */
	public void playSettlement() throws ClientException{
			settlements--;
		boolean firstRounds = false;
		try {
			firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
					ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!firstRounds) {
			resources.setBrick(resources.getBrick() - 1);
			resources.setWood(resources.getWood() - 1);
			resources.setWheat(resources.getWheat() - 1);
			resources.setSheep(resources.getSheep() - 1);
		}

		
	}
	
	
	/** Places a city for the player on a given vertex
	 * @pre canBuildCity() returns true
	 * @post a city will be placed at the desired vertex
	 * @throws ClientException when the precondition is not met
	 */
	public void playCity() throws ClientException {
			settlements++;
			cities--;

			resources.setOre(resources.getOre() - 3);
			resources.setWheat(resources.getWheat() - 2);

	}
	
	
	/** Places a road for the player on a given edge
	 * @pre canBuildRoad() returns true
	 * @post a road will be placed at the desired edge
	 * @throws ClientException when the precondition is not met
	 */
	public void playRoad() throws ClientException{
			roads--;
		boolean firstRounds = false;
		try {
			firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
					ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!firstRounds) {
			resources.setBrick(resources.getBrick() - 1);
			resources.setWood(resources.getWood() - 1);
		}

		
	}
	
	
	
	
	/**
	 * Buys a dev card and places it in the players newDevCards list 
	 * @pre the resources (1 ore, 1 wool, 1 wheat) required for a dev card and at least one dev card in the bank
	 * @post a dev card will be added to the player's newDevCard list
	 * @throws ClientException when the precondition is not met
	 */
	public void buyDevCard() throws ClientException{

			resources.setOre(resources.getOre() -1 );
			resources.setWheat(resources.getWheat() -1 );
			resources.setSheep(resources.getSheep() -1 );

	}
	
	
	
	
	/** Plays a dev card for the player
	 * @param card dev card the player wants to play
	 * @pre the player has to have the dev card that he wants to play in oldDevCards
	 * @post the effects of the dev card take effect in the game
	 * @throws ClientException if the preconditions aren't met
	 */
	public void useDevCard(DevCardType card) throws ClientException{
		
			switch(card){
			case MONOPOLY:
				if (oldDevCards.getMonopoly() > 0) {
					oldDevCards.setMonopoly(oldDevCards.getMonopoly()+1);
				}
				break;
			case YEAR_OF_PLENTY:
				if (oldDevCards.getYearOfPlenty() > 0) {
					oldDevCards.setYearOfPlenty(oldDevCards.getYearOfPlenty()+1);
				}
				break;
			case SOLDIER:
				if (oldDevCards.getSoldier() > 0) {
					oldDevCards.setSoldier(oldDevCards.getSoldier()+1);
				}
				break;
			case ROAD_BUILD:
				if (oldDevCards.getRoadBuilding() > 0) {
					oldDevCards.setRoadBuilding(oldDevCards.getRoadBuilding()+1);
				}
				break;
			case MONUMENT:
				if (oldDevCards.getMonument() > 0) {
					oldDevCards.setMonument(oldDevCards.getMonument()+1);
				}
				break;
			default:
				throw new ClientException("Exception thrown in useDevCard");
			}
		}
	
	
	
	
	
	
	
	
	/**
	 * Ends the player's turn and
	 * @pre It's the player's turn
	 * @post moves newDevCards to oldDevCards. Checks to see if the player has won (10 vicotry points)
	 * @throws ClientException when the precondition is not met
	 */
	public void endTurn() throws ClientException{
			hasRolled = false;
			playedDevCard = false;
			oldDevCards.setMonopoly(oldDevCards.getMonopoly()+newDevCards.getMonopoly());
			oldDevCards.setSoldier(oldDevCards.getSoldier()+newDevCards.getSoldier());
			oldDevCards.setYearOfPlenty(oldDevCards.getYearOfPlenty()+newDevCards.getYearOfPlenty());
			oldDevCards.setRoadBuilding(oldDevCards.getRoadBuilding()+newDevCards.getRoadBuilding());
			oldDevCards.setMonument(oldDevCards.getMonument()+newDevCards.getMonument());
		
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
	public DevCardList getNewDevCards() {
		return newDevCards;
	}
	
	/**
	 * @param newDevCards newDevCards to set (List[DevCard])
	 */
	public void setNewDevCards(DevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}
	
	/**
	 * @return gets the list of dev cards owned by player not bought this turn
	 */
	public DevCardList getOldDevCards() {
		return oldDevCards;
	}
	
	/**
	 * @param oldDevCards oldDevCards to set (List[DevCard])
	 */
	public void setOldDevCards(DevCardList oldDevCards) {
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


		victoryPoints = (2 * (4 - cities)) + (5 - settlements) + monuments;

		return victoryPoints;
	}
	
	/**
	 * @param victoryPoints voctoryPoints to set (int)
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
	public boolean hasDevCard(DevCardType card){
		switch(card){
		case MONOPOLY:
			if (oldDevCards.getMonopoly() > 0) {
				return true;
			}
			break;
		case YEAR_OF_PLENTY:
			if (oldDevCards.getYearOfPlenty() > 0) {
				return true;
			}
			break;
		case SOLDIER:
			if (oldDevCards.getSoldier() > 0) {
				return true;
			}
			break;
		case ROAD_BUILD:
			if (oldDevCards.getRoadBuilding() > 0) {
				return true;
			}
			break;
		case MONUMENT:
			if (oldDevCards.getMonument() > 0) {
				return true;
			}
			break;
		}
		return false;
	}



}
