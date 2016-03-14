package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/sendChat endpoint
 * @author S Jacob Powell
 *
 */
public class MovesSendChatCommand implements ICatanCommand {

	private int playerIndex;
	private String content;

	public MovesSendChatCommand(int playerIndex, String content) {
		this.playerIndex = playerIndex;
		this.content = content;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
