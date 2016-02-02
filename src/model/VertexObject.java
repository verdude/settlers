package model;

public class VertexObject {
	int owner; // The index of the player that owns the vertex
	EdgeLocation location;
	
	
	public VertexObject(){
		//...
	}
	
	// Getters
	public int 	getOwner() 							{ return owner; 			}
	public EdgeLocation getLocation()					{ return location; 			}
	
	// Setters
	public void setOwner(int owner) 					{ this.owner = owner; 		}
	public void setLocation(EdgeLocation location) 	{ this.location = location; }
}
