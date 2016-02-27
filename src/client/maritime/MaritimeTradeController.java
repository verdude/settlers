package client.maritime;

import shared.definitions.*;
import client.base.*;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;


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

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		System.out.println("Maritime notify");
	}

}

