package model;

public class City {
	int playerIndex;
	VertexObject location;
	
	public City(){}
	public City(VertexObject position){
		
		this.setLocation(position);
		
	}
	
	public int getPlayerIndex()				{ return playerIndex; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	public void setPlayerIndex(int id) 				{ this.playerIndex = id; 		}
	public void setLocation(VertexObject location)	{ this.location = location; }

}
