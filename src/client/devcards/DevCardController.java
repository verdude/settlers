package client.devcards;


import client.base.Controller;
import client.base.IAction;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		try {
			ClientFacade.getSingleton().buyDevCard();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		try {
			DevCardList cards = ClientFacade.getSingleton().getClientModel().getPlayers()[ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex()].getOldDevCards();

			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, cards.getMonopoly());
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, cards.getMonument());
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, cards.getRoadBuilding());
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, cards.getSoldier());
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, cards.getYearOfPlenty());
			getPlayCardView().showModal();
		} catch (ClientException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void cancelPlayCard() {
		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {

	}

	@Override
	public void playMonumentCard() {
		try {
			int currentPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			ClientFacade.getSingleton().monument();
		} catch (ClientException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void playRoadBuildCard() {
		
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		
//		int localPlayerIndex = model.getTurnTracker().getCurrentTurn();
//		Player localPlayer = model.getPlayers()[localPlayerIndex];
//
		
		
	}

}

