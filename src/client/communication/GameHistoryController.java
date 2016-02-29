package client.communication;

import java.util.ArrayList;
import java.util.List;

import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import client.base.Controller;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

	public GameHistoryController(IGameHistoryView view) {
		
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
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		// We need to populate it from the model
		List<LogEntry> entries = new ArrayList<LogEntry>();

		getView().setEntries(entries);
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		
	}
	
}

