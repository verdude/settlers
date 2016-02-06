package clientTests;

import static org.junit.Assert.assertNotEquals;

import java.net.MalformedURLException;
import java.util.ArrayList;

import model.EdgeValue;
import model.ServerProxy;
import model.VertexObject;

import org.junit.BeforeClass;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ServerProxyTests {

	ServerProxy proxy;
	
	@BeforeClass
	public void loginJoinGame() throws MalformedURLException {
		proxy = ServerProxy.getSingleton("localhost", "8081");
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
	public void userRegisterTest() {
		assertNotEquals(proxy.userRegister("NewUser", "NewPass"), "Error");
	}

	@Test
	public void gamesListTest() {
		assertNotEquals(proxy.gamesList(), "Error");
	}

	@Test
	public void gamesCreateTest() {
		assertNotEquals(proxy.gamesCreate("true", "true", "true", "TheNameGame"), "Error");
	}
	
	@Test
	public void gamesModelTest(String version) {
		assertNotEquals(proxy.gamesModel(""), "Error");
	}

	@Test
	public void gamesResetTest() {
		assertNotEquals(proxy.gamesReset(), "Error");
	}

	@Test
	public void gamesCommandsGetTest() {
		assertNotEquals(proxy.gamesCommandsGet(), "Error");
	}

	@Test
	public void gamesCommandsPostTest() {
		assertNotEquals(proxy.gamesCommandsPost("[]"), "Error");
	}

	@Test
	public void gamesListAITest() {
		assertNotEquals(proxy.gamesListAI(), "Error");
	}

	@Test
	public void gamesAddAITest() {
		assertNotEquals(proxy.gamesAddAI("LARGEST_ARMY"), "Error");
	}

	@Test
	public void utilChangeLogLevelTest() {
		assertNotEquals(proxy.utilChangeLogLevel("ALL"), "Error");
	}

	@Test
	public void sendChatTest() {
		assertNotEquals(proxy.sendChat(0, "hi"), "Error");		
	}

	@Test
	public void acceptTradeTest() {
		assertNotEquals(proxy.acceptTrade(0, true), "Error");
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
		edgeValue.setLocation(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertNotEquals(proxy.buildRoad(0, edgeValue, true), "Error");
	}

	@Test
	public void buildSettlementTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setLocation(new HexLocation(0, 0));
		 new VertexObject(new HexLocation(0, 0), VertexDirection.East)
		assertNotEquals(proxy.buildSettlement(0,, "true"), "Error");
	}

	@Test
	public void buildCityTest() {
//		assertNotEquals(proxy.buildCity(0, new VertexLocation(new HexLocation(0, 0), VertexDirection.East), "true"), "Error");
	}

	@Test
	public void offerTradeTest() {
//		assertNotEquals(proxy.offerTrade(0, new TradeOffer()), "Error");
	}

	@Test
	public void maritimeTradeTest() {
//		assertNotEquals(proxy.maritimeTrade(0, 2, new Resource(0), new Resource(0)), "Error");
	}

	@Test
	public void robPlayerTest() {
//		assertNotEquals(proxy.robPlayer(0, 3, new model.HexLocation()), "Error");
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
//		assertNotEquals(proxy.soldier(0, 3, new model.HexLocation()), "Error");
	}

	@Test
	public void yearOfPlentyTest() {
//		assertNotEquals(proxy.yearOfPlenty(0, new Resource(0), new Resource(0)), "Error");
	}

	@Test
	public void roadBuildingTest() {
//		assertNotEquals(proxy.roadBuilding(0, new EdgeLocation(), new EdgeLocation()), "Error");
	}

	@Test
	public void monopolyTest() {
//		assertNotEquals(proxy.monopoly(new Resource(0), 0), "Error");
	}

	@Test
	public void monumentTest() {
//		assertNotEquals(proxy.monument(0), "Error");
	}
}
