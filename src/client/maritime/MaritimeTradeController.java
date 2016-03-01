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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	private List<ResourceType> availableToGive;
	private TradeInfo currTrade;

	/*
	 * inner class that is used to keep track of the trade information
	 */
	private class TradeInfo {

		public ResourceType getGet() {
			return get;
		}

		public void setGet(ResourceType get) {
			this.get = get;
		}

		public ResourceType getGive() {
			return give;
		}

		public void setGive(ResourceType give) {
			this.give = give;
		}

		public int getRatio() {
			return ratio;
		}

		public void setRatio(int ratio) {
			this.ratio = ratio;
		}

		private int ratio;
		private ResourceType give;
		private ResourceType get;

		public TradeInfo() {
			give = ResourceType.BRICK;
			get = ResourceType.BRICK;
			ratio = -1;
		}
	}

	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);
		availableToGive = new ArrayList<ResourceType>();
		setTradeOverlay(tradeOverlay);

		currTrade = new TradeInfo();

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
		try {
			if (setMaritimeButton(ClientFacade.getSingleton().getClientModel())) {
				getTradeOverlay().reset();
				ResourceType[] temp = new ResourceType[availableToGive.size()];
				for (int i = 0; i < availableToGive.size(); ++i) {
					temp[i] = availableToGive.get(i);
				}
				getTradeOverlay().showGiveOptions(temp);
				getTradeOverlay().showModal();
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void makeTrade() {
		try {
			ClientFacade.getSingleton().maritimeTrade(4, currTrade.getGive(), currTrade.getGet());
			getTradeOverlay().closeModal();
		} catch (ClientException e) {
			System.out.println("Broke trying to make maritime trade.");
			e.printStackTrace();
		}
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().selectGetOption(resource, 1);
		currTrade.setGet(resource);
		getTradeOverlay().setTradeEnabled(true);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().showGetOptions(ResourceType.values());
		getTradeOverlay().selectGiveOption(resource, 4);
		currTrade.setGive(resource);
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().hideGetOptions();
		ResourceType[] temp = new ResourceType[availableToGive.size()];
        for (int i = 0; i < availableToGive.size(); ++i) {
            temp[i] = availableToGive.get(i);
        }
		getTradeOverlay().showGetOptions(temp);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		ResourceType[] temp = new ResourceType[availableToGive.size()];
        for (int i = 0; i < availableToGive.size(); ++i) {
            temp[i] = availableToGive.get(i);
        }
		getTradeOverlay().showGiveOptions(temp);
	}

	private boolean loadAvailableResources() {
		availableToGive.clear();
		ResourceType[] res = ResourceType.values();
		try {
			ClientFacade facade = ClientFacade.getSingleton();
			for (int i = 0; i < res.length; ++i) {
				// check if the player can do a maritime trade for each of the resoures
				if (facade.getClientModel().canMaritimeTrade(facade.getLocalPlayer().getPlayerIndex(), res[i])) {
					availableToGive.add(res[i]);
				}
			}
		} catch (ClientException e) {
			System.out.println("Error getting client model in the maritime controller");
    	    e.printStackTrace();
		}
		// means that you do not have any maritime options
		return availableToGive.size() > 0;
	}

	/**
	 * Enables/ disables the maritime button
	 * @param model the current client model
	 * @return
     */
	private boolean setMaritimeButton(ClientModel model) {
		try {
			IState currState = ClientFacade.getSingleton().getContext().getState();
			boolean isLocalPlayersTurn = model.getTurnTracker().getCurrentTurn() ==
											ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			// sets the maritime trade button to on or off
			// Dont' actually want to set the button to off.
			if (isLocalPlayersTurn && currState instanceof PlayingState && loadAvailableResources()){
				//((MaritimeTradeView)getView()).enableMaritimeTrade(true);
				return true;
			} else {
				//((MaritimeTradeView)getView()).enableMaritimeTrade(false);
				return false;
			}
		} catch (ClientException e) {
			((MaritimeTradeView)getView()).enableMaritimeTrade(true);
			e.printStackTrace();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		//setMaritimeButton(model);
	}

}

