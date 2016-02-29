package clientTests;

import static org.junit.Assert.assertTrue;

import model.EdgeValue;
import model.GameMap;
import model.GameMapException;
import model.VertexObject;

import org.junit.Test;

public class GameMapTest {
	static GameMap testMap;

	@Test
	public void testInit() {
		testMap = new GameMap();
		
		assertTrue(testMap.getHexes() 		!= null);
		assertTrue(testMap.getPorts() 		!= null);
		assertTrue(testMap.getRoads() 		!= null);
		assertTrue(testMap.getSettlements() 	!= null);
		assertTrue(testMap.getCities() 		!= null);
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
			assertTrue(testMap.getSettlements().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceCity() {
		testMap = new GameMap();
		
		try {
			testMap.placeCity(new VertexObject());
			assertTrue(testMap.getCities().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceRoad() {
		testMap = new GameMap();
		
		try {
			testMap.placeRoad(new EdgeValue());
			assertTrue(testMap.getRoads().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
}
