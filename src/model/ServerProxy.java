package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * @author S Jacob Powell
 *
 *	This is the Client implementation of the IProxy interface.
 *	It will do the client side server querying and posting '
 *	required for the playing of the game
 *
 */
public class ServerProxy implements IProxy {
	private final String HOST;
	private final String PORT;
	private final URL mainURL;
	private String encodedCookie;
//	private String decodedCookie;
	private String gameID;
	private static ServerProxy SINGLETON;
	
	public static ServerProxy getSingleton(String HOST, String PORT) throws MalformedURLException {
		if(SINGLETON == null) {
			SINGLETON = new ServerProxy(HOST, PORT);
		}
		return SINGLETON;
	}
	
	public static ServerProxy getSingleton() throws ClientException {
		if(SINGLETON == null) {
			throw new ClientException();
		}
		return SINGLETON;
	}
	
	/**
	 * 
	 * @param HOST The hostname of the server
	 * @param PORT The Port on which the server program is to be accessed
	 * @throws MalformedURLException 
	 * @pre HOST and PORT are not null and correct
	 * @post the ServerProxy is connected and ready to use
	 */
	private ServerProxy(String HOST, String PORT) throws MalformedURLException {
		this.HOST = HOST;
		this.PORT = PORT;
		mainURL = new URL("http://" + HOST + ":" + PORT + "/");
		encodedCookie = "";
//		decodedCookie = "";
		gameID = "";
	}	
	
	/**
	 * @return the HOST
	 */
	public String getHOST() {
		return HOST;
	}

	/**
	 * @return the PORT
	 */
	public String getPORT() {
		return PORT;
	}

	private String post(String endPoint, String data)
	{
		try {
			byte[] postData = data.getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL(mainURL, endPoint).openConnection();           
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie + "; catan.game=" + gameID);
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
			    response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

	private String get(String endPoint)
	{
		try {
			HttpURLConnection conn= (HttpURLConnection) new URL(mainURL, endPoint).openConnection();           
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie + "; catan.game=" + gameID);
			conn.setUseCaches(false);
			
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
			    response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}
	
	@Override
	public String userLogin(String username, String password) {		
		try {
			byte[] postData = ("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}").getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL(mainURL, "user/login").openConnection();           
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			encodedCookie = conn.getHeaderField("Set-cookie").split(";")[0].split("=")[1];
//			decodedCookie = URLDecoder.decode(encodedCookie);
			while ((line = reader.readLine()) != null) {
			    response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

	@Override
	public String gamesJoin(int id, String color) {
		try {
			byte[] postData = ("{\"id\": \"" + id + "\", \"color\": \"" + color + "\"}").getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;
			HttpURLConnection conn= (HttpURLConnection) new URL(mainURL, "games/join").openConnection();           
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Cookie", "catan.user=" + encodedCookie);
			conn.setUseCaches(false);
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.write(postData);
			StringBuilder response = new StringBuilder();
			String line;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			gameID = conn.getHeaderField("Set-cookie").split(";")[0].split("catan.game=")[1];
			while ((line = reader.readLine()) != null) {
			    response.append(line);
			}
			return response.toString();
		} catch (Exception e) {
			//e.printStackTrace();
			return "Error";
		}
	}

	@Override
	public String userRegister(String username, String password) {
		return post("user/register", "{\"username\": \"" + username + "\", \"password\": \"" + password	 + "\"}");
	}

	@Override
	public String gamesList() {
		return get("games/list");
	}

	@Override
	public String gamesCreate(String randomTiles, String randomNumbers, String randomPorts, String name) {
		return post("games/create", "{\"randomTiles\": " + randomTiles 
				+ ", \"randomNumbers\": " + randomNumbers 
				+ ", \"randomPorts\": " + randomPorts 
				+ ", \"name\": \"" + name + "\"}");
	}

	//Doesn't need junit test
	@Override
	public String gamesSave(int id, String name) {
		return post("games/save", "{\"id\": \"" + id + "\", \"name\": \"" + name + "\"}");
	}

	//Doesn't need junit test
	@Override
	public String gamesLoad(String name) {
		return post("games/load", "{\"name\": \"" + name + "\"}");
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	@Override
	public String gamesModel(String version) {
		if(version.isEmpty()) {
			return get("game/model");
		} else {
			return get("game/model?version=" + version);
		}
			 
	}

	@Override
	public String gamesReset() {
		return get("game/reset");
	}

	@Override
	public String gamesCommandsGet() {
		return get("game/commands");
	}

	@Override
	public String gamesCommandsPost(String commandList) {
		
		return post("game/commands", commandList);
	}

	@Override
	public String gamesListAI() {
		return get("game/listAI");
	}

	@Override
	public String gamesAddAI(String AIType) {
		
		return post("game/addAI", "{\"AIType\": \"" + AIType + "\"}");
	}

	@Override
	public String utilChangeLogLevel(String logLevel) {
		
		return post("util/changeLogLevel", "{\"logLevel\": \"" + logLevel + "\"}");
	}

	@Override
	public String sendChat(String playerName, String message) {
		return post("moves/sendChat", "{\"type\": \"sendChat\", \"playerIndex\": " + playerName + ", \"content\": \"" + message + "\"}");
	}

	@Override
	public String discardCards(int playerIndex, List<ResourceType> discardedCards) {
		int wood = 0;
		int brick = 0;
		int sheep = 0;
		int wheat = 0;
		int ore = 0;
		for(ResourceType resource : discardedCards)
		{
			switch(resource)
			{
				case WOOD:
					wood++;
					break;
				case BRICK:
					brick++;
					break;
				case SHEEP:
					sheep++;
					break;
				case WHEAT:
					wheat++;
					break;
				case ORE:
					ore++;
					break;
			}
		}
		return post("moves/discardCards", "{\"type\": \"discardCards\", \"playerIndex\": " + playerIndex + ", "
				+ "\"discardedCards\": {"
				+ "\"brick\": " + brick + ", "
				+ "\"ore\": " + ore + ", "
				+ "\"sheep\": " + sheep + ", "
				+ "\"wheat\": " + wheat + ", "
				+ "\"wood\": " + wood + ""
				+ "}}");
	}

	@Override
	public String rollNumber(int playerIndex, int number) {
		return post("moves/rollNumber", "{\"type\": \"rollNumber\", \"playerIndex\": " + playerIndex + ", \"number\": " + number + "}");
	}

	@Override
	public String buildRoad(int playerIndex, EdgeValue roadLocation, String free) {
		return post("moves/buildRoad", "{\"type\": \"buildRoad\", \"playerIndex\": " + playerIndex + ", "
				+ "\"roadLocation\": {"
				+ "\"x\": " + roadLocation.getLocation().getHexLoc().getX() + ", "
				+ "\"y\": " + roadLocation.getLocation().getHexLoc().getY() + ", "
				+ "\"direction\": \"" + roadLocation.getLocation().getDir().toString() + "\""
				+ "}, "
				+ "\"free\": " + free + "}");
	}

	@Override
	public String buildSettlement(int playerIndex, VertexObject vertexObject, String free) {
		return post("moves/buildSettlement", "{\"type\": \"buildSettlement\", \"playerIndex\": " + playerIndex + ", "
				+ "\"vertexLocation\": {"
				+ "\"x\": " + vertexObject.getLocation().getHexLoc().getX() + ", "
				+ "\"y\": " + vertexObject.getLocation().getHexLoc().getY() + ", "
				+ "\"direction\": \"" + vertexObject.getLocation().getDir().toString() + "\""
				+ "}, "
				+ "\"free\": " + free + "}");
	}

	@Override
	public String buildCity(int playerIndex, VertexObject vertexObject) {
		return post("moves/buildCity", "{\"type\": \"buildCity\", \"playerIndex\": " + playerIndex + ", "
				+ "\"vertexLocation\": {"
				+ "\"x\": " + vertexObject.getLocation().getHexLoc().getX() + ", "
				+ "\"y\": " + vertexObject.getLocation().getHexLoc().getY() + ", "
				+ "\"direction\": \"" + vertexObject.getLocation().getDir().toString() + "\""
				+ "}}");
	}

	@Override
	public String offerTrade(int playerIndex, TradeOffer offer) {
		List<Integer> offerList = offer.getOffer();
		return post("moves/offerTrade", "{\"type\": \"offerTrade\", \"playerIndex\": " + playerIndex + ", "
				+ "\"offer\": {"
				+ "\"brick\": " + offerList.get(1) + ", "
				+ "\"ore\": " + offerList.get(4) + ", "
				+ "\"sheep\": " + offerList.get(2) + ", "
				+ "\"wheat\": " + offerList.get(3) + ", "
				+ "\"wood\": " + offerList.get(0) + "}, "
				+ "\"receiver\": " + offer.getReceiver()
				+ "}");
	}

	@Override
	public String acceptTrade(int playerIndex, boolean willAccept) {
		return post("moves/acceptTrade", "{\"type\": \"acceptTrade\", \"playerIndex\": "
	+ playerIndex + ", \"willAccept\": " + willAccept + "}");
	}

	@Override
	public String maritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) {
		return post("moves/maritimeTrade", "{\"type\": \"maritimeTrade\", \"playerIndex\": " + playerIndex 
				+ ", \"ratio\": " + ratio + ", "
				+ "\"inputResource\": \"" + inputResource.toString() + "\", "
				+ "\"outputResource\": \"" + outputResource.toString() + "\""
				+ "}");
	}

	@Override
	public String robPlayer(int playerIndex, int victimIndex, HexLocation location) {
		return post("moves/robPlayer", "{\"type\": \"robPlayer\", \"playerIndex\": " + playerIndex 
				+ ", \"victimIndex\": " + victimIndex + ", "
				+ "\"location\": {"
				+ "\"x\": " + location.getX() + ", "
				+ "\"y\": " + location.getY()
				+ "}}");
	}

	@Override
	public String finishTurn(int playerIndex) {
		return post("moves/finishTurn", "{\"type\": \"finishTurn\", \"playerIndex\": " + playerIndex + "}");
	}

	@Override
	public String buyDevCard(int playerIndex) {
		return post("moves/buyDevCard", "{\"type\": \"buyDevCard\", \"playerIndex\": " + playerIndex + "}");
	}

	@Override
	public String soldier(int playerIndex, int victimIndex, HexLocation location) {
		return post("moves/Soldier", "{\"type\": \"Soldier\", \"playerIndex\": " + playerIndex 
				+ ", \"victimIndex\": " + victimIndex + ", "
				+ "\"location\": {"
				+ "\"x\": " + location.getX() + ", "
				+ "\"y\": " + location.getY()
				+ "}}");
	}
	
	@Override
	public String yearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) {
		return post("moves/Year_of_Plenty", "{\"type\": \"Year_of_Plenty\", \"playerIndex\": " + playerIndex 
				+ ", \"resource1\": " + resource1.toString()
				+ ", \"resource2\": " + resource2.toString()
				+ "}");
	}

	@Override
	public String roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		return post("moves/Road_Building", "{\"type\": \"Road_Building\", \"playerIndex\": " + playerIndex + ", "
				+ "\"spot1\": {"
				+ "\"x\": " + spot1.getHexLoc().getX() + ", "
				+ "\"y\": " + spot1.getHexLoc().getY() + ", "
				+ "\"direction\": \"" + spot1.getDir().toString() + "\""
				+ "}, "
				+ "\"spot2\": {"
				+ "\"x\": " + spot2.getHexLoc().getX() + ", "
				+ "\"y\": " + spot2.getHexLoc().getY() + ", "
				+ "\"direction\": \"" + spot2.getDir().toString() + "\""
				+ "}}");
	}

	@Override
	public String monopoly(ResourceType resource, int playerIndex) {
		return post("moves/Monopoly", "{\"type\": \"Monopoly\", \"resource\": \"" + resource.toString() 
				+ "\", \"playerIndex\": " + playerIndex + "}");
	}

	@Override
	public String monument(int playerIndex) {
		return post("moves/Monument", "{\"type\": \"Monument\", \"playerIndex\": " + playerIndex + "}");
	}
}
