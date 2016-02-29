package model;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class VertexObject {
		int owner; // The index of the player that owns the vertex
		VertexLocation vertexLocation;
		EdgeLocation location;


	public EdgeLocation getLocation() {
		return location;
	}

	public void setLocation(EdgeLocation location) {
		this.location = location;
	}


		public VertexObject(){
			owner = -1;
//			location = new EdgeLocation(new HexLocation(0,0), EdgeDirection.North);
//			vertexLocation = location.toVertexLocation();
			//...
		}

		// Getters
		public int 	getOwner() 								{ return owner; 			}
		public VertexLocation getVertexLocation()					{ return location.toVertexLocation(); 			}

		// Setters
		public void setOwner(int owner) 					{ this.owner = owner; 		}
		public void setVertexLocation(VertexLocation vertexLocation) 	{ this.vertexLocation = vertexLocation; }
}
