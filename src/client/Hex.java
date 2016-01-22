package client;

public class Hex {
	HexLocation location;
	Resource resourceType;
	int number;
	
	
	public Hex() {
		//...
	}
	
	// Getters
	public HexLocation	getLocation() 					{ return location; 					}
	public Resource 	getResourceType() 				{ return resourceType;				}
	public int 			getNumber() 					{ return number; 					}
	
	// Setters
	public void setLocation(HexLocation location) 		{ this.location = location; 		}
	public void setResourceType(Resource resourceType) 	{ this.resourceType = resourceType; }
	public void setNumber(int number) 					{ this.number = number; 			}
}
