package model;

public class Port {
	Resource resourceType;
	VertexObject location;
	Direction direction;
	Double ratio;
	
	
	public Port(){
		//...
	}
	
	//Getters
	public Resource 	getResourceType() 				{ return resourceType; 				}
	public VertexObject getLocation() 					{ return location; 					}
	public Direction 	getDirection() 					{ return direction;	 				}
	public Double 		getRatio() 						{ return ratio; 					}
	
	//Setters
	public void setResourceType(Resource resourceType) 	{ this.resourceType = resourceType;	}
	public void setLocation(VertexObject location) 		{ this.location = location; 		}
	public void setDirection(Direction direction) 		{ this.direction = direction;		}
	public void setRatio(Double ratio) 					{ this.ratio = ratio; 				}	
}
