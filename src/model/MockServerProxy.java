package model;

import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * @author S Jacob Powell
 *
 *	This is the mock Client implementation of the IProxy interface.
 *	It will do the fake client side server querying and posting '
 *	required for the testing of the playing of the game
 *
 */
public class MockServerProxy implements IProxy {
	
	/**
	 * 
	 * @param HOST The hostname of the server
	 * @param PORT The Port on which the server program is to be accessed
	 * @pre HOST and PORT are not "" and correct
	 * @post the ServerProxy is connected and ready to use
	 */
	public MockServerProxy(String HOST, String PORT) {
	}	
	
	/**
	 * @return the HOST
	 */
	public String getHOST() {
		return gamesModel("");
	}

	/**
	 * @return the PORT
	 */
	public String getPORT() {
		return gamesModel("");
	}

	@Override
	public String userLogin(String username, String password) {
		
		return "Success";
	}

	@Override
	public String userRegister(String username, String password) {
		
		return "Success";
	}

	@Override
	public String gamesList() {
		
		return "[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"Default Game\",\r\n" + 
				"    \"id\": 0,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Sam\",\r\n" + 
				"        \"id\": 0\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"blue\",\r\n" + 
				"        \"name\": \"Brooke\",\r\n" + 
				"        \"id\": 1\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"red\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Mark\",\r\n" + 
				"        \"id\": 11\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"AI Game\",\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"white\",\r\n" + 
				"        \"name\": \"Squall\",\r\n" + 
				"        \"id\": -2\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"purple\",\r\n" + 
				"        \"name\": \"Quinn\",\r\n" + 
				"        \"id\": -3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Ken\",\r\n" + 
				"        \"id\": -4\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"Empty Game\",\r\n" + 
				"    \"id\": 2,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Sam\",\r\n" + 
				"        \"id\": 0\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"blue\",\r\n" + 
				"        \"name\": \"Brooke\",\r\n" + 
				"        \"id\": 1\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"red\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Mark\",\r\n" + 
				"        \"id\": 11\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"]";
	}

	@Override
	public String gamesCreate(String randomTiles, String randomNumbers, String randomPorts, String name) {
		
		return "{\r\n" + 
				"  \"title\": \"sdfsfr\",\r\n" + 
				"  \"id\": 3,\r\n" + 
				"  \"players\": [\r\n" + 
				"    {},\r\n" + 
				"    {},\r\n" + 
				"    {},\r\n" + 
				"    {}\r\n" + 
				"  ]\r\n" + 
				"}";
	}

	@Override
	public String gamesJoin(int ID, String color) {
		
		return "Success";
	}

	@Override
	public String gamesSave(int ID, String name) {
		
		return "Success";
	}

	@Override
	public String gamesLoad(String name) {
		
		return "Success";
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	@Override
	public String gamesModel(String version) {
		return "{\r\n" + 
				"  \"deck\": {\r\n" + 
				"    \"yearOfPlenty\": 2,\r\n" + 
				"    \"monopoly\": 2,\r\n" + 
				"    \"soldier\": 14,\r\n" + 
				"    \"roadBuilding\": 2,\r\n" + 
				"    \"monument\": 5\r\n" + 
				"  },\r\n" + 
				"  \"map\": {\r\n" + 
				"    \"hexes\": [\r\n" + 
				"      {\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 12\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 2\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"roads\": [\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"cities\": [],\r\n" + 
				"    \"settlements\": [\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"radius\": 3,\r\n" + 
				"    \"ports\": [\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"NW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 3,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"N\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -3,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SE\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -3,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"direction\": \"NW\",\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"x\": 3,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"robber\": {\r\n" + 
				"      \"x\": 0,\r\n" + 
				"      \"y\": -2\r\n" + 
				"    }\r\n" + 
				"  },\r\n" + 
				"  \"players\": [\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 1,\r\n" + 
				"        \"ore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 0,\r\n" + 
				"      \"playerIndex\": 0,\r\n" + 
				"      \"name\": \"Sam\",\r\n" + 
				"      \"color\": \"orange\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 1,\r\n" + 
				"        \"wood\": 0,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 0,\r\n" + 
				"        \"ore\": 1\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 1,\r\n" + 
				"      \"playerIndex\": 1,\r\n" + 
				"      \"name\": \"Brooke\",\r\n" + 
				"      \"color\": \"blue\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 1,\r\n" + 
				"        \"ore\": 0\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 10,\r\n" + 
				"      \"playerIndex\": 2,\r\n" + 
				"      \"name\": \"Pete\",\r\n" + 
				"      \"color\": \"red\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"resources\": {\r\n" + 
				"        \"brick\": 0,\r\n" + 
				"        \"wood\": 1,\r\n" + 
				"        \"sheep\": 1,\r\n" + 
				"        \"wheat\": 0,\r\n" + 
				"        \"ore\": 1\r\n" + 
				"      },\r\n" + 
				"      \"oldDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"newDevCards\": {\r\n" + 
				"        \"yearOfPlenty\": 0,\r\n" + 
				"        \"monopoly\": 0,\r\n" + 
				"        \"soldier\": 0,\r\n" + 
				"        \"roadBuilding\": 0,\r\n" + 
				"        \"monument\": 0\r\n" + 
				"      },\r\n" + 
				"      \"roads\": 13,\r\n" + 
				"      \"cities\": 4,\r\n" + 
				"      \"settlements\": 3,\r\n" + 
				"      \"soldiers\": 0,\r\n" + 
				"      \"victoryPoints\": 2,\r\n" + 
				"      \"monuments\": 0,\r\n" + 
				"      \"playedDevCard\": false,\r\n" + 
				"      \"discarded\": false,\r\n" + 
				"      \"playerID\": 11,\r\n" + 
				"      \"playerIndex\": 3,\r\n" + 
				"      \"name\": \"Mark\",\r\n" + 
				"      \"color\": \"green\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"log\": {\r\n" + 
				"    \"lines\": [\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Mark\",\r\n" + 
				"        \"message\": \"Mark's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Pete\",\r\n" + 
				"        \"message\": \"Pete's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Brooke\",\r\n" + 
				"        \"message\": \"Brooke's turn just ended\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a road\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam built a settlement\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"source\": \"Sam\",\r\n" + 
				"        \"message\": \"Sam's turn just ended\"\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  \"chat\": {\r\n" + 
				"    \"lines\": []\r\n" + 
				"  },\r\n" + 
				"  \"bank\": {\r\n" + 
				"    \"brick\": 23,\r\n" + 
				"    \"wood\": 21,\r\n" + 
				"    \"sheep\": 20,\r\n" + 
				"    \"wheat\": 22,\r\n" + 
				"    \"ore\": 22\r\n" + 
				"  },\r\n" + 
				"  \"turnTracker\": {\r\n" + 
				"    \"status\": \"Rolling\",\r\n" + 
				"    \"currentTurn\": 0,\r\n" + 
				"    \"longestRoad\": -1,\r\n" + 
				"    \"largestArmy\": -1\r\n" + 
				"  },\r\n" + 
				"  \"winner\": -1,\r\n" + 
				"  \"version\": 0\r\n" + 
				"}";
	}

	@Override
	public String gamesReset() {
		return gamesModel("");
	}

	@Override
	public String gamesCommandsGet() {
		
		return "[]";
	}

	@Override
	public String gamesCommandsPost(String commandList) {
		return gamesModel("");
	}

	@Override
	public String gamesListAI() {
		
		return "[\r\n" + 
				"  \"LARGEST_ARMY\"\r\n" + 
				"]";
	}

	@Override
	public String gamesAddAI(String AIType) {
		return "Could not add AI player  [LARGEST_ARMY]";
	}

	@Override
	public String utilChangeLogLevel(String logLevel) {
		return "Success";
	}

	@Override
	public String sendChat(int playerIndex, String message) {
		return gamesModel("");
	}

	@Override
	public String acceptTrade(int playerIndex, boolean willAccept) {
		return gamesModel("");
	}

	@Override
	public String discardCards(int playerIndex, List<ResourceType> discardedCards) {
		
		return gamesModel("");
	}

	@Override
	public String rollNumber(int playerIndex, int number) {
		
		return gamesModel("");
	}

	@Override
	public String buildRoad(int playerIndex, EdgeValue roadLocation, boolean free) {
		
		return gamesModel("");
	}

	@Override
	public String buildSettlement(int playerIndex, VertexObject vertexObject, String free) {
		
		return gamesModel("");
	}

	@Override
	public String buildCity(int playerIndex, VertexObject vertexObject, String free) {
		
		return gamesModel("");
	}

	@Override
	public String offerTrade(int playerIndex, TradeOffer offer) {
		
		return gamesModel("");
	}

	@Override
	public String maritimeTrade(int playerIndex, int ratio,
			ResourceType inputResource, ResourceType outputResource) {
		
		return gamesModel("");
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	@Override
	public String robPlayer(int playerIndex, int victimIndex,
			HexLocation location) {
		
		return gamesModel("");
	}

	@Override
	public String finishTurn(int playerIndex) {
		
		return gamesModel("");
	}

	@Override
	public String buyDevCard(int playerIndex) {
		
		return gamesModel("");
	}

	@Override
	public String soldier(int playerIndex, int victimIndex, HexLocation location) {
		
		return gamesModel("");
	}

	@Override
	public String yearOfPlenty(int playerIndex, ResourceType resource1,
			ResourceType resource2) {
		
		return gamesModel("");
	}

	@Override
	public String roadBuilding(int playerIndex, EdgeLocation spot1,
			EdgeLocation spot2) {
		
		return gamesModel("");
	}

	@Override
	public String monopoly(ResourceType resource, int playerIndex) {
		
		return gamesModel("");
	}

	@Override
	public String monument(int playerIndex) {
		
		return gamesModel("");
	}

	
}
