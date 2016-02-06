package model;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

public class Port {
	PortType resourceType;
	HexLocation location;
	EdgeDirection direction;
	Double ratio;
	
	
	public Port(){
		//...
	}
	
	//Getters
	public PortType 	getResourceType() 				{ return resourceType; 				}
	public HexLocation getLocation() 					{ return location; 					}
	public EdgeDirection 	getDirection() 					{ return direction;	 				}
	public Double 		getRatio() 						{ return ratio; 					}
	
	//Setters
	public void setResourceType(PortType resourceType) 	{ this.resourceType = resourceType;	}
	public void setLocation(HexLocation location) 		{ this.location = location; 		}
	public void setDirection(EdgeDirection direction) 		{ this.direction = direction;		}
	public void setRatio(Double ratio) 					{ this.ratio = ratio; 				}	
}
