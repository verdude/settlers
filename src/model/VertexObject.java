package model;

import shared.locations.VertexLocation;

public class VertexObject {
	int owner; // The index of the player that owns the vertex
	VertexLocation location;


	public VertexObject(){
		owner = -1;
		//...
	}

	// Getters
	public int 	getOwner() 								{ return owner; 			}
	public VertexLocation getLocation()					{ return location; 			}

	// Setters
	public void setOwner(int owner) 					{ this.owner = owner; 		}
	public void setLocation(VertexLocation location) 	{ this.location = location; }
}
