package map;

import java.util.*;

public class Map {
	int RADIUS;			// It appears that the map will have a radius of 2;					
	Robber robber;
	List<Hex> 			hexList;
	List<Port> 			portList;
	List<Road> 			roadList;
	List<Settlement> 	settlementList;
	List<City> 			cityList;
	
	
	
	//methods
	public Map(){
		// Not sure if the Map has any dependencies...
	}
	
	/** Moves the robber to a different position on the map
	 * @pre 	The current player must have rolled a 7;
	 * @post 	The robber will be moved to a different position on the map
	 * @param 	position
	 */
	public void moveRobber(VertexObject position){}
	
	/** Places a settlement for the player on a given vertex
	 * @pre 	The player has to have the resources (1 wheat, 1 brick, 1 lumber, 1 sheep) for a settlement and the vertex has to be connected
	 * 			  to a player's road and at least 2 edges away from another settlement or city. And the player has a settlement left.
	 * @post 	A settlement will be placed at the desired vertex
	 * @param 	(vertex): the vertex where the player wants to place the settlement
	 */
	public void placeSettlement(VertexObject position){}
	
	/** Places a city for the player on a given vertex
	 * @pre 	The player must have the resources (2 wheat, 3 ore) for a city and the vertex must be connected 
	 * 			  to a player's road and at least 2 edges away from another settlement or city. And the player has a city left.
	 * @post 	a city will be placed at the desired vertex
	 * @param 	(vertex): the vertex where the player wants to place the city
	 */
	public void placeCity(VertexObject position){}
	
	/** Places a road for the player on a given edge
	 * @pre 	The player must have the resources (1 lumber, 1 brick) for a road and the edge must be connected 
	 * 			  to a player's road or municipality and the edge must not be occupied by another piece. And the player has a road left.
	 * @post 	a road will be placed at the desired edge
	 * @param 	(edge): the edge where the player wants to place a road
	 */
	public void placeRoad(VertexObject position){}
	
	
	
	//Getters
	public List<Hex>  		getHexList() 							{ return hexList; 			}
	public List<Port> 		getPortList() 							{ return portList; 			}
	public List<Road> 		getRoadList() 							{ return roadList; 			}
	public List<Settlement> getSettlementList() 					{ return settlementList; 	}
	public List<City> 		getCityList() 							{ return cityList; 			}
	public int 				getRADIUS() 							{ return RADIUS; 			}
	public Robber 			getRobber() 							{ return robber; 			}
	
	// Setters
	public void setHexList(List<Hex> hexList) 						{ this.hexList = hexList; 	}
	public void setPortList(List<Port> portList) 					{ this.portList = portList; }
	public void setRoadList(List<Road> roadList) 					{ this.roadList = roadList; }
	public void setSettlementList(List<Settlement> settlementList) 	{ this.settlementList = settlementList; }
	public void setCityList(List<City> cityList) 					{ this.cityList = cityList; }
	public void setRADIUS(int _RADIUS) 								{ RADIUS = _RADIUS; 		}
	public void setRobber(Robber robber) 							{ this.robber = robber; 	}
}
