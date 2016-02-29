package client.communication;

import java.util.ArrayList;
import java.util.List;

import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.MessageLine;
import model.Player;
import shared.definitions.CatanColor;
import client.base.Controller;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	public ChatController(IChatView view) {
		
		super(view);
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
			e.printStackTrace();
		}
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		try {
			ClientFacade.getSingleton().sendChat(ClientFacade.getSingleton().getLocalPlayer().getName(), message);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		List<LogEntry> log = new ArrayList<LogEntry>();
		List<LogEntry> messages = new ArrayList<LogEntry>();
		for(MessageLine line : model.getChat().getLines()) 
		{
			CatanColor color = CatanColor.WHITE;
			for(Player player : model.getPlayers()) {
				if(player.getName().toLowerCase().equals(line.getSource().toLowerCase())) {
					color = player.getColor();
					break;
				}
			}
			messages.add(new LogEntry(color, line.getMessage()));
		}
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
		getView().setEntries(messages);
		
	}

}

