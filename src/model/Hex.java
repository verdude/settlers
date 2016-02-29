package model;

import shared.locations.HexLocation;

public class Hex {
	HexLocation location;
	String resource;
	int number;
	
	
	public Hex() {
		//...
	}

	
	// Getters
	public HexLocation	getLocation() 					{ return location; 					}
	public String 		getResource() 					{ return resource;					}
	public int 			getNumber() 					{ return number; 					}
	
	// Setters
	public void setLocation(HexLocation location) 		{ this.location = location; 		}
	public void setResource(String resource) 			{ this.resource = resource; 		}
	public void setNumber(int number) 					{ this.number = number; 			}
}
