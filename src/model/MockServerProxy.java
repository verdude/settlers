package model;

import java.util.List;

import shared.locations.*;

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

	@Override
	public String userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return null;
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
	public String gamesCreate(boolean randomTiles, boolean randomNumbers,
			boolean randomPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesJoin(int ID, String color) {
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
	@Override
	public String gameModel(String version) {
		// TODO Auto-generated method stub
		return null;
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

	/**
	 * Inherited from implemented class
	 * @return Whether the method was a success
	 */
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
		return null;
	}

	@Override
	public String monument(int playerIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
