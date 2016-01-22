package client;

/**
 * @author S Jacob Powell
 *
 *	This is the mock Client implementation of the IProxy interface.
 *	It will do the fake client side server querying and posting '
 *	required for the testing of the playing of the game
 *
 */
public class MockServerProxy implements IProxy {
	private final String HOST;
	private final String PORT;
	
	/**
	 * 
	 * @param HOST The hostname of the server
	 * @param PORT The Port on which the server program is to be accessed
	 * @pre HOST and PORT are not null and correct
	 * @post the ServerProxy is connected and ready to use
	 */
	public MockServerProxy(String HOST, String PORT) {
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
	 * Inherited from implimented class
	 */
	public boolean userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean userRegister(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public String gamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesCreate(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesJoin(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesSave(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public String gamesModel(String version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesReset() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public String gamesCommandsGet() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesCommandsPost(String commandList) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public String gamesListAI() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean gamesAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean sendChat(String message) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean discardCards(ResourceHand discardedCards) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean rollNumber(int number) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean buildRoad(boolean free, EdgeLocation roadLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean buildSettlement(boolean free, VertexLocation vertexLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean buildCity(VertexLocation vertexLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean offerTrade(ResourceHand offer, int receiver) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean maritimeTrade(int ratio, Resource inputResource,
			Resource outputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean robPlayer(HexLocation location, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean soldier(HexLocation location, int victimIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean yearOfPlenty(Resource resource1, Resource resource2) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean monopoly(Resource resource) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean monument() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canUserLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canUserRegister() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesList() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesJoin() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesSave() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesModel() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesReset() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesCommandsGet() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesCommansPost() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesListAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canGamesAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canUtilChangeLogLevel() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean utilChangeLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canSendChat() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canAcceptTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canDiscardCards() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canRollNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canBuildRoad() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canBuildSettlement() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canBuildCity() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canOfferTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canMaritimeTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canRobPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canSoldier() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canRoadBuilding() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implimented class
	 */
	public boolean canMonument() {
		// TODO Auto-generated method stub
		return false;
	}

}
