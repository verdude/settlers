/**
 * 
 */
package client;

import java.util.List;

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
	
	/**
	 * 
	 * @param HOST The hostname of the server
	 * @param PORT The Port on which the server program is to be accessed
	 * @pre HOST and PORT are not null and correct
	 * @post the ServerProxy is connected and ready to use
	 */
	public ServerProxy(String HOST, String PORT) {
		this.HOST = HOST;
		this.PORT = PORT;
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

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean userRegister(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public String gamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesCreate(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesJoin(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesSave(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public String gamesModel(String version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesReset() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public String gamesCommandsGet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesCommandsPost(String commandList) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public String gamesListAI() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean gamesAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean sendChat(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean discardCards(List<Resource> discardedCards) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean rollNumber(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean buildRoad(boolean free, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean buildSettlement(boolean free, VertexObject vertextObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean buildCity(VertexObject vertextObject) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean offerTrade(TradeOffer offer, int receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean maritimeTrade(int ratio, Resource inputResource,
			Resource outputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean robPlayer(HexLocation location, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean soldier(HexLocation location, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean yearOfPlenty(Resource resource1, Resource resource2) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean monopoly(Resource resource) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean monument() {
		// TODO Auto-generated method stub
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
	 * @post True if client can perform gamesModel
	 * @return Whether the action is possible
	 */
	public boolean canGamesModel() {
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
	public boolean utilChangeLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		return false;
	}

}
