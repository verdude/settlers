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
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
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





	}

}

