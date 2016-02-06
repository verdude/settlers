package model;

public class City {
	int playerId;
	VertexObject location;
	
	public City(){}
	public City(VertexObject position){
		
		this.setLocation(position);
		
	}
	
	public int 			getPlayerId()				{ return playerId; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	public void setPlayerId(int id) 				{ this.playerId = id; 		}
	public void setLocation(VertexObject location)	{ this.location = location; }
}
