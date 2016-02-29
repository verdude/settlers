package model;

import shared.locations.HexLocation;

public class Robber {
	HexLocation location;
	
	public Robber(){
		//...
	}
	
	/**	Moves the Robber when the player rolls 7
	 * @pre		Robber has a valid vertexLocation
	 * post		New vertexLocation is not the same as old vertexLocation
	 * @param location, The new hex vertexLocation for the robber
	 * @throws ClientException if this function runs and dies when it shoouldn't
	 */
	public void move(HexLocation location) throws GameMapException{}
	
	public HexLocation getLocation() 				{ return location; 			}
	public void setLocation(HexLocation location) 	{ this.location = location; }
}
