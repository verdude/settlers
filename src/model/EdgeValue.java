package model;

import shared.locations.EdgeLocation;

public class EdgeValue {
	int owner; 				// index of the player that is the owner
	EdgeLocation location;


	public EdgeValue() {
		owner = -1;
	}

	// Getters
	public int 		getOwner() 						{ return owner; 			}
	public EdgeLocation getLocation() 				{ return location; 			}

	// Setters
	public void setOwner(int owner) 				{ this.owner = owner; 		}
	public void setLocation(EdgeLocation location) 	{ this.location = location; }
}
