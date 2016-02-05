/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
	
	/**
	 * 
	 * @param HOST The hostname of the server
	 * @param PORT The Port on which the server program is to be accessed
	 * @throws MalformedURLException 
	 * @pre HOST and PORT are not null and correct
	 * @post the ServerProxy is connected and ready to use
	 */
	public ServerProxy(String HOST, String PORT) throws MalformedURLException {
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

	public String post(String endPoint, String data)
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
			e.printStackTrace();
			return "Error";
		}
	}

	public String get(String endPoint)
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
		return post("games/create", "{\"randomTiles\": \"" + randomTiles 
				+ "\", \"randomNumbers\": \"" + randomNumbers 
				+ "\", \"randomPorts\": \"" + randomPorts 
				+ "\", \"name\": \"" + name + "\"}");
	}

	@Override
	public String gamesSave(int id, String name) {
		return post("games/save", "{\"id\": \"" + id + "\", \"name\": \"" + name	 + "\"}");
	}

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
	public String sendChat(int playerIndex, String message) {
		return post("moves/sendChat", "{\"type\": \"sendChat\", \"playerIndex\": " + playerIndex + ", \"content\": \"" + message + "\"}");
	}

	@Override
	public String acceptTrade(int playerIndex, boolean willAccept) {
		return post("moves/acceptTrade", "{\"type\": \"acceptTrade\", \"playerIndex\": " + playerIndex + ", \"willAccept\": \"" + willAccept + "\"}");
	}

	@Override
	public String discardCards(int playerIndex, List<Resource> discardedCards) {
		int wood = 0;
		int brick = 0;
		int sheep = 0;
		int wheat = 0;
		int ore = 0;
		for(Resource resource : discardedCards)
		{
			switch(resource.getType())
			{
				case 0:
					wood++;
					break;
				case 1:
					brick++;
					break;
				case 2:
					sheep++;
					break;
				case 3:
					wheat++;
					break;
				case 4:
					ore++;
					break;
			}
		}
		return post("moves/discardCards", "{\"type\": \"discardCards\", \"playerIndex\": " + playerIndex + ", "
				+ "\"discardedCards\": {"
				+ "\"brick\": \"" + brick + "\", "
				+ "\"ore\": \"" + ore + "\", "
				+ "\"sheep\": \"" + sheep + "\", "
				+ "\"wheat\": \"" + wheat + "\", "
				+ "\"wood\": \"" + wood + "\""
				+ "}}");
	}

	@Override
	public String rollNumber(int playerIndex, int number) {
		return post("moves/rollNumber", "{\"type\": \"rollNumber\", \"playerIndex\": " + playerIndex + ", \"number\": \"" + number + "\"}");
	}

	@Override
	public String buildRoad(int playerIndex, EdgeLocation roadLocation, boolean free) {
		return post("moves/buildRoad", "{\"type\": \"buildRoad\", \"playerIndex\": " + playerIndex + ", "
				+ "\"roadLocation\": {"
				+ "\"x\": \"" + roadLocation.getX() + "\", "
				+ "\"y\": \"" + roadLocation.getY() + "\", "
				+ "\"direction\": \"" + roadLocation.getDirection() + "\", "
				+ "}, "
				+ "\"free\": \"" + free + "\"}");
	}

	@Override
	public String buildSettlement(int playerIndex, shared.locations.VertexLocation vertexLocation, String free) {
		return post("moves/buildSettlement", "{\"type\": \"buildSettlement\", \"playerIndex\": " + playerIndex + ", "
				+ "\"vertexLocation\": {"
				+ "\"x\": \"" + vertexLocation.getHexLoc().getX() + "\", "
				+ "\"y\": \"" + vertexLocation.getHexLoc().getY() + "\", "
				+ "\"direction\": \"" + vertexLocation.getDir() + "\", "
				+ "}, "
				+ "\"free\": \"" + free + "\"}");
	}

	@Override
	public String buildCity(int playerIndex, shared.locations.VertexLocation vertexLocation, String free) {
		return post("moves/buildCity", "{\"type\": \"buildCity\", \"playerIndex\": " + playerIndex + ", "
				+ "\"vertexLocation\": {"
				+ "\"x\": \"" + vertexLocation.getHexLoc().getX() + "\", "
				+ "\"y\": \"" + vertexLocation.getHexLoc().getY() + "\", "
				+ "\"direction\": \"" + vertexLocation.getDir() + "\", "
				+ "}, "
				+ "\"free\": \"" + free + "\"}");
	}

	@Override
	public String offerTrade(int playerIndex, TradeOffer offer) {
		List<Integer> offerList = offer.getOffer();
		return post("moves/offerTrade", "{\"type\": \"offerTrade\", \"playerIndex\": " + playerIndex + ", "
				+ "\"offer\": {"
				+ "\"brick\": \"" + offerList.get(1) + "\", "
				+ "\"ore\": \"" + offerList.get(4) + "\", "
				+ "\"sheep\": \"" + offerList.get(2) + "\", "
				+ "\"wheat\": \"" + offerList.get(3) + "\", "
				+ "\"wood\": \"" + offerList.get(0) + "\""
				+ "}, "
				+ "\"receiver\": \"" + offer.getReceiver() + "\"," 
				+ "}");
	}

	@Override
	public String maritimeTrade(int playerIndex, int ratio, Resource inputResource, Resource outputResource) {
		return post("moves/maritimeTrade", "{\"type\": \"maritimeTrade\", \"playerIndex\": " + playerIndex 
				+ "\", \"ratio\": \"" + ratio + "\", "
				+ "\", \"inputResource\": \"" + inputResource.getType() + "\", "
				+ "\", \"outputResource\": \"" + outputResource.getType() + "\""
				+ "}");
	}

	@Override
	public String robPlayer(int playerIndex, int victimIndex, HexLocation location) {
		return post("moves/robPlayer", "{\"type\": \"robPlayer\", \"playerIndex\": " + playerIndex 
				+ "\", \"victimIndex\": " + victimIndex + ", "
				+ "\", \"location\": {"
				+ "\", \"x\": \"" + location.getX() + "\", "
				+ "\", \"y\": \"" + location.getY() + "\""
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
		return post("moves/soldier", "{\"type\": \"soldier\", \"playerIndex\": " + playerIndex 
				+ "\", \"victimIndex\": " + victimIndex + ", "
				+ "\", \"location\": {"
				+ "\", \"x\": \"" + location.getX() + "\", "
				+ "\", \"y\": \"" + location.getY() + "\""
				+ "}}");
	}
	
	@Override
	public String yearOfPlenty(int playerIndex, Resource resource1, Resource resource2) {
		return post("moves/yearOfPlenty", "{\"type\": \"yearOfPlenty\", \"playerIndex\": " + playerIndex 
				+ "\", \"resource1\": \"" + resource1 + "\""
				+ "\", \"resource2\": \"" + resource2 + "\""
				+ "}");
	}

	@Override
	public String roadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) {
		return post("moves/roadBuilding", "{\"type\": \"roadBuilding\", \"playerIndex\": " + playerIndex + ", "
				+ "\"spot1\": {"
				+ "\"x\": \"" + spot1.getX() + "\", "
				+ "\"y\": \"" + spot1.getY() + "\", "
				+ "\"direction\": \"" + spot1.getDirection() + "\", "
				+ "}, "
				+ "\"spot2\": {"
				+ "\"x\": \"" + spot2.getX() + "\", "
				+ "\"y\": \"" + spot2.getY() + "\", "
				+ "\"direction\": \"" + spot2.getDirection() + "\", "
				+ "}}");
	}

	@Override
	public String monopoly(Resource resource, int playerIndex) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return false;
	}

	/**
	 * Checks the model to see if the client can log in
	 * @pre None
	 * @post True if client can perform userLogin
	 * @return Whether the action is possible
	 */
	public boolean canUserLogin() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can register a new user
	 * @pre None
	 * @post True if client can perform userRegister
	 * @return Whether the action is possible
	 */
	public boolean canUserRegister() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can get a list of games
	 * @pre None
	 * @post True if client can perform gamesList
	 * @return Whether the action is possible
	 */
	public boolean canGamesList() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can create a game
	 * @pre None
	 * @post True if client can perform gamesCreate
	 * @return Whether the action is possible
	 */
	public boolean canGamesCreate() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can join a game
	 * @pre None
	 * @post True if client can perform gamesJoin
	 * @return Whether the action is possible
	 */
	public boolean canGamesJoin() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can save a game
	 * @pre None
	 * @post True if client can perform gamesSave
	 * @return Whether the action is possible
	 */
	public boolean canGamesSave() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can load a game
	 * @pre None
	 * @post True if client can perform gamesLoad
	 * @return Whether the action is possible
	 */
	public boolean canGamesLoad() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can get the current model ID
	 * @pre None
	 * @post True if client can perform gameModel
	 * @return Whether the action is possible
	 */
	public boolean canGameModel() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can reset a game
	 * @pre None
	 * @post True if client can perform gamesReset
	 * @return Whether the action is possible
	 */
	public boolean canGamesReset() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can get a list of commands executed for the game
	 * @pre None
	 * @post True if client can perform gamesCommandsGet
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommandsGet() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can post a list of command for the current game
	 * @pre None
	 * @post True if client can perform gamesCommandsPost
	 * @return Whether the action is possible
	 */
	public boolean canGamesCommansPost() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can list AI players
	 * @pre None
	 * @post True if client can perform gamesListAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesListAI() {
		//TO-DO
		return false;
	}
	
	/**
	 * Checks the model to see if the client can add an AI
	 * @pre None
	 * @post True if client can perform gamesAddAI
	 * @return Whether the action is possible
	 */
	public boolean canGamesAddAI() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can change the logging level
	 * @pre None
	 * @post True if client can perform utilChangeLogLevel
	 * @return Whether the action is possible
	 */
	public boolean canUtilChangeLogLevel() {
		return post("moves/Monopoly", "{\"type\": \"Monopoly\", \"resource\": \"" + resource 
				+ "\", \"playerIndex\": " + playerIndex + "}");
	}

	@Override
	public String monument(int playerIndex) {
		return post("moves/Monument", "{\"type\": \"Monument\", \"playerIndex\": " + playerIndex + "}");
	}

}
