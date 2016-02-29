package client.turntracker;

import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.Player;
import model.TurnTracker;
import client.base.Controller;
import client.data.PlayerInfo;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
		initFromModel();
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		getView().updateGameState("Waiting for other players.", true);
		try {
			ClientFacade.getSingleton().finishTurn();
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initFromModel() {
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		try {
			if (ClientFacade.getSingleton().getGameStarted() == false) {
				System.out.println("Returned in turn tracker. Game not started");
				return;
			}
			PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
			getView().setLocalPlayerColor(localPlayer.getColor());
			Player[] players = ClientFacade.getSingleton().getClientModel().getPlayers();
			TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
			for(int i = 0; i < players.length; i++) {
				getView().initializePlayer(players[i].getPlayerIndex(), 
						players[i].getName(),
						players[i].getColor());
				getView().updatePlayer(players[i].getPlayerIndex(), 
						players[i].getVictoryPoints(), 
						players[i].getPlayerIndex() == turnTracker.getCurrentTurn(), 
						players[i].getPlayerIndex() == turnTracker.getLargestArmy(), 
						players[i].getPlayerIndex() == turnTracker.getLongestRoad());
				}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

