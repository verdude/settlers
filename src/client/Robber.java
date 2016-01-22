package client;

public class Robber {
	HexLocation location;
	
	public Robber(){
		//...
	}
	
	/**	Moves the Robber when the player rolls 7
	 * @pre		Robber has a valid location
	 * post		New location is not the same as old location
	 * @param location
	 */
	public void move(HexLocation location){}
	
	public HexLocation getLocation() 				{ return location; 			}
	public void setLocation(HexLocation location) 	{ this.location = location; }
}
