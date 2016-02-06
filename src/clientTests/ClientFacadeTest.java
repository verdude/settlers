package clientTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import model.ClientFacade;
import model.ClientModel;
import model.EdgeValue;
import model.MockServerProxy;
import model.ServerProxy;
import model.TradeOffer;
import model.VertexObject;

import org.junit.Before;
import org.junit.Test;

import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ClientFacadeTest {

	private ClientFacade facade;
	private ClientFacade mockFacade;
	
	@Before
	public void setUp() throws MalformedURLException {
		mockFacade = ClientFacade.getSingleton(MockServerProxy.getSingleton("localhost", "8081"));
		facade = ClientFacade.getSingleton(ServerProxy.getSingleton("localhost", "8081"));
	}

	@Test
	public void getVersionTest() {
		assertNotEquals(facade.getVersion(), 0);
	}

	//Throws exception if something fails
	@Test
	public void updateModelTest() {
		facade.updateModel(new ClientModel());
	}

	@Test
	public void userLoginTest() {
		assertTrue(facade.userLogin("Sam", "sam"));
	}

	@Test
	public void userRegisterTest() {
		assertTrue(facade.userRegister("newUser", "newPass"));
	}

	@Test
	public void gamesListTest() {
		assertEquals("[\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"Default Game\",\r\n" + 
				"    \"id\": 0,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Sam\",\r\n" + 
				"        \"id\": 0\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"blue\",\r\n" + 
				"        \"name\": \"Brooke\",\r\n" + 
				"        \"id\": 1\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"red\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Mark\",\r\n" + 
				"        \"id\": 11\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"AI Game\",\r\n" + 
				"    \"id\": 1,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"white\",\r\n" + 
				"        \"name\": \"Squall\",\r\n" + 
				"        \"id\": -2\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"purple\",\r\n" + 
				"        \"name\": \"Quinn\",\r\n" + 
				"        \"id\": -3\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Ken\",\r\n" + 
				"        \"id\": -4\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"title\": \"Empty Game\",\r\n" + 
				"    \"id\": 2,\r\n" + 
				"    \"players\": [\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"orange\",\r\n" + 
				"        \"name\": \"Sam\",\r\n" + 
				"        \"id\": 0\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"blue\",\r\n" + 
				"        \"name\": \"Brooke\",\r\n" + 
				"        \"id\": 1\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"red\",\r\n" + 
				"        \"name\": \"Pete\",\r\n" + 
				"        \"id\": 10\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"color\": \"green\",\r\n" + 
				"        \"name\": \"Mark\",\r\n" + 
				"        \"id\": 11\r\n" + 
				"      }\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"]", mockFacade.gamesList());
	}

	@Test
	public void gamesCreateTest() {
		assertEquals("{\r\n" + 
				"  \"title\": \"sdfsfr\",\r\n" + 
				"  \"id\": 3,\r\n" + 
				"  \"players\": [\r\n" + 
				"    {},\r\n" + 
				"    {},\r\n" + 
				"    {},\r\n" + 
				"    {}\r\n" + 
				"  ]\r\n" + 
				"}", mockFacade.gamesCreate("true", "true", "true", "sdfsfr"));
	}

	@Test
	public void gamesJoinTest() {
		assertTrue(facade.gamesJoin(0, "orange"));
	}	

	@Test
	public void gamesSaveLoadTest() {
		assertTrue(facade.gamesSave(0, "orangeGame"));
		assertTrue(facade.gamesLoad("orangeGame"));
	}

	@Test
	public void sendChatTest() {
		assertTrue(facade.sendChat("Sam", "orangeMessage"));
	}

	@Test
	public void tradeTest() {
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
		assertTrue(facade.offerTrade(0, trade));
		assertTrue(facade.acceptTrade(0, true));
	}

	@Test
	public void discardCardsTest() {
		assertTrue(facade.discardCards(0, new ArrayList<ResourceType>()));
	}

	@Test
	public void rollNumberTest() {
		assertTrue(facade.rollNumber(0));
	}

	@Test
	public void buildRoadTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertTrue(facade.buildRoad(0, edgeValue, "true"));
	}

	@Test
	public void buildSettlementTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);
		assertTrue(facade.buildSettlement(0, vertexObject, "true"));
	}

	@Test
	public void buildCityTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);
		assertTrue(facade.buildCity(0, vertexObject));
	}

	@Test
	public void maritimeTradeTest() {
		assertTrue(facade.maritimeTrade(0, 2, ResourceType.BRICK, ResourceType.ORE));
	}

	@Test
	public void robPlayerTest() {
		assertTrue(facade.robPlayer(0, 3, new HexLocation(0, 0)));
	}

	@Test
	public void finishTurnTest() {
		assertTrue(facade.finishTurn(0));
	}

	@Test
	public void buyDevCardTest() {
		assertTrue(facade.buyDevCard(0));
	}

	@Test
	public void soldierTest() {
		assertTrue(facade.soldier(0, 1, new HexLocation(0, 0)));
	}

	@Test
	public void yearOfPlentyTest() {
		assertTrue(facade.yearOfPlenty(0, ResourceType.BRICK, ResourceType.ORE));
	}

	@Test
	public void roadBuildingTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertTrue(facade.roadBuilding(0, new EdgeLocation(new HexLocation(0,0), EdgeDirection.North),
				new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North)));
	}
	
	@Test
	public void monopolyTest() {
		assertTrue(facade.monopoly(ResourceType.BRICK, 0));
	}
	
	@Test
	public void monumentTest() {
		assertTrue(facade.monument(0));
	}
}
