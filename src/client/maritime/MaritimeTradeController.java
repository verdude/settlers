package client.maritime;

import shared.definitions.*;
import client.base.*;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import state.FirstRoundState;
import state.IState;
import state.PlayingState;
import state.SecondRoundState;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		System.out.println("Maritime Start Trade");
		getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		System.out.println("Maritime Make Trade");
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {
		System.out.println("Maritime cancel Trade");
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		System.out.println("Maritime set get resource");
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		System.out.println("Maritime set give resource");
	}

	@Override
	public void unsetGetValue() {
		System.out.println("Maritime unsert get value");
	}

	@Override
	public void unsetGiveValue() {
		System.out.println("Maritime unset give value");
	}

	private boolean canMaritimeAny() {
		ResourceType[] res = ResourceType.values();
		try {
			for (int i = 0; i < res.length; ++i) {
				// check if the player can do a maritime trade for each of the resoures
				if (!ClientFacade.getSingleton().getClientModel().canMaritimeTrade(ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex(), res[i])) {
					return false;
				}
			}
		} catch (ClientException e) {
			System.out.println("Error getting client model in the maritime controller");
    	    e.printStackTrace();
		}
		// can maritime trade
		return true;
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		try {
			IState currState = ClientFacade.getSingleton().getContext().getState();
			boolean isLocalPlayersTurn = ClientFacade.getSingleton().getClientModel().getTurnTracker().getCurrentTurn() ==
											ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			if (isLocalPlayersTurn && currState instanceof PlayingState && canMaritimeAny()){
				((MaritimeTradeView)getView()).enableMaritimeTrade(true);
			} else {
				((MaritimeTradeView)getView()).enableMaritimeTrade(false);
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}

