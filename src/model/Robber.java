package model;

import shared.locations.HexLocation;

public class Robber {
	HexLocation location;
	
	public Robber(){
		//...
	}
	
	/**	Moves the Robber when the player rolls 7
	 * @pre		Robber has a valid location
	 * post		New location is not the same as old location
	 * @param location, The new hex location for the robber
	 * @throws ClientException if this function runs and dies when it shoouldn't
	 */
	public void move(HexLocation location) throws GameMapException{}
	
	public HexLocation getLocation() 				{ return location; 			}
	public void setLocation(HexLocation location) 	{ this.location = location; }
}
