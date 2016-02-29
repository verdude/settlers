package model;

public class Road {
	int owner;
	EdgeValue 	location;
	
	public Road(){
		location = null;
		owner = -1;
	}
	
	public Road(EdgeValue location){
		setLocation(location);
	}
	
	public int getOwner()				{ return owner; 			}
	public EdgeValue 	getLocation() 				{ return location; 			}
	
	public void setOwner(int id) 				{ this.owner = id; 		}
	public void setLocation(EdgeValue location)		{ this.location = location; }
}
