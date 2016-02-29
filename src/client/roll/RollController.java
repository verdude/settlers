package client.roll;

import java.util.Timer;
import java.util.TimerTask;

import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import client.base.Controller;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);

		setResultView(resultView);
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}

	@Override
	public void rollDice() {
		try {
			getResultView().setRollValue(ClientFacade.getSingleton().rollNumber());
			if (!getResultView().isModalShowing() &&
					ClientFacade.getSingleton().getClientModel().canRollNumber(ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex())) {
				getResultView().showModal();
			}
		} catch (ClientException e) {
			System.out.println("Problem when rolling dice");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		if(model.getTurnTracker().getStatus().toLowerCase().contains("rolling")) {
			try {
				if (!getRollView().isModalShowing() &&
						ClientFacade.getSingleton().getClientModel().canRollNumber(ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex())) {
                    getRollView().showModal();
                }
			} catch (ClientException e) {
				e.printStackTrace();
			}
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
			    public void run() {
			    	if (getRollView().isModalShowing()) {
						getRollView().closeModal();
						rollDice();
			    	}
					this.cancel();
			    }
			}, 3000, 3000);
		}
	}

}

