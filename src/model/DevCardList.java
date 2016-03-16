package model;


/**
 * @author santi
 * This is the bank for the Dev Cards.
 */
public class DevCardList {

	// Progress Cards
	private int monopoly;
	private int roadBuilding;
	private int yearOfPlenty;
	// Other Dev Cards
	private int monument;
	private int soldier;

	/**
	 * This is for the ServerModel
	 * Sets the progress cards initial value to 2.
	 * Sets the soldier variable to 14.
	 * Sets the monument (victory cards) to 5.
	 */
	public DevCardList() {
		monopoly = roadBuilding = yearOfPlenty = 2;
		soldier = 14;
		monument = 5;
	}
	//Constructor for a player so that they can start with an empty DevCardList
	public DevCardList(int startValue){
		
		this.setMonopoly(startValue);
		this.setMonument(startValue);
		this.setRoadBuilding(startValue);
		this.setSoldier(startValue);
		this.setYearOfPlenty(startValue);
	}

	/**
	 * @return the monopoly
	 */
	public int getMonopoly() {
		return monopoly;
	}

	/**
	 * @param monopoly the monopoly to set
	 */
	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}

	/**
	 * @return the roadBuilding
	 */
	public int getRoadBuilding() {
		return roadBuilding;
	}

	/**
	 * @param roadBuilding the roadBuilding to set
	 */
	public void setRoadBuilding(int roadBuilding) {
		this.roadBuilding = roadBuilding;
	}

	/**
	 * @return the yearOfPlenty
	 */
	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	/**
	 * @param yearOfPlenty the yearOfPlenty to set
	 */
	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}

	/**
	 * @return the monument
	 */
	public int getMonument() {
		return monument;
	}

	/**
	 * @param monument the monument to set
	 */
	public void setMonument(int monument) {
		this.monument = monument;
	}

	/**
	 * @return the soldier
	 */
	public int getSoldier() {
		return soldier;
	}

	/**
	 * @param soldier the soldier to set
	 */
	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}
	
	public int getTotal(){
		return monument + soldier + monopoly + yearOfPlenty + roadBuilding;
	}

}
