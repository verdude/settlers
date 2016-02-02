package client.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import client.ClientException;
import client.ClientModel;
import client.MessageLine;

public class TestClientModel {

	@Test
	//Constructor
	public void testClientModel() {
		ClientModel model = new ClientModel();
		assertNotNull(model.getBank());
		assertNotNull(model.getChat());
		assertNotNull(model.getLog());
		assertNotNull(model.getMap());
		assertTrue(model.getPlayers().length == 4);
		assertNotNull(model.getTurnTracker());
		assertTrue(model.getVersion() == 0);
		assertTrue(model.getWinner() == -1);
	}
	
	@Test
	public void testRemoveResource() throws ClientException {
		ClientModel model = new ClientModel();
		model.removeResource("wheat", 2);
		assertTrue(model.getBank().getWheat() == 17);
		model.removeResource("sheep", 1);
		assertTrue(model.getBank().getSheep() == 18);
		model.removeResource("ore", 17);
		assertTrue(model.getBank().getOre() == 2);
		model.removeResource("brick", 19);
		assertTrue(model.getBank().getBrick() == 0);
		model.removeResource("wood", 20);
		assertTrue(model.getBank().getWood() == 0);
	}
	
	@Test(expected=ClientException.class)
	public void testRemoveResourceErrors() throws ClientException {
		ClientModel model = new ClientModel();
		model.removeResource("sdgdggg", 2);
	}
	

	@Test
	public void testAddResource() throws ClientException {
		ClientModel model = new ClientModel();
		model.addResource("wheat", 2);
		assertTrue(model.getBank().getWheat() == 19);
		model.addResource("sheep", 1);
		assertTrue(model.getBank().getSheep() == 19);
		model.addResource("ore", 17);
		assertTrue(model.getBank().getOre() == 19);
		model.addResource("brick", 19);
		assertTrue(model.getBank().getBrick() == 19);
		model.addResource("wood", 20);
		assertTrue(model.getBank().getWood() == 19);
	}
	
	@Test(expected=ClientException.class)
	public void testAddResourceErrors() throws ClientException {
		ClientModel model = new ClientModel();
		model.addResource("sdgdggg", 2);
	}
	
	@Test
	public void testAddChatMessage() throws ClientException {
		ClientModel model = new ClientModel();
		MessageLine message = new MessageLine("This is the message", "Source of all sources");
		model.addChatMessage(message);
		assertTrue(model.getChat().getLines().get(0) == message);
		assertTrue(model.getLog().getLines().get(0) == message);
	}
	
	@Test
	public void testEndTurn() throws ClientException {
		ClientModel model = new ClientModel();
		model.endTurn();
		//Do the players need to be initialized in the model?
	}
}
