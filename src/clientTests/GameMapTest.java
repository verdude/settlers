package clientTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import shared.definitions.CatanColor;
import model.*;

public class GameMapTest {
	static GameMap testMap;

	@Test
	public void init() {
		testMap = new GameMap();
		assertTrue(testMap.getHexList() 		!= null);
		assertTrue(testMap.getPortList() 		!= null);
		assertTrue(testMap.getRoadList() 		!= null);
		assertTrue(testMap.getSettlementList() 	!= null);
		assertTrue(testMap.getCityList() 		!= null);
	}
	
	@Test
	public void testMoveRobber() {
		init();
		
	}
	
	@Test
	public void testPlaceSettlement() {
		init();
	}
	
	@Test
	public void testPlaceCity() {
		init();
	}
	
	@Test
	public void testPlaceRoad() {
		init();
	}

}
