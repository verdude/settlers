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
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canUserLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canUserRegister() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesList() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesJoin() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesSave() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesModel() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesReset() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesCommandsGet() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesCommansPost() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesListAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canGamesAddAI() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean canUtilChangeLogLevel() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
	public boolean utilChangeLogLevel(String logLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canSendChat() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canAcceptTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canDiscardCards() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canRollNumber() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canBuildRoad() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canBuildSettlement() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canBuildCity() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canOfferTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canMaritimeTrade() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canRobPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canFinishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canBuyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canSoldier() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canYearOfPlenty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canRoadBuilding() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canMonopoly() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Inherited from implemented class
	 */
	public boolean canMonument() {
		// TODO Auto-generated method stub
		return false;
	}

}
