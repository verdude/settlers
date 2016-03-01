package clientTests;

import client.data.PlayerInfo;
import model.*;
import org.junit.After;
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

	private  ClientFacade facade;
	private  ClientFacade mockFacade;
	private  Player player;

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
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.setColor(CatanColor.BLUE);
		playerInfo.setId(0);
		playerInfo.setName("Sean");
		playerInfo.setPlayerIndex(0);
		facade.setLocalPlayer(playerInfo);
		Player[] playerList = new Player[4];
		Player player1 = new Player("sean",CatanColor.WHITE,0);
		player1.setPlayerID(0);
		player1.setResources(new ResourceList(15));
		playerList[0] = player1;
		player1.setHasRolled(true);

		TurnTracker turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);

		facade.getClientModel().setTurnTracker(turnTracker);
		turnTracker.setCurrentTurn(0);

		facade.getClientModel().setPlayers(playerList);

		try {
			ClientFacade.getSingleton().getClientModel().getPlayers()[0] = player;
			ClientFacade.getSingleton().getClientModel().getTurnTracker().setCurrentTurn(0);
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown(){
		facade = null;
		mockFacade = null;
		player = null;
//		try {
//			setUp();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}


	}

	@Test
	public void getVersionTest() {
		assertNotEquals(facade.getVersion(), -1);
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
		assertEquals("{\"deck\":{\"yearOfPlenty\":2,\"monopoly\":2,\"soldier\":14,\"roadBuilding\":2,\"monument\":5},\"map\":{\"hexes\":[{\"vertexLocation\":{\"x\":0,\"y\":-2}},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":1,\"y\":-2},\"number\":4},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":2,\"y\":-2},\"number\":11},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":-1,\"y\":-1},\"number\":8},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":0,\"y\":-1},\"number\":3},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":1,\"y\":-1},\"number\":9},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":2,\"y\":-1},\"number\":12},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":-2,\"y\":0},\"number\":5},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":-1,\"y\":0},\"number\":10},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":0,\"y\":0},\"number\":11},{\"resource\":\"brick\",\"vertexLocation\":{\"x\":1,\"y\":0},\"number\":5},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":2,\"y\":0},\"number\":6},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":-2,\"y\":1},\"number\":2},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":-1,\"y\":1},\"number\":9},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":0,\"y\":1},\"number\":4},{\"resource\":\"sheep\",\"vertexLocation\":{\"x\":1,\"y\":1},\"number\":10},{\"resource\":\"wood\",\"vertexLocation\":{\"x\":-2,\"y\":2},\"number\":6},{\"resource\":\"ore\",\"vertexLocation\":{\"x\":-1,\"y\":2},\"number\":3},{\"resource\":\"wheat\",\"vertexLocation\":{\"x\":0,\"y\":2},\"number\":8}],\"roads\":[{\"owner\":1,\"vertexLocation\":{\"direction\":\"S\",\"x\":-1,\"y\":-1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":-2}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"S\",\"x\":1,\"y\":-1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"S\",\"x\":0,\"y\":1}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"S\",\"x\":0,\"y\":0}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-2,\"y\":1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":0}}],\"cities\":[],\"settlements\":[{\"owner\":3,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":1}},{\"owner\":3,\"vertexLocation\":{\"direction\":\"SE\",\"x\":1,\"y\":-2}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"SW\",\"x\":0,\"y\":0}},{\"owner\":2,\"vertexLocation\":{\"direction\":\"SW\",\"x\":1,\"y\":-1}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-2,\"y\":1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SE\",\"x\":0,\"y\":1}},{\"owner\":1,\"vertexLocation\":{\"direction\":\"SW\",\"x\":-1,\"y\":-1}},{\"owner\":0,\"vertexLocation\":{\"direction\":\"SW\",\"x\":2,\"y\":0}}],\"radius\":3,\"ports\":[{\"ratio\":2,\"resource\":\"brick\",\"direction\":\"NE\",\"vertexLocation\":{\"x\":-2,\"y\":3}},{\"ratio\":3,\"direction\":\"SE\",\"vertexLocation\":{\"x\":-3,\"y\":0}},{\"ratio\":2,\"resource\":\"sheep\",\"direction\":\"NW\",\"vertexLocation\":{\"x\":3,\"y\":-1}},{\"ratio\":3,\"direction\":\"NW\",\"vertexLocation\":{\"x\":2,\"y\":1}},{\"ratio\":3,\"direction\":\"N\",\"vertexLocation\":{\"x\":0,\"y\":3}},{\"ratio\":2,\"resource\":\"ore\",\"direction\":\"S\",\"vertexLocation\":{\"x\":1,\"y\":-3}},{\"ratio\":3,\"direction\":\"SW\",\"vertexLocation\":{\"x\":3,\"y\":-3}},{\"ratio\":2,\"resource\":\"wheat\",\"direction\":\"S\",\"vertexLocation\":{\"x\":-1,\"y\":-2}},{\"ratio\":2,\"resource\":\"wood\",\"direction\":\"NE\",\"vertexLocation\":{\"x\":-3,\"y\":2}}],\"robber\":{\"x\":0,\"y\":-2}},\"players\":[{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":1,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":0,\"playerIndex\":0,\"name\":\"Sam\",\"color\":\"red\"},{\"resources\":{\"brick\":1,\"wood\":0,\"sheep\":1,\"wheat\":0,\"ore\":1},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":1,\"playerIndex\":1,\"name\":\"Brooke\",\"color\":\"blue\"},{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":1,\"ore\":0},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":10,\"playerIndex\":2,\"name\":\"Pete\",\"color\":\"red\"},{\"resources\":{\"brick\":0,\"wood\":1,\"sheep\":1,\"wheat\":0,\"ore\":1},\"oldDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"newDevCards\":{\"yearOfPlenty\":0,\"monopoly\":0,\"soldier\":0,\"roadBuilding\":0,\"monument\":0},\"roads\":13,\"cities\":4,\"settlements\":3,\"soldiers\":0,\"victoryPoints\":2,\"monuments\":0,\"playedDevCard\":false,\"discarded\":false,\"playerID\":11,\"playerIndex\":3,\"name\":\"Mark\",\"color\":\"green\"}],\"log\":{\"lines\":[{\"source\":\"Sam\",\"message\":\"Sam built a road\"},{\"source\":\"Sam\",\"message\":\"Sam built a settlement\"},{\"source\":\"Sam\",\"message\":\"Sam's turn just ended\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a road\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a settlement\"},{\"source\":\"Brooke\",\"message\":\"Brooke's turn just ended\"},{\"source\":\"Pete\",\"message\":\"Pete built a road\"},{\"source\":\"Pete\",\"message\":\"Pete built a settlement\"},{\"source\":\"Pete\",\"message\":\"Pete's turn just ended\"},{\"source\":\"Mark\",\"message\":\"Mark built a road\"},{\"source\":\"Mark\",\"message\":\"Mark built a settlement\"},{\"source\":\"Mark\",\"message\":\"Mark's turn just ended\"},{\"source\":\"Mark\",\"message\":\"Mark built a road\"},{\"source\":\"Mark\",\"message\":\"Mark built a settlement\"},{\"source\":\"Mark\",\"message\":\"Mark's turn just ended\"},{\"source\":\"Pete\",\"message\":\"Pete built a road\"},{\"source\":\"Pete\",\"message\":\"Pete built a settlement\"},{\"source\":\"Pete\",\"message\":\"Pete's turn just ended\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a road\"},{\"source\":\"Brooke\",\"message\":\"Brooke built a settlement\"},{\"source\":\"Brooke\",\"message\":\"Brooke's turn just ended\"},{\"source\":\"Sam\",\"message\":\"Sam built a road\"},{\"source\":\"Sam\",\"message\":\"Sam built a settlement\"},{\"source\":\"Sam\",\"message\":\"Sam's turn just ended\"}]},\"chat\":{\"lines\":[]},\"bank\":{\"brick\":23,\"wood\":21,\"sheep\":20,\"wheat\":22,\"ore\":22},\"turnTracker\":{\"status\":\"Rolling\",\"currentTurn\":0,\"longestRoad\":-1,\"largestArmy\":-1},\"winner\":-1,\"version\":0}",
				mockFacade.gamesList());
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

		facade.getClientModel().setTradeOffer(trade);
		assertTrue(facade.offerTrade(trade));
	}

	@Test
	public void discardCardsTest() {
		assertTrue(facade.discardCards(new ArrayList<ResourceType>()));
	}

	@Test
	public void rollNumberTest() {
		assertTrue(facade.rollNumber(12) < 13 && facade.rollNumber(2) > 1);

	}

	@Test
	public void buildRoadTest() {
//		EdgeValue edgeValue =  new EdgeValue();
//		edgeValue.setLocation(new EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
//		edgeValue.setOwner(0);
//		assertTrue(facade.buildRoad(edgeValue, "true"));
	}

	@Test
	public void buildSettlementTest() {
//		EdgeValue edgeValue =  new EdgeValue();
//		edgeValue.setLocation(new shared.locations.EdgeLocation(new HexLocation(0, 0), EdgeDirection.North));
//		edgeValue.setOwner(0);
//		assertTrue(facade.buildRoad(edgeValue, "true"));
//
//
//		VertexObject vertexObject = new VertexObject();
//		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
//		vertexObject.setLocation(new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast));
//
//		vertexObject.setOwner(0);
//		assertTrue(facade.buildSettlement(vertexObject, "true"));


	}

	@Test
	public void buildCityTest() {
		VertexObject vertexObject = new VertexObject();
		vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0, 0), VertexDirection.NorthEast));
		vertexObject.setOwner(0);

		Settlement settlement = new Settlement();
		settlement.setPlayerIndex(0);
		settlement.setLocation(vertexObject);
		try {
			ClientFacade.getSingleton().getClientModel().getMap().getSettlements().add(settlement.getLocation());
			ClientFacade.getSingleton().getContext();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		vertexObject.setLocation(new EdgeLocation(new HexLocation(0,0), EdgeDirection.NorthEast));

		assertTrue(facade.buildCity(vertexObject));

	}

	@Test
	public void maritimeTradeTest() {
		assertTrue(facade.maritimeTrade(2, ResourceType.BRICK, ResourceType.ORE));
	}

	@Test
	public void robPlayerTest() {
		//assertTrue(facade.robPlayer(0, new HexLocation(0, 0)));
	}

	@Test
	public void finishTurnTest() {
		assertFalse(facade.finishTurn());
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
