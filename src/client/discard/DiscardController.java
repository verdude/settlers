package client.discard;

import client.base.Controller;
import client.data.PlayerInfo;
import client.misc.IWaitView;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.ResourceList;
import shared.definitions.ResourceType;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	int amountToDiscard;
	PlayerInfo localPlayer;
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		ResourceList resourceList = null;
//		try {
//			//localPlayer = ClientFacade.getSingleton().getLocalPlayer();
//			//resourceList = ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayer.getPlayerIndex()].getResources();
//
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
		//amountToDiscard = resourceList.getTotal()/2;
		this.waitView = waitView;
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {


	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	@Override
	public void discard() {
		
		getDiscardView().closeModal();
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		
	}

}

