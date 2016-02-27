package model;

public class Road {
	int 		playerId;
	EdgeValue 	location;
	
	public Road(){
		location = null;
		playerId = -1;
	}
	
	public Road(EdgeValue location){
		setLocation(location);
	}
	
	public int 			getPlayerId()				{ return playerId; 			}
	public EdgeValue 	getLocation() 				{ return location; 			}
	
	public void setPlayerId(int id) 				{ this.playerId = id; 		}
	public void setLocation(EdgeValue location)		{ this.location = location; }
}
