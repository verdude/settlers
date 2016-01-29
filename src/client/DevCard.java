package client;

public class DevCard {

	/**
	 * Type int maps to a string in the DevCarEnum
	 */
	private int type;

	public DevCard(int type) {
		this.type = type;
	}

	public int getType() {return type;}

	public void setType(int type) {this.type = type;}

}
