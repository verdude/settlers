package client;

public class EdgeLocation {
	int x;
	int y;
	Direction direction;
	
	public EdgeLocation(){
		//...
	}
	
	//Getters
	public int getX() 								{ return x; 					}
	public int getY() 								{ return y; 					}
	public Direction getDirection() 				{ return direction; 			}
	
	//Setters
	public void setX(int x) 						{ this.x = x; 					}
	public void setY(int y) 						{ this.y = y; 					}
	public void setDirection(Direction direction) 	{ this.direction = direction; 	}
	
}