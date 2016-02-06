package clientTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import shared.definitions.CatanColor;
import shared.locations.HexLocation;
import model.*;

public class GameMapTest {
	static GameMap testMap;

	@Test
	public void testInit() {
		testMap = new GameMap();
		
		assertTrue(testMap.getHexList() 		!= null);
		assertTrue(testMap.getPortList() 		!= null);
		assertTrue(testMap.getRoadList() 		!= null);
		assertTrue(testMap.getSettlementList() 	!= null);
		assertTrue(testMap.getCityList() 		!= null);
	}
	
	@Test
	public void testMoveRobber() {
		testMap = new GameMap();
		
		assertTrue(testMap.getRobber() != null);
	}
	
	@Test
	public void testPlaceSettlement() {
		testMap = new GameMap();
		
		try {
			testMap.placeSettlement(new VertexObject());
			assertTrue(testMap.getSettlementList().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceCity() {
		testMap = new GameMap();
		
		try {
			testMap.placeCity(new VertexObject());
			assertTrue(testMap.getCityList().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceRoad() {
		testMap = new GameMap();
		
		try {
			testMap.placeRoad(new EdgeValue());
			assertTrue(testMap.getRoadList().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
}
