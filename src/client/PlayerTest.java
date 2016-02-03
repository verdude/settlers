package client;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClientException;
import model.Player;
import model.ResourceList;
import shared.definitions.CatanColor;

public class PlayerTest {
	static Player test1;




	@Test
	public void testRollNumber() {

		test1 = new Player("sean", CatanColor.WHITE,0);
		assertTrue(test1.canRollNumber());
		int roll = -1;
		try {
			for(int i = 0; i < 100; i++){ // perform a hundred rolls 
				roll = test1.rollNumber();
				assertTrue(roll > 1 && roll < 13);	
				assertFalse(test1.canRollNumber()); // should only be able to roll once per turn
				test1.setHasRolled(false);
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}


	}

	@Test
	public void testPlaySettlement(){
		test1 = new Player("sean", CatanColor.WHITE,0);
		assertFalse(test1.canPlaySettlement());// not enough resources to play a settlement
		test1.setResources(new ResourceList(6));// enough resources to buy 6 settlements
		int wheat = 6;
		int sheep = 6;
		int wood = 6;
		int brick = 6;
		int settlements = 5; // the number of settlements a player starts with
		try {
			for (int i = 0; i < 5; i++){ // play all of the settlements that the player has
				test1.playSettlement();

				settlements--;
				wheat--;
				sheep--;
				wood--;
				brick--;

				assertEquals(settlements, test1.getSettlements());

				assertEquals(brick,test1.getResources().getBrick());
				assertEquals(wheat,test1.getResources().getWheat());
				assertEquals(wood,test1.getResources().getWood());
				assertEquals(sheep,test1.getResources().getSheep());

			}
			assertFalse(test1.canPlaySettlement()); // has enough resources, but not enough settlements

		} catch (ClientException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testPlayCity(){
		test1 = new Player("sean", CatanColor.WHITE,0);
		assertFalse(test1.canPlayCity());// not enough resources to play a settlement
		test1.getResources().setOre(15);;// enough ore to buy 5 cities
		test1.getResources().setWheat(10);;// enough wheat to buy 5 cities
		assertEquals(test1.getResources().getOre(),15);

		int ore = 15;
		int wheat = 10;

		int cities = 4; // the number of cities a player starts with

		test1.setSettlements(1);//
		int settlements = 1;
		try {
			for (int i = 0; i < 4; i++){ // play all of the cities that the player has
				test1.playCity();

				cities--;
				wheat -= 2;
				ore -= 3;
				settlements++;

				assertEquals(cities, test1.getCities());
				assertEquals(settlements, test1.getSettlements());
				assertEquals(ore,test1.getResources().getOre());
				assertEquals(wheat,test1.getResources().getWheat());


			}
			assertFalse(test1.canPlayCity());

		} catch (ClientException e) {
			e.printStackTrace();
		}



	}

	@Test
	public void testPlayRoad(){
		test1 = new Player("sean", CatanColor.WHITE,0);

		assertFalse(test1.canPlayRoad());// not enough resources to play a road
		test1.getResources().setBrick(16);// enough brick to build 16 roads
		test1.getResources().setWood(16);// enough wood to build 16 roads


		int wood = 16;
		int brick = 16;
		int roads = 15; // the number of roads a player starts with
		try {
			for (int i = 0; i < 15; i++){ // play all of the roads that the player has
				test1.playRoad();

				roads--;

				wood--;
				brick--;

				assertEquals(roads, test1.getRoads());

				assertEquals(brick,test1.getResources().getBrick());
				assertEquals(wood,test1.getResources().getWood());

			}
			assertFalse(test1.canPlayRoad()); // has enough resources, but not enough roads

		} catch (ClientException e) {
			e.printStackTrace();
		}

	}

}
