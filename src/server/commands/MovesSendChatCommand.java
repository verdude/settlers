package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

/**
 * This represents the moves/sendChat endpoint
 * @author S Jacob Powell
 *
 */
public class MovesSendChatCommand extends ICatanCommand {

	private int playerIndex;
	private String content;

	/**
	 * @pre None
	 * @post The chat message will be sent.
	 * @param playerIndex The index of the player sending the chat.
	 * @param content The chat message to be sent.
     */
	public MovesSendChatCommand(int playerIndex, String content) {
		this.playerIndex = playerIndex;
		this.content = content;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		ServerFacade.getSingleton().setPlayerIndex(playerIndex);

		String response = facade.sendChat(playerIndex, content);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
