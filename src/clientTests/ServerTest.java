package clientTests;

import static org.junit.Assert.assertNotEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;

import model.EdgeLocation;
import model.Resource;
import model.ServerProxy;
import model.TradeOffer;

import org.junit.Test;

import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ServerTest {

	@Test
	public void roughTestOfLiveServer() throws MalformedURLException {
		ServerProxy proxy = new ServerProxy("localhost", "8081");
		assertNotEquals(proxy.userLogin("Sam", "sam"), "Error");
		assertNotEquals(proxy.gamesJoin(0, "orange"), "Error");
		assertNotEquals(proxy.gamesModel(""), "Error");
		
		
		assertNotEquals(proxy.sendChat(0, "hi"), "Error");		
//		assertNotEquals(proxy.offerTrade(0, new TradeOffer()), "Error");
//		assertNotEquals(proxy.acceptTrade(0, true), "Error");
		assertNotEquals(proxy.discardCards(0, new ArrayList<Resource>()), "Error");
		assertNotEquals(proxy.rollNumber(0, 3), "Error");
//		assertNotEquals(proxy.buildRoad(0, new EdgeLocation(), true), "Error");
//		assertNotEquals(proxy.buildSettlement(0, new VertexLocation(new HexLocation(0, 0), VertexDirection.East), "true"), "Error");
//		assertNotEquals(proxy.buildCity(0, new VertexLocation(new HexLocation(0, 0), VertexDirection.East), "true"), "Error");
//		assertNotEquals(proxy.maritimeTrade(0, 2, new Resource(0), new Resource(0)), "Error");
//		assertNotEquals(proxy.robPlayer(0, 3, new model.HexLocation()), "Error");
		assertNotEquals(proxy.finishTurn(0), "Error");
		assertNotEquals(proxy.buyDevCard(0), "Error");
//		assertNotEquals(proxy.soldier(0, 3, new model.HexLocation()), "Error");
//		assertNotEquals(proxy.yearOfPlenty(0, new Resource(0), new Resource(0)), "Error");
//		assertNotEquals(proxy.roadBuilding(0, new EdgeLocation(), new EdgeLocation()), "Error");
//		assertNotEquals(proxy.monopoly(new Resource(0), 0), "Error");
//		assertNotEquals(proxy.monument(0), "Error");
	}

}
