package model;

import shared.locations.EdgeLocation;

public class Road {
	int owner;
	EdgeLocation location;
	
	public Road(){
		location = null;
		owner = -1;
	}
	
	public Road(EdgeLocation location){
		setLocation(location);
	}
	
	public int getOwner()				{ return owner; 			}
	public EdgeLocation getLocation() 				{ return location; 			}
	
	public void setOwner(int id) 				{ this.owner = id; 		}
	public void setLocation(EdgeLocation location)		{ this.location = location; }
}
