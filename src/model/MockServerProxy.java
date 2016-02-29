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
	
	private static MockServerProxy SINGLETON;
	
	public static MockServerProxy getSingleton(String HOST, String PORT) {
		if(SINGLETON == null) {
			SINGLETON = new MockServerProxy(HOST, PORT);
		}
		return SINGLETON;
	}
	
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
		return "{\"deck\":{\"yearOfPlenty\":2,\"monopoly\":2,\"soldier\":14,\"roadBuilding\":2,\"monument\":5},\"map\":{\"hexes\":[{\"vertexLocation\":{\"x\":0,\"y\":-2}},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":1,\"y\":-2},\"number\":4},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":2,\"y\":-2},\"number\":11},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":-1,\"y\":-1},\"number\":8},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":0,\"y\":-1},\"number\":3},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":1,\"y\":-1},\"number\":9},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":2,\"y\":-1},\"number\":12},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":-2,\"y\":0},\"number\":5},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":-1,\"y\":0},\"number\":10},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":0,\"y\":0},\"number\":11},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":1,\"y\":0},\"number\":5},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":2,\"y\":0},\"number\":6},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":-2,\"y\":1},\"number\":2},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":-1,\"y\":1},\"number\":9},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":0,\"y\":1},\"number\":4},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":1,\"y\":1},\"number\":10},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":-2,\"y\":2},\"number\":6},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":-1,\"y\":2},\"number\":3},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":0,\"y\":2},\"number\":8}],\"roads\":[{\"owner\":1,\"vertexLocation\":{\"direction\":\"S\",\"x\":-1,\"y\":-1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":-2}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"S\",\"x\":1,\"y\":-1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"S\",\"x\":0,\"y\":1}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"S\",\"x\":0,\"y\":0}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-2,\"y\":1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":0}}],\"cities\":[],\"settlements\":[{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SE\",\"x\":1,\"y\":-2}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"SW\",\"x\":0,\"y\":0}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"SW\",\"x\":1,\"y\":-1}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-2,\"y\":1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SE\",\"x\":0,\"y\":1}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":-1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":0}}],\"radius\":3,\"ports\":[{\"ratio\":2,\"resource\":\"brick\",\"direction\":\"NE\",\"vertexLocation\":{\"x\":-2,\"y\":3}},{\"ratio\":3,\"direction\":\"SE\",\"vertexLocation\":{\"x\":-3,\"y\":0}},{\"ratio\":2,\"resource\":\"sheep\",\"direction\":\"NW\",\"vertexLocation\":{\"x\":3,\"y\":-1}},{\"ratio\":3,\"direction\":\"NW\",\"vertexLocation\":{\"x\":2,\"y\":1}},{\"ratio\":3,\"direction\":\"N\",\"vertexLocation\":{\"x\":0,\"y\":3}},{\"ratio\":2,\"resource\":\"ore\",\"direction\":\"S\",\"vertexLocation\":{\"x\":1,\"y\":-3}},{\"ratio\":3,\"direction\":\"SW\",\"vertexLocation\":{\"x\":3,\"y\":-3}},{\"ratio\":2,\"resource\":\"wheat\",\"direction\":\"S\",\"vertexLocation\":{\"x\":-1,\"y\":-2}},{\"ratio\":2,\"resource\":\"wood\",\"direction\":\"NE\",\"vertexLocation\":{\"x\":-3,\"y\":2}}],\"robber\":{\"x\":0,\"y\":-2}},\"players\":[{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":1,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":0,\"playerIndex\":0,\"name\":\"Sam\",\"color\":\"red\"},{\"resources\":{\"brick\":1,\"wood\":0,\"sheep\":1,\"wheat\":0,\"ore\":1},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":1,\"playerIndex\":1,\"name\":\"Brooke\",\"color\":\"blue\"},{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":1,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":10,\"playerIndex\":2,\"name\":\"Pete\",\"color\":\"red\"},{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":0,\"ore\":1},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":11,\"playerIndex\":3,\"name\":\"Mark\",\"color\":\"green\"}],\"log\":{\"lines\":[{\"source\":\"Sam\",\"message\":\"Sam built a road\"},{\"source\":\"Sam\",\"message\":\"Sam built a settlement\"},{\"source\":\"Sam\",\"message\":\"Sam's turn just ended\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a road\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a settlement\"},{\"source\":\"Brooke\",\"message\":\"Brooke's turn just ended\"},{\"source\":\"Pete\",\"message\":\"Pete built a road\"},{\"source\":\"Pete\",\"message\":\"Pete built a settlement\"},{\"source\":\"Pete\",\"message\":\"Pete's turn just ended\"},{\"source\":\"Mark\",\"message\":\"Mark built a road\"},{\"source\":\"Mark\",\"message\":\"Mark built a settlement\"},{\"source\":\"Mark\",\"message\":\"Mark's turn just ended\"},{\"source\":\"Mark\",\"message\":\"Mark built a road\"},{\"source\":\"Mark\",\"message\":\"Mark built a settlement\"},{\"source\":\"Mark\",\"message\":\"Mark's turn just ended\"},{\"source\":\"Pete\",\"message\":\"Pete built a road\"},{\"source\":\"Pete\",\"message\":\"Pete built a settlement\"},{\"source\":\"Pete\",\"message\":\"Pete's turn just ended\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a road\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a settlement\"},{\"source\":\"Brooke\",\"message\":\"Brooke's turn just ended\"},{\"source\":\"Sam\",\"message\":\"Sam built a road\"},{\"source\":\"Sam\",\"message\":\"Sam built a settlement\"},{\"source\":\"Sam\",\"message\":\"Sam's turn just ended\"}]},\"chat\":{\"lines\":[]},\"bank\":{\"brick\":23,\"wood\":21,\"sheep\":20,\"wheat\":22,\"ore\":22},\"turnTracker\":{\"status\":\"Rolling\",\"currentTurn\":0,\"longestRoad\":-1,\"largestArmy\":-1},\"winner\":-1,\"version\":0}";
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
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 2,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 12\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 11\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 1,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 5\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 2,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 2\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 9\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 4\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        },\r\n" + 
				"        \"number\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -2,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 6\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -1,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        },\r\n" + 
				"        \"number\": 8\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"roads\": [\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 2,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"S\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
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
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 3,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 2,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": 1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SE\",\r\n" + 
				"          \"x\": 0,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 1,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"direction\": \"SW\",\r\n" + 
				"          \"x\": -1,\r\n" + 
				"          \"y\": -1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"owner\": 0,\r\n" + 
				"        \"vertexLocation\": {\r\n" +
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
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 2,\r\n" + 
				"          \"y\": 1\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"brick\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -2,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SW\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 3,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"N\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 0,\r\n" + 
				"          \"y\": 3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wood\",\r\n" + 
				"        \"direction\": \"NE\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -3,\r\n" + 
				"          \"y\": 2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 3,\r\n" + 
				"        \"direction\": \"SE\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -3,\r\n" + 
				"          \"y\": 0\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"wheat\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": -1,\r\n" + 
				"          \"y\": -2\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"ore\",\r\n" + 
				"        \"direction\": \"S\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
				"          \"x\": 1,\r\n" + 
				"          \"y\": -3\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"ratio\": 2,\r\n" + 
				"        \"resource\": \"sheep\",\r\n" + 
				"        \"direction\": \"NW\",\r\n" + 
				"        \"vertexLocation\": {\r\n" +
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
	public String sendChat(String playerName, String message) {
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
	public String buildRoad(int playerIndex, EdgeValue roadLocation, String free) {
		
		return gamesModel("");
	}

	@Override
	public String buildSettlement(int playerIndex, VertexObject vertexObject, String free) {
		
		return gamesModel("");
	}

	@Override
	public String buildCity(int playerIndex, VertexObject vertexObject) {
		
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
