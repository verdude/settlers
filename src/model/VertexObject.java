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
			
//			location = new EdgeValue(new HexLocation(0,0), EdgeDirection.North);
//			vertexLocation = location.toVertexLocation();
			//...
		}

		// Getters
		public int 	getOwner() 								{ return owner; 			}
		public VertexLocation getVertexLocation()					{

			if(vertexLocation != null && vertexLocation.getDirection() != null){
				return vertexLocation;
			}
			if(location.getDirection() != null) {
				return location.toVertexLocation();
			}else{
				return null;
			}
		}

		// Setters
		public void setOwner(int owner) 					{ this.owner = owner; 		}
		public void setVertexLocation(VertexLocation vertexLocation) 	{ this.vertexLocation = vertexLocation; }
}
