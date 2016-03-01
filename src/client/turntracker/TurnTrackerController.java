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
		try {
			int largestRoads = 4;
			int playerWithLong = -1;

			int mostSoldiers = 2;
			int playerWithArmy = -1;

			Player[] players = ClientFacade.getSingleton().getClientModel().getPlayers();
			for(Player player : players){
				if(15 - player.getRoads() > largestRoads){
					largestRoads = 15 - player.getRoads();
					playerWithLong = player.getPlayerIndex();
				}

				if(player.getSoldiers() > mostSoldiers){
					playerWithArmy = player.getPlayerIndex();
				}
			}
			if(playerWithLong != -1){
				ClientFacade.getSingleton().getClientModel().getTurnTracker().setLongestRoad(playerWithLong);
			}
			if(playerWithArmy != -1){
				ClientFacade.getSingleton().getClientModel().getTurnTracker().setLargestArmy(playerWithLong);

			}

//			int victoryPoints = 0;
//			Player localPlayer = ClientFacade.getSingleton().getClientModel().getPlayers()[ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex()];
//			victoryPoints = localPlayer.getVictoryPoints();
//			if(ClientFacade.getSingleton().getClientModel().getTurnTracker().getLargestArmy() == localPlayer.getPlayerIndex()){
//				victoryPoints += 2;
//			}
//			if(ClientFacade.getSingleton().getClientModel().getTurnTracker().getLongestRoad() == localPlayer.getPlayerIndex()){
//				victoryPoints += 2;
//			}


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
			if (!ClientFacade.getSingleton().getGameStarted()) {
				System.out.println("Returned in turn tracker. Game not started");
				return;
			}
			PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
			getView().setLocalPlayerColor(localPlayer.getColor());
			Player[] players = ClientFacade.getSingleton().getClientModel().getPlayers();
			TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
			for(int i = 0; i < players.length; i++) {
				if (players[i] == null) {
					continue;
				}
				getView().initializePlayer(players[i].getPlayerIndex(), 
						players[i].getName(),
						players[i].getColor());
				int extraPoints = 0;
				boolean largestArmy = players[i].getPlayerIndex() == turnTracker.getLargestArmy();
				boolean longestRoad = players[i].getPlayerIndex() == turnTracker.getLongestRoad();
				if (largestArmy) {
					extraPoints += 2;
				}
				if (longestRoad) {
					extraPoints += 2;
				}
				getView().updatePlayer(players[i].getPlayerIndex(),
						players[i].getVictoryPoints() + extraPoints,
						players[i].getPlayerIndex() == turnTracker.getCurrentTurn(),
						largestArmy,
						longestRoad);
			}
			if (ClientFacade.getSingleton().getClientModel().canFinishTurn(localPlayer.getPlayerIndex())) {
				getView().updateGameState("Finish turn", true);
			} else {
				getView().updateGameState(ClientFacade.getSingleton().getContext().getState().toString(), false);
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

