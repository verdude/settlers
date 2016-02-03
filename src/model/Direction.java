package model;

public class Direction {

	private int type;

	public Direction(int type) {
		this.type = type;
	}

	/**
	 * Returns the int type which maps to a value in the Enums.Directions enum 
	 * @return type Int
	 */
	public int getType() {return type;}

	public void setType(int type) {this.type = type;}

}
