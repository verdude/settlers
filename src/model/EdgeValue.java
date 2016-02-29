package model;

public class EdgeValue {
	int owner; 				// index of the player that is the owner
	shared.locations.EdgeLocation location;


	public EdgeValue() {
		owner = -1;
	}

	// Getters
	public int 		getOwner() 						{ return owner; 			}
	public shared.locations.EdgeLocation getLocation() 				{ return location; 			}

	// Setters
	public void setOwner(int owner) 				{ this.owner = owner; 		}
	public void setLocation(shared.locations.EdgeLocation location) 	{ this.location = location; }
}
