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
import java.net.URLDecoder;
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
	private String decodedCookie;
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
		decodedCookie = "";
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
	
	@SuppressWarnings("deprecation")
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
			decodedCookie = URLDecoder.decode(encodedCookie);
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
	public String gamesJoin(int ID, String color) {
		try {
			byte[] postData = ("{\"id\": \"" + ID + "\", \"color\": \"" + color + "\"}").getBytes(StandardCharsets.UTF_8);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesCreate(boolean randomTiles, boolean randomNumbers, boolean randomPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesSave(int ID, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesLoad(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public String gamesModel() {
		return get("game/model");
	}

	@Override
	public String gamesReset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesCommandsGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesCommandsPost(String commandList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesListAI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesAddAI(String AIType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String utilChangeLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendChat(int playerIndex, String message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acceptTrade(int playerIndex, boolean willAccept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String discardCards(int playerIndex, List<Resource> discardedCards) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollNumber(int playerIndex, int number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildRoad(int playerIndex, EdgeLocation roadLocation,
			boolean free) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSettlement(int playerIndex, VertexObject vertexObject,
			String free) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildCity(int playerIndex, VertexObject vertexObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offerTrade(int playerIndex, TradeOffer offer, int receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String maritimeTrade(int playerIndex, int ratio,
			Resource inputResource, Resource outputResource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String robPlayer(int playerIndex, int victimIndex,
			HexLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finishTurn(int playerIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyDevCard(int playerIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String soldier(int playerIndex, int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String yearOfPlenty(int playerIndex, Resource resource1,
			Resource resource2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roadBuilding(int playerIndex, EdgeLocation spot1,
			EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String monopoly(Resource resource, int playerIndex) {
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
		//TO-DO
		return false;
	}

	@Override
	public String monument(int playerIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
