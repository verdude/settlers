package clientTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.City;
import model.ClientModel;
import model.EdgeValue;
import model.GameMap;
import model.Hex;
import model.Player;
import model.Port;
import model.ResourceList;
import model.Road;
import model.Settlement;
import model.TurnTracker;
import model.VertexObject;
import shared.definitions.CatanColor;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class ClientCanDoTests {

	private static  ClientModel model;
	private static GameMap map;
	private List<Hex> 			hexList;
	private List<Port> 			portList;
	private List<Road> 			roadList;
	private List<Settlement> 	settlementList;
	private List<City> 			cityList;
	private TurnTracker turnTracker;
	
	@Before
	public  void setUpBefore() throws Exception {
		model = new ClientModel();
//		Hex south = new Hex();
//		south.setLocation(new HexLocation(0,0));
//		Hex northEast = new Hex();
//		northEast.setLocation(new HexLocation(1,-1));
//		Hex north = new Hex();
//		north.setLocation(new HexLocation(0,-1));
		
		
		
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

//	@After
//	public static void tearDownAfter() throws Exception {
//	}

	@Test
	public void canPlayRoadTest() {
		Road road2 = new Road();
		
		EdgeValue road2Val = new EdgeValue();
		EdgeLocation road1Loc = new EdgeLocation(new HexLocation(1,-1), EdgeDirection.North);
		road2Val.setLocation(road1Loc);
		
		road2.setPlayerId(0);
		road2.setLocation(road2Val);
		assertTrue(model.canBuildRoad(0, road2Val));
		assertFalse(model.canBuildRoad(0, road2Val));

	}

}
