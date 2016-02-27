package client.join;

import client.base.Controller;
import client.data.GameInfo;
import client.data.PlayerInfo;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.Converter;
import model.ServerProxy;

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

	@Override
	public void start() {
		getView().showModal();
		Timer timer = new Timer();
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
                                this.cancel();
                            } else {
								PlayerInfo[] players = new PlayerInfo[4];
								for (int i = 0; i < game.getPlayers().size(); ++i) {
									players[i] = game.getPlayers().get(i);
								}
								getView().setPlayers(players);
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

