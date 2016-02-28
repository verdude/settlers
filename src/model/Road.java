package model;

public class Road {
	int playerIndex;
	EdgeValue 	location;
	
	public Road(){
		location = null;
		playerIndex = -1;
	}
	
	public Road(EdgeValue location){
		setLocation(location);
	}
	
	public int getPlayerIndex()				{ return playerIndex; 			}
	public EdgeValue 	getLocation() 				{ return location; 			}
	
	public void setPlayerIndex(int id) 				{ this.playerIndex = id; 		}
	public void setLocation(EdgeValue location)		{ this.location = location; }
}
