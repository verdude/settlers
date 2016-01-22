package client;

public class VertexObject {
	Player owner;
	VertexLocation location;
	
	
	public VertexObject(){
		//...
	}
	
	// Getters
	public Player 	getOwner() 							{ return owner; 			}
	public VertexLocation getLocation()					{ return location; 			}
	
	// Setters
	public void setOwner(Player owner) 					{ this.owner = owner; 		}
	public void setLocation(VertexLocation location) 	{ this.location = location; }
}
