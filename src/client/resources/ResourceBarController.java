package client.resources;

import client.base.Controller;
import client.base.IAction;
import client.base.IObserver;
import client.data.PlayerInfo;
import model.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, IObserver {

	private Map<ResourceBarElement, IAction> elementActions;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {
		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {

		try {
			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			Player player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			ResourceList resources = player.getResources();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(resources.getBrick() < 1 || resources.getWood() <1
					|| player.getRoads() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.ROAD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.ROAD,true);
				executeElementAction(ResourceBarElement.ROAD);


			}




		} catch (ClientException e) {
			e.printStackTrace();
		}
		//executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		try {
			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			Player player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			ResourceList resources = player.getResources();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(resources.getBrick() < 1 || resources.getSheep() < 1 ||
					resources.getWheat() < 1 || resources.getWood() <1 || player.getSettlements() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT,true);
				executeElementAction(ResourceBarElement.SETTLEMENT);

			}




		} catch (ClientException e) {
			e.printStackTrace();
		}


	//	executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {

		try {

			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			Player player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			ResourceList resources = player.getResources();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(resources.getOre() < 3|| resources.getWheat() < 2 ||
					player.getCities() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.CITY,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.CITY,true);
				executeElementAction(ResourceBarElement.CITY);


			}




		} catch (ClientException e) {
			e.printStackTrace();
		}


//		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		try {

			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			Player player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			ResourceList resources = player.getResources();
			int bankDevCards = ClientFacade.getSingleton().getClientModel().getDevCardList().getTotal();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(resources.getOre() < 1|| resources.getWheat() < 1 || resources.getSheep() < 1 ||
					bankDevCards < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.BUY_CARD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.BUY_CARD,true);
				executeElementAction(ResourceBarElement.BUY_CARD);

			}




		} catch (ClientException e) {
			e.printStackTrace();
		}



	}

	@Override
	public void playCard() {


		try {

			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			Player player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			ResourceList resources = player.getResources();
			int devsAvailable = player.getOldDevCards().getTotal();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(devsAvailable < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD,true);
				executeElementAction(ResourceBarElement.PLAY_CARD);

			}




		} catch (ClientException e) {
			e.printStackTrace();
		}




	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		PlayerInfo currentPlayer = null;

		try {
			currentPlayer = ClientFacade.getSingleton().getLocalPlayer();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		if (currentPlayer == null || currentPlayer.getPlayerIndex() == -1) {
			return;
		}
		Player player = model.getPlayers()[currentPlayer.getPlayerIndex()];
		ResourceList resources = player.getResources();

		//Set resource amounts for the local player based off of the current model
		getView().setElementAmount(ResourceBarElement.BRICK, resources.getBrick());
		getView().setElementAmount(ResourceBarElement.WHEAT, resources.getWheat());
		getView().setElementAmount(ResourceBarElement.WOOD, resources.getWood());
		getView().setElementAmount(ResourceBarElement.SHEEP, resources.getSheep());
		getView().setElementAmount(ResourceBarElement.ORE, resources.getOre());

		//Set how many roads, settlements, soldiers, and cities the local player has
		getView().setElementAmount(ResourceBarElement.ROAD,player.getRoads());
		getView().setElementAmount(ResourceBarElement.CITY,player.getCities());
		getView().setElementAmount(ResourceBarElement.SETTLEMENT,player.getSettlements());
		getView().setElementAmount(ResourceBarElement.SOLDIERS,player.getSoldiers());



		try {
			int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			 player = ClientFacade.getSingleton().getClientModel().getPlayers()[playerIndex];
			 resources = player.getResources();
			int currentTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn();

			if(resources.getBrick() < 1 || resources.getWood() <1
					|| player.getRoads() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.ROAD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.ROAD,true);


			}

			if(resources.getBrick() < 1 || resources.getSheep() < 1 ||
					resources.getWheat() < 1 || resources.getWood() <1 ||
					player.getSettlements() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.SETTLEMENT,true);

			}
			if(resources.getOre() < 3|| resources.getWheat() < 2 ||
					player.getCities() < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.CITY,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.CITY,true);

			}

			int bankDevCards = ClientFacade.getSingleton().getClientModel().getDevCardList().getTotal();
			if(resources.getOre() < 1|| resources.getWheat() < 1 || resources.getSheep() < 1 ||
					bankDevCards < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.BUY_CARD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.BUY_CARD,true);

			}

			int devsAvailable = player.getOldDevCards().getTotal();

			if(devsAvailable < 1 || playerIndex != currentTurn){
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD,false);
			}else{
				getView().setElementEnabled(ResourceBarElement.PLAY_CARD,true);

			}



		} catch (ClientException e) {
			e.printStackTrace();
		}



	}

}

