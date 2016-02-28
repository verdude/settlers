package model;


public class Settlement {
	int playerIndex;
	VertexObject location;
	
	public Settlement(){}
	public Settlement(VertexObject position){
		
		this.setLocation(position);
		
	}
	
	public int getPlayerIndex()				{ return playerIndex; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	public void setPlayerIndex(int id) 				{ this.playerIndex = id; 		}
	public void setLocation(VertexObject location)	{ this.location = location; }
}
