/**
 * 
 */
package client;

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
	 */
	public ServerProxy(String HOST, String PORT) {
		this.HOST = HOST;
		this.PORT = PORT;
	}	
	
	/**
	 * @return the hOST
	 */
	public String getHOST() {
		return HOST;
	}

	/**
	 * @return the pORT
	 */
	public String getPORT() {
		return PORT;
	}

	public boolean userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean userRegister(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public String gamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gamesCreate(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean gamesJoin(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean gamesSave(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean gamesLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	public String gamesModel(String version) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gamesReset() {
		// TODO Auto-generated method stub
		return false;
	}

	public String gamesCommandsGet() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gamesCommandsPost(String commandList) {
		// TODO Auto-generated method stub
		return false;
	}

	public String gamesListAI() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean gamesAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean utilChangLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean sendChat(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean discardCards(ResourceHand discardedCards) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rollNumber(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean buildRoad(boolean free, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean buildSettlement(boolean free, VertexLocation vertexLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean buildCity(VertexLocation vertexLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean offerTrade(ResourceHand offer, playerIndex receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean maritimeTrade(int ratio, Resource inputResource,
			Resource outputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean robPlayer(HexLocation location, playerIndex victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean soldier(HexLocation location, playerIndex victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean yearOfPlenty(Resource resource1, Resource resource2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean monopoly(Resource resource) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean monument() {
		// TODO Auto-generated method stub
		return false;
	}

}
