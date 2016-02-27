package model;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

public class Port {
	PortType 		resource;
	HexLocation 	location;
	EdgeDirection 	direction;
	Double 			ratio;
	
	
	public Port(){
		//...
	}
	
	//Getters
	public PortType 		getResource() 				{ return resource; 					}
	public HexLocation 		getLocation() 				{ return location; 					}
	public EdgeDirection 	getDirection() 				{ return direction;	 				}
	public Double 			getRatio() 					{ return ratio; 					}
	
	//Setters
	public void setResource(PortType resource) 			{ this.resource = resource;	}
	public void setLocation(HexLocation location) 		{ this.location = location; 		}
	public void setDirection(EdgeDirection direction) 	{ this.direction = direction;		}
	public void setRatio(Double ratio) 					{ this.ratio = ratio; 				}	
}
