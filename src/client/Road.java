package client;

public class Road {
	int playerId;
	VertexObject location;
	
	public Road(){}
	
	public int 			getPlayerId()				{ return playerId; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	public void setPlayerId(int id) 				{ this.playerId = id; 		}
	public void setLocation(VertexObject location)	{ this.location = location; }
}
