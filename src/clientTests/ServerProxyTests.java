package clientTests;

import static org.junit.Assert.assertNotEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import model.EdgeValue;
import model.ServerProxy;
import model.TradeOffer;
import model.VertexObject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ServerProxyTests {

	static ServerProxy proxy;
	@BeforeClass
	public static void setUp() throws MalformedURLException {
		proxy = ServerProxy.getSingleton("localhost", "8081");
	}
	
	@Before
	public void loginJoinGame() throws MalformedURLException {
		userLoginTest();
		gamesJoinTest();
	}

	@Test
	public void singletonTest() {
	}

	@Test
	public void userLoginTest() {		
		assertNotEquals(proxy.userLogin("Sam", "sam"), "Error");
	}

	@Test
	public void gamesJoinTest() {
		assertNotEquals(proxy.gamesJoin(0, "orange"), "Error");
	}

	@Test
	public void sendChatTest() {
		assertNotEquals(proxy.sendChat("Sam", "hi"), "Error");		
	}

	@Test
	public void discardCardsTest() {
		assertNotEquals(proxy.discardCards(0, new ArrayList<ResourceType>()), "Error");
	}

	@Test
	public void rollNumberTest() {
		assertNotEquals(proxy.rollNumber(0, 3), "Error");
	}

	@Test
	public void buildRoadTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertNotEquals(proxy.buildRoad(0, edgeValue, "true"), "Error");
	}

	@Test
	public void buildSettlementTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);
		assertNotEquals(proxy.buildSettlement(0, vertexObject, "true"), "Error");
	}

	@Test
	public void buildCityTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);
		assertNotEquals(proxy.buildCity(0, vertexObject), "Error");
	}

	@Test
	public void offerTradeTest() {
		List<Integer> resources = new ArrayList<Integer>();
		resources.add(2);
		resources.add(5);
		resources.add(0);
		resources.add(-2);
		resources.add(-2);
		TradeOffer trade = new TradeOffer();
		trade.setOffer(resources);
		trade.setReceiver(1);
		trade.setSender(0);
		assertNotEquals(proxy.offerTrade(0, trade), "Error");
		assertNotEquals(proxy.acceptTrade(0, true), "Error");
	}

	@Test
	public void maritimeTradeTest() {
		assertNotEquals(proxy.maritimeTrade(0, 2, ResourceType.BRICK, ResourceType.ORE), "Error");
	}

	@Test
	public void robPlayerTest() {
		assertNotEquals(proxy.robPlayer(0, 3, new HexLocation(0, 0)), "Error");
	}

	@Test
	public void finishTurnTest() {
		assertNotEquals(proxy.finishTurn(0), "Error");
	}

	@Test
	public void buyDevCardTest() {
		assertNotEquals(proxy.buyDevCard(0), "Error");
	}

	@Test
	public void soldierTest() {
		assertNotEquals(proxy.soldier(0, 3, new HexLocation(0, 0)), "Error");
	}

	@Test
	public void yearOfPlentyTest() {
		assertNotEquals(proxy.yearOfPlenty(0, ResourceType.BRICK, ResourceType.ORE), "Error");
	}

	@Test
	public void roadBuildingTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertNotEquals(proxy.roadBuilding(0, new shared.locations.EdgeLocation(new HexLocation(0,0), EdgeDirection.North),
												new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North)), "Error");
	}

	@Test
	public void monopolyTest() {
		assertNotEquals(proxy.monopoly(ResourceType.BRICK, 0), "Error");
	}

	@Test
	public void monumentTest() {
		assertNotEquals(proxy.monument(0), "Error");
	}
}
