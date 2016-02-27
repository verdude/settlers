package client.roll;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
		} catch (ClientException e) {
			System.out.println("Problem when rolling dice");
			e.printStackTrace();
		}
		getResultView().showModal();
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		if(model.getTurnTracker().getStatus().toLowerCase().contains("rolling")) {
			ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();     
			s.schedule(new Runnable() {
			    public void run() {
			    	rollDice();
					getRollView().closeModal();
			    }
			}, 20, TimeUnit.SECONDS);
			getRollView().showModal();
		}
	}

}

