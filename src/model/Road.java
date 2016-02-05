package model;

public class Road {
	int playerId;
	EdgeValue location;
	
	public Road(){
		location = null;
		playerId = -1;
		
		
	}
	
	public int 			getPlayerId()				{ return playerId; 			}
	public EdgeValue getLocation() 				{ return location; 			}
	
	public void setPlayerId(int id) 				{ this.playerId = id; 		}
	public void setLocation(EdgeValue location)	{ this.location = location; }
	
//	@Override
//	public boolean equals(Object o){
//		if(this == o){
//			return true;
//		}
//		if(o == null){
//			return false;
//		}
//		if(o.getClass() != this.getClass()){
//			return false;
//		}
//		
//		
//		
//		return false;
//	}
}
