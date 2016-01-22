package map;

public class EdgeValue {
	Player owner;
	VertexObject location;
	
	
	public EdgeValue() {
		//...
	}
	
	// Getters
	public Player 		getOwner() 					{ return owner; 			}
	public VertexObject getLocation() 				{ return location; 			}
	
	// Setters
	public void setOwner(Player owner) 				{ this.owner = owner; 		}
	public void setLocation(VertexObject location) 	{ this.location = location; }
}
