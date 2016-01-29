package client;

public class EdgeValue {
	int owner; // index of the player that is the owner
	VertexObject location;
	
	
	public EdgeValue() {
		//...
	}
	
	// Getters
	public int 		getOwner() 					{ return owner; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	// Setters
	public void setOwner(int owner) 				{ this.owner = owner; 		}
	public void setLocation(VertexObject location) 	{ this.location = location; }
}
