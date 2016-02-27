package clientTests;

import model.*;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.CatanColor;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ClientCanDoTests {

	private static  ClientModel model;
	private static GameMap map;

	private TurnTracker turnTracker;
	
	@Before
	public  void setUpBefore() throws Exception {
		model = new ClientModel();

		
		model.setDevCardList(new DevCardList());
		
		Settlement sett1 = new Settlement();
		VertexLocation sett1Loc = new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast);
		
		VertexObject sett1Vert = new VertexObject();
		sett1Vert.setOwner(0);
		sett1Vert.setLocation(sett1Loc);
		sett1.setLocation(sett1Vert);
		sett1.setPlayerId(0);
		
		
		Road road1 = new Road();
		
		EdgeValue road1Val = new EdgeValue();
		road1Val.setOwner(0);
		EdgeLocation road1Loc = new EdgeLocation(new HexLocation(1,-1), EdgeDirection.NorthWest);
		road1Val.setLocation(road1Loc);
		
		road1.setPlayerId(0);
		road1.setLocation(road1Val);
		
		
		map = new GameMap();
		
		map.getSettlementList().add(sett1);
		map.getRoadList().add(road1);
		
		model.setMap(map);
		
		Player[] playerList = new Player[4];
		Player player1 = new Player("sean",CatanColor.WHITE,0);
		player1.setPlayerID(0);
		player1.setResources(new ResourceList(15));
		playerList[0] = player1;
		player1.setHasRolled(true);
		
		turnTracker = new TurnTracker();
		turnTracker.setCurrentTurn(0);
		
		model.setPlayers(playerList);
		model.setTurnTracker(turnTracker);
		
		
		
	}



	@Test
	public void canBuildRoad() {
		Road road2 = new Road();
		
		EdgeValue road2Val = new EdgeValue();
		EdgeLocation road1Loc = new EdgeLocation(new HexLocation(1,-1), EdgeDirection.North);
		road2Val.setLocation(road1Loc);
		
		road2.setPlayerId(0);
		road2.setLocation(road2Val);

		assertTrue(model.canBuildRoad(0, road2.getLocation().getLocation()));
		road2.getLocation().setOwner(0);
		map.getRoadList().add(road2);
		assertFalse(model.canBuildRoad(0, road2.getLocation().getLocation()));


	}
	
	@Test
	public void canBuildSettlementTest() {
		Settlement sett2 = new Settlement();
		VertexLocation sett1Loc = new VertexLocation(new HexLocation(1,-1), VertexDirection.NorthEast);
		
		VertexObject sett2Vert = new VertexObject();
		//sett2Vert.setOwner(0);
		sett2Vert.setLocation(sett1Loc);
		sett2.setLocation(sett2Vert);
		sett2.setPlayerId(0);
				assertFalse(model.canBuildSettlement(0, sett2Vert.getLocation().getNormalizedLocation()));

		Road road2 = new Road();
		
		EdgeValue road2Val = new EdgeValue();
		EdgeLocation road1Loc = new EdgeLocation(new HexLocation(1,-1), EdgeDirection.North);
		road2Val.setLocation(road1Loc);
		road2.setPlayerId(0);
		road2.setLocation(road2Val);
		
		assertTrue(model.canBuildRoad(0, road2Val.getLocation()));
		road2.getLocation().setOwner(0);
		road2Val.setOwner(0);
		road2.setLocation(road2Val);
		map.getRoadList().add(road2);
		road2Val.setOwner(0);
		
		assertTrue(model.canBuildSettlement(0, sett2Vert.getLocation().getNormalizedLocation()));

		



	}
	@Test
	public void canBuildCityTest() {
		City city = new City();
		VertexLocation cityLoc = new VertexLocation(new HexLocation(1,-1), VertexDirection.NorthEast);
		
		VertexObject cityVert = new VertexObject();
		//sett2Vert.setOwner(0);
		cityVert.setLocation(cityLoc);
		city.setLocation(cityVert);
		city.setPlayerId(0);
		
		//no settlement
		assertFalse(model.canBuildCity(0, cityVert.getLocation()));

		cityLoc = new VertexLocation(new HexLocation(0,0), VertexDirection.NorthEast);

		cityVert = new VertexObject();
		//sett2Vert.setOwner(0);
		cityVert.setLocation(cityLoc);
		city.setLocation(cityVert);
		city.setPlayerId(0);
		assertTrue(model.canBuildCity(0, cityVert.getLocation()));

	}
	
	@Test
	public void canMaritimeTrade() {
		Port port = new Port();
		port.setResourceType(PortType.BRICK);
		port.setDirection(EdgeDirection.North);
		port.setLocation(new HexLocation(0,0));
		model.getMap().getPortList().add(port);
		
		assertTrue(model.canMaritimeTrade(0, ResourceType.BRICK));
		assertFalse(model.canMaritimeTrade(0, ResourceType.WOOD));


	}
	
	@Test
	public void canOfferTradeTest() {
		TradeOffer offer = new TradeOffer();
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(-1);
		offer.setOffer(list);
		offer.setReceiver(1);
		
		model.setTradeOffer(offer);
		List<Integer> requested = new ArrayList<>();
		List<Integer> offered = new ArrayList<>();
		
		//Checking the sort
		model.sortOffer(offer, offered, requested);
		assertTrue(offered.size() == 2);
		assertTrue(requested.size() == 1);
		
		assertTrue(model.canOfferTrade(0));
		for(int i = 0; i < 20; i++){
			list.add(2);
		}
		offer.setOffer(list);
		model.setTradeOffer(offer);
		assertFalse(model.canOfferTrade(0));
		


	}
	
	@Test
	public void canAcceptTrade() {
		TradeOffer offer = new TradeOffer();
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(-1);
		offer.setOffer(list);
		offer.setReceiver(0);
		
		model.setTradeOffer(offer);
		
		
		
		
		assertTrue(model.canAcceptTrade(0));
		
		


	}
	
	@Test
	public void canDiscardCardsTest(){
		assertTrue(model.canDiscardCards(0));
	}
	
	@Test
	public void canBuyDevCardTest(){
		assertTrue(model.canBuyDevCard(0));
		model.getPlayers()[0].setResources(new ResourceList(0));
		assertFalse(model.canBuyDevCard(0));
		
	}
	

	@Test
	public void canRollNumberTest(){
		model.getPlayers()[0].setHasRolled(false);
		assertTrue(model.canRollNumber(0));
		model.getPlayers()[0].setHasRolled(true);
		assertFalse(model.canRollNumber(0));		
	}
	
	@Test
	public void canFinishTurnTest(){
		assertTrue(model.canFinishTurn(0));
		model.getTurnTracker().setCurrentTurn(1);
		assertFalse(model.canFinishTurn(0));

	}
	@Test
	public void canYearOfPlentyTest(){
		assertFalse(model.canYearOfPlenty(0));

		DevCardList oldDevCards =new DevCardList(0);
		oldDevCards.setYearOfPlenty(1);
		
		model.getPlayers()[0].setOldDevCards(oldDevCards);
		assertTrue(model.canYearOfPlenty(0));

	}
	
	@Test
	public void canMonopolyTest(){
		DevCardList oldDevCards =new DevCardList(0);
		oldDevCards.setMonopoly(1);
		
		model.getPlayers()[0].setOldDevCards(oldDevCards);
		assertTrue(model.canMonopoly(0));

	}
	
	@Test
	public void canMonumentTest(){
		DevCardList oldDevCards =new DevCardList(0);
		oldDevCards.setMonument(1);;
		
		model.getPlayers()[0].setOldDevCards(oldDevCards);
		assertTrue(model.canMonument(0));

	}
	
	@Test
	public void canRoadBuildingTest(){
		DevCardList oldDevCards =new DevCardList(0);
		oldDevCards.setRoadBuilding(1);;
		
		model.getPlayers()[0].setOldDevCards(oldDevCards);
		assertTrue(model.canRoadBuilding(0));

	}
	
	@Test
	public void canSoldierTest(){
		DevCardList oldDevCards =new DevCardList(0);
		oldDevCards.setSoldier(1);;
		
		model.getPlayers()[0].setOldDevCards(oldDevCards);
		assertTrue(model.canSoldier(0));

	}
	
	@Test
	public void canPlaceRobber(){
		
		model.setRoll(7);
		assertTrue(model.canPlaceRobber(0));
		model.setRoll(2);
		assertFalse(model.canPlaceRobber(0));

	}
	
	@Test
	public void canSendChatTest(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 400; i++){
			sb.append("D");
		}
		String message = sb.toString();
		assertFalse(model.canSendChat(message));
		message = "I'm tired of this...";
		assertTrue(model.canSendChat(message));
		
		
		
		
	}
	

}
