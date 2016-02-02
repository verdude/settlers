package model;

/**
 * This is the 'bank' class. Tracks the amount of resources that are not owned by a player.
 */
public class ResourceList {
	
	private int brick;
	private int ore;
	private int sheep;
	private int wheat;
	private int wood;
	public final static int max = 19;
	public final static int min = 0;
	
	/**
	 * Sets all resources to the initial value (19).
	 */
	public ResourceList(int initial) {
		brick = ore = sheep = wheat = wood = initial;
	}

	/**
	 * @return the brick
	 */
	public int getBrick() {
		return brick;
	}

	/**
	 * @param brick the brick to set
	 */
	public void setBrick(int brick) {
		this.brick = brick;
	}

	/**
	 * @return the ore
	 */
	public int getOre() {
		return ore;
	}

	/**
	 * @param ore the ore to set
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}

	/**
	 * @return the sheep
	 */
	public int getSheep() {
		return sheep;
	}

	/**
	 * @param sheep the sheep to set
	 */
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	/**
	 * @return the wheat
	 */
	public int getWheat() {
		return wheat;
	}

	/**
	 * @param wheat the wheat to set
	 */
	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	/**
	 * @return the wood
	 */
	public int getWood() {
		return wood;
	}

	/**
	 * @param wood the wood to set
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}

}
