package model;

import shared.locations.EdgeLocation;

public class Road {
	EdgeLocation location;
	int playerIndex;
	
	public Road(){
		location = null;
		playerIndex = -1;
	}
	
	public Road(EdgeLocation location){
		setLocation(location);
	}
	
	public int getPlayerIndex()				{ return playerIndex; 			}
	public EdgeLocation getLocation() 				{ return location; 			}
	
	public void setPlayerIndex(int id) 				{ this.playerIndex = id; 		}
	public void setLocation(EdgeLocation location)		{ this.location = location; }
}
