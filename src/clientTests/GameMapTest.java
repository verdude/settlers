package clientTests;

import model.EdgeValue;
import model.GameMap;
import model.GameMapException;
import model.VertexObject;
import org.junit.Test;
import shared.locations.*;

import static org.junit.Assert.assertTrue;

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
			VertexObject vertexObject = new VertexObject();
			vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0,0), VertexDirection.East));
			vertexObject.setLocation(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
			testMap.placeSettlement(vertexObject);
			assertTrue(testMap.getSettlements().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceCity() {
		testMap = new GameMap();
		
		try {
			VertexObject vertexObject = new VertexObject();
			vertexObject.setVertexLocation(new VertexLocation(new HexLocation(0,0), VertexDirection.East));
			vertexObject.setLocation(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
			testMap.placeCity(vertexObject);
			assertTrue(testMap.getCities().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testPlaceRoad() {
		testMap = new GameMap();
		
		try {
			EdgeValue edgeValue = new EdgeValue();
			edgeValue.setLocation(new EdgeLocation(new HexLocation(0,0), EdgeDirection.North));
			testMap.placeRoad(edgeValue);
			assertTrue(testMap.getRoads().size() > 0);
		} catch (GameMapException e) {
			assertTrue(false);
		}
	}
}
