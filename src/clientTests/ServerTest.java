package clientTests;

import static org.junit.Assert.assertNotEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;

import model.EdgeLocation;
import model.Resource;
import model.ServerProxy;

import org.junit.Test;

public class ServerTest {

	@Test
	public void roughTestOfLiveServer() throws MalformedURLException {
		ServerProxy proxy = new ServerProxy("localhost", "8081");
		assertNotEquals(proxy.userLogin("Sam", "sam"), "Error");
		assertNotEquals(proxy.gamesJoin(0, "orange"), "Error");
		assertNotEquals(proxy.gamesModel(), "Error");
		
		
		assertNotEquals(proxy.sendChat(0, "hi"), "Error");
		assertNotEquals(proxy.acceptTrade(0, true), "Error");
		assertNotEquals(proxy.discardCards(0, new ArrayList<Resource>()), "Error");
		assertNotEquals(proxy.rollNumber(0, 3), "Error");
		assertNotEquals(proxy.buildRoad(0, new EdgeLocation(), true), "Error");
		assertNotEquals(proxy.buildSettlement(0, new vertexLocation, true), "Error");
		assertNotEquals(proxy.buildCity(0, vertexLocation, true), "Error");
		assertNotEquals(proxy.offerTrade(0, offer), "Error");
		assertNotEquals(proxy.maritimeTrade(0, ratio, Resource inputResource, Resource outputResource), "Error");
		assertNotEquals(proxy.robPlayer(0, victimIndex, HexLocation location), "Error");
		assertNotEquals(proxy.finishTurn(0), "Error");
		assertNotEquals(proxy.buyDevCard(0), "Error");
		assertNotEquals(proxy.soldier(0, victimIndex, HexLocation location), "Error");
		assertNotEquals(proxy.yearOfPlenty(0, Resource resource1, Resource resource2), "Error");
		assertNotEquals(proxy.roadBuilding(0, EdgeLocation spot1, EdgeLocation spot2), "Error");
		assertNotEquals(proxy.monopoly(Resource resource, 0), "Error");
		assertNotEquals(proxy.monument(0), "Error");
	}

}
