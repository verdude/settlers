package clientTests;

import model.*;
import model.EdgeValue;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientFacadeTest {

	private ClientFacade facade;
	private ClientFacade mockFacade;
	private Player player;
	
	@Before
	public void setUp() throws MalformedURLException {
		mockFacade = ClientFacade.getSingleton(MockServerProxy.getSingleton("localhost", "8081"));
		facade = ClientFacade.getSingleton(ServerProxy.getSingleton("localhost", "8081"));
		player = new Player("test1", CatanColor.BLUE, 0);
		player.setHasRolled(true);
		player.setResources(new ResourceList(5));
		DevCardList devCardList = new DevCardList();
		devCardList.setMonopoly(1);
		devCardList.setMonument(1);
		devCardList.setRoadBuilding(1);
		devCardList.setSoldier(1);
		devCardList.setYearOfPlenty(1);
		player.setOldDevCards(devCardList);
		try {
			ClientFacade.getSingleton().getClientModel().getPlayers()[0] = player;
			ClientFacade.getSingleton().getClientModel().getTurnTracker().setCurrentTurn(0);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getVersionTest() {
		assertNotEquals(facade.getVersion(), 0);
	}

	//Throws exception if something fails
	@Test
	public void updateModelTest() {
		try {
			facade.updateModel(ClientFacade.getSingleton().getClientModel());
		} catch (ClientException e) {
			e.printStackTrace();
		}
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
		Player player2 = new Player("test2", CatanColor.WHITE, 1);
		player.setHasRolled(true);
		player.setResources(new ResourceList(5));
		try {
			ClientFacade.getSingleton().getClientModel().getPlayers()[1] = player2;
		} catch (ClientException e) {
			e.printStackTrace();
		}

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
		assertTrue(facade.offerTrade(trade));
		assertTrue(facade.acceptTrade(true));
	}

	@Test
	public void discardCardsTest() {
		assertTrue(facade.discardCards(new ArrayList<ResourceType>()));
	}

	@Test
	public void rollNumberTest() {
		assertTrue(facade.rollNumber() < 13 && facade.rollNumber() > 1);

	}

	@Test
	public void buildRoadTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertTrue(facade.buildRoad(edgeValue, "true"));
	}

	@Test
	public void buildSettlementTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);
		assertTrue(facade.buildSettlement(vertexObject, "true"));
	}

	@Test
	public void buildCityTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);

		Settlement settlement = new Settlement();
		settlement.setPlayerIndex(player.getPlayerID());
		settlement.setLocation(vertexObject);
		try {
			ClientFacade.getSingleton().getClientModel().getMap().getSettlements().add(settlement.getLocation());
			ClientFacade.getSingleton().getContext();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		assertTrue(facade.buildCity(vertexObject));

	}

	@Test
	public void maritimeTradeTest() {
		assertTrue(facade.maritimeTrade(2, ResourceType.BRICK, ResourceType.ORE));
	}

	@Test
	public void robPlayerTest() {
		assertTrue(facade.robPlayer(3, new HexLocation(0, 0)));
	}

	@Test
	public void finishTurnTest() {
		assertTrue(facade.finishTurn());
	}

	@Test
	public void buyDevCardTest() {
		assertTrue(facade.buyDevCard());
	}

	@Test
	public void soldierTest() {
		assertTrue(facade.soldier(1, new HexLocation(0, 0)));
	}

	@Test
	public void yearOfPlentyTest() {
		assertTrue(facade.yearOfPlenty(ResourceType.BRICK, ResourceType.ORE));
	}

	@Test
	public void roadBuildingTest() {
		EdgeValue edgeValue =  new EdgeValue();
		edgeValue.setLocation(new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
		edgeValue.setOwner(0);
		assertTrue(facade.roadBuilding(new shared.locations.EdgeLocation(new HexLocation(0,0), EdgeDirection.North),
				new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North)));
	}
	
	@Test
	public void monopolyTest() {
		assertTrue(facade.monopoly(ResourceType.BRICK, 0));
	}
	
	@Test
	public void monumentTest() {
		assertTrue(facade.monument());
	}
}
