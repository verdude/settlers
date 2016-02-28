package client.points;

import client.base.*;
import client.data.PlayerInfo;
import model.*;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

	private IGameFinishedView finishedView;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		initFromModel();
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {
		//<temp>		
		getPointsView().setPoints(0);
		//</temp>
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		PlayerInfo currentPlayer;

		try {
			currentPlayer = ClientFacade.getSingleton().getLocalPlayer();
			if (currentPlayer.getPlayerIndex() == -1) {
				return;
			}
			Player player = model.getPlayers()[currentPlayer.getPlayerIndex()];
			TurnTracker turnTracker = model.getTurnTracker();
			int victoryPoints = player.getVictoryPoints();

			if(turnTracker.getLargestArmy() != -1 && turnTracker.getLargestArmy() == player.getPlayerIndex()){
				victoryPoints += 2;
			}
			if(turnTracker.getLongestRoad() != -1 && turnTracker.getLongestRoad() == player.getPlayerIndex()){
				victoryPoints += 2;
			}

			getPointsView().setPoints(victoryPoints);
		} catch (ClientException e) {
			System.out.println("Could not set the points. PointsController.");
			e.printStackTrace();
		}

	}
}

