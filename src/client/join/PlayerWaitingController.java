package client.join;

import client.base.Controller;
import client.data.GameInfo;
import client.data.PlayerInfo;
import model.*;

import java.util.Timer;
import java.util.TimerTask;


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

	/**
	 * Gets all of the players in the game and adds them to the player waiting view list
	 * @param jsonGames String result that you get from the client facade gamesList method
	 * @throws ClientException
     */
	private void setPlayersList(String jsonGames) throws ClientException {
		GameInfo[] games = Converter.deserializeGamesArray(jsonGames);
		int gameId = Integer.parseInt(ServerProxy.getSingleton().getGameID());
		for (GameInfo game : games) {
			if (game.getId() == gameId) {
				PlayerInfo[] players = new PlayerInfo[game.getPlayers().size()];
				for (int i = 0; i < game.getPlayers().size(); ++i) {
					players[i] = game.getPlayers().get(i);
				}
				getView().setPlayers(players);
				break;
			}
		}
	}

	@Override
	public void start() {
		getView().showModal();
		Timer timer = new Timer();
		try {
			setPlayersList(ClientFacade.getSingleton().gamesList());
		} catch (ClientException e) {
			System.out.println("PlayerWaitingController set player list failed.");
			e.printStackTrace();
		}
		timer.schedule(new TimerTask() {
            @Override
            public void run() {
        		GameInfo[] games;
        		try {
        			games = Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());
	                for (GameInfo game : games) {
                        if (game.getId() == Integer.parseInt(ServerProxy.getSingleton().getGameID())) {
                            if (game.getPlayers().size() == 4) {
                                getView().closeModal();
								ClientFacade.getSingleton().setGameStarted(true);
                                this.cancel();
                            }
                            break;
                        }
	                }
        		} catch (ClientException e) {
        			System.out.println("Error getting game list");
        			e.printStackTrace();
        			return;
                }
            }
        }, 0, 1000);
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

