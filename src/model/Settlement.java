package model;

import shared.locations.VertexLocation;

public class Settlement {
	int playerId;
	VertexObject location;
	
	public Settlement(){}
	public Settlement(VertexObject position){
		
		this.setLocation(position);
		
	}
	
	public int 			getPlayerId()				{ return playerId; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	public void setPlayerId(int id) 				{ this.playerId = id; 		}
	public void setLocation(VertexObject location)	{ this.location = location; }
}
