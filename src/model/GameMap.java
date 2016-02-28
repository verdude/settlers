package model;

import shared.locations.HexLocation;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
	int 				radius;				
	Robber 				robber;
	List<Hex> 			hexes;
	List<Port> 			ports;
	List<Road> 			roads;
	List<VertexObject> 	settlements;
	List<VertexObject> 			cities;
	
	
	
	// Methods
	public GameMap(){
		hexes 			= new ArrayList<Hex>();
		ports 			= new ArrayList<Port>();
		roads 			= new ArrayList<Road>();
		settlements 	= new ArrayList<VertexObject>();
		cities 			= new ArrayList<VertexObject>();

		robber			= new Robber();
	}
	
	/** Moves the robber to a different position on the map
	 * @pre 	The current player must have rolled a 7;
	 * @post 	The robber will be moved to a different position on the map
	 * @param 	position, the VertextObject where the robber will be placed
	 * @throws GameMapException if this function runs and dies when it shoouldn't
	 */
	public void moveRobber(HexLocation position) throws GameMapException {
		robber.move(position);
	}
	
	/** Places a settlement for the player on a given vertex
	 * @pre 	The player has to have the resources (1 wheat, 1 brick, 1 lumber, 1 sheep) for a settlement and the vertex has to be connected
	 * 			  to a player's road and at least 2 edges away from another settlement or city. And the player has a settlement left.
	 * @post 	A settlement will be placed at the desired vertex
	 * @param position, (vertex): the vertex where the player wants to place the settlement
	 * @throws GameMapException if this function runs and dies when it shoouldn't
	 */
	public void placeSettlement(VertexObject position) throws GameMapException {
		settlements.add(position);
	}
	
	/** Places a city for the player on a given vertex
	 * @pre 	The player must have the resources (2 wheat, 3 ore) for a city and the vertex must be connected 
	 * 			  to a player's road and at least 2 edges away from another settlement or city. And the player has a city left.
	 * @post 	a city will be placed at the desired vertex
	 * @param position, (vertex): the vertex where the player wants to place the city
	 * @throws GameMapException if this function runs and dies when it shoouldn't
	 */
	public void placeCity(VertexObject position) throws GameMapException {
		cities.add(position);
	}
	
	/** Places a road for the player on a given edge
	 * @pre 	The player must have the resources (1 lumber, 1 brick) for a road and the edge must be connected 
	 * 			  to a player's road or municipality and the edge must not be occupied by another piece. And the player has a road left.
	 * @post 	a road will be placed at the desired edge
	 * @param position, (edge): the edge where the player wants to place a road
	 * @throws GameMapException if this function runs and dies when it shoouldn't
	 */
	public void placeRoad(EdgeValue position) throws GameMapException {
		roads.add(new Road(position));
	}
	
	
	
	//Getters
	public List<Hex>  		getHexes() 								{ return hexes; 			}
	public List<Port> 		getPorts() 								{ return ports; 			}
	public List<Road> 		getRoads() 								{ return roads; 			}
	public List<VertexObject> getSettlements() 						{ return settlements; 		}
	public List<VertexObject> 		getCities() 							{ return cities; 			}
	public int 				getRadius() 							{ return radius; 			}
	public Robber 			getRobber() 							{ return robber; 			}
	
	// Setters
	public void setHexes(List<Hex> hexes) 							{ this.hexes = hexes; 		}
	public void setPorts(List<Port> ports) 							{ this.ports = ports; 		}
	public void setRoads(List<Road> roads) 							{ this.roads = roads; 		}
	public void setSettlements(List<VertexObject> settlements) 		{ this.settlements = settlements; 	}
	public void setCities(List<VertexObject> cities) 						{ this.cities = cities; 	}
	public void setRadius(int _RADIUS) 								{ radius = _RADIUS; 		}
	public void setRobber(Robber robber) 							{ this.robber = robber; 	}
}
