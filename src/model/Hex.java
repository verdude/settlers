package model;

import shared.locations.HexLocation;

public class Hex {
	HexLocation location;
	String resourceType;
	int number;
	
	
	public Hex() {
		//...
	}
	
	// Getters
	public HexLocation	getLocation() 					{ return location; 					}
	public String 	getResourceType() 				{ return resourceType;				}
	public int 			getNumber() 					{ return number; 					}
	
	// Setters
	public void setLocation(HexLocation location) 		{ this.location = location; 		}
	public void setResourceType(String resourceType) 	{ this.resourceType = resourceType; }
	public void setNumber(int number) 					{ this.number = number; 			}
}
