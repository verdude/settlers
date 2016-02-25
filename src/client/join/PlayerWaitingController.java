package client.join;

import client.base.Controller;
import client.data.GameInfo;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.Converter;
import model.ServerProxy;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		getView().showModal();
		try {
			GameInfo[] games = Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());
			boolean waitingForPlayers = true;
			while (waitingForPlayers) {
				for (GameInfo game : games) {
					if (game.getId() == Integer.parseInt(ServerProxy.getSingleton().getGameID())) {
						if (game.getPlayers().size() == 4) {
							getView().closeModal();
							waitingForPlayers = false;
						} else {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						break;
					}
				}
			}
		} catch (ClientException e) {
			System.out.println("Error getting game list");
			e.printStackTrace();
		}
	}

	@Override
	public void addAI() {

		// TEMPORARY
//		getView().closeModal();
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		
	}

}

