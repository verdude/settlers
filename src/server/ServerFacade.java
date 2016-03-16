package server;

import java.util.List;

import model.EdgeValue;
import model.IFacade;
import model.TradeOffer;
import model.VertexObject;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;

/**
 * This is the class that contains all of the commands which are to be done on the server model.
 * View the javadoc for IFacade for descriptions.
 * @author S. Jacob Powell
 *
 */

public class ServerFacade implements IFacade{

	@Override
	public boolean userLogin(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userRegister(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String gamesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gamesCreate(String randomTiles, String randomNumbers,
			String randomPorts, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean gamesJoin(int ID, String color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gamesSave(int ID, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gamesLoad(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendChat(String playerName, String message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean acceptTrade(boolean willAccept) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean discardCards(List<ResourceType> discardedCards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int rollNumber(int number) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean buildRoad(EdgeValue roadLocation, String free) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildSettlement(VertexObject vertexObject, String free) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buildCity(VertexObject vertexObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerTrade(TradeOffer offer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean maritimeTrade(int ratio, ResourceType inputResource,
			ResourceType outputResource) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean robPlayer(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTurn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean buyDevCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean soldier(int victimIndex, HexLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean roadBuilding(EdgeLocation spot1, EdgeLocation spot2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean monopoly(ResourceType resource, int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean monument() {
		// TODO Auto-generated method stub
		return false;
	}

	
}
