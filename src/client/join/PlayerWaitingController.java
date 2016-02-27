package client.join;

import client.base.Controller;
import client.data.GameInfo;
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
		System.out.println("in the waiting controller");
		Timer timer = new Timer();
		GameInfo[] games;
		try {
			games = Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());

		} catch (ClientException e) {
			System.out.println("Error getting game list");
			e.printStackTrace();
			return;
		}
		timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (GameInfo game : games) {
                    try {
                        if (game.getId() == Integer.parseInt(ServerProxy.getSingleton().getGameID())) {
                            if (game.getPlayers().size() == 4) {
                                getView().closeModal();
                                timer.cancel();
                                timer.purge();
                            }
                            break;
                        }
                    } catch (ClientException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 3000);
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

