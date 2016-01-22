package map;

public class VertexObject {
	Player owner;
	VertexObject location;
	
	
	public VertexObject(){
		//...
	}
	
	// Getters
	public Player 	getOwner() 						{ return owner; 			}
	public Location getLocation() 					{ return location; 			}
	
	// Setters
	public void setOwner(Player owner) 				{ this.owner = owner; 		}
	public void setLocation(VertexObject location) 	{ this.location = location; }
}
