package client.communication;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.CatanColor;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.MessageLine;
import model.Player;
import client.base.Controller;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
		
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
	
	private void initFromModel(ClientModel model) {
		List<LogEntry> log = new ArrayList<LogEntry>();
		for(MessageLine line : model.getLog().getLines()) 
		{
			CatanColor color = CatanColor.WHITE;
			for(Player player : model.getPlayers()) {
				if(player.getName().toLowerCase().equals(line.getSource().toLowerCase())) {
					color = player.getColor();
					break;
				}
			}
			log.add(new LogEntry(color, line.getMessage()));
		}
		getView().setEntries(log);
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		initFromModel(model);
	}
	
}

