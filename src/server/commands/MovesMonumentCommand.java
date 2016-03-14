package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/Monument endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMonumentCommand implements ICatanCommand {

	private int playerIndex;

	/**
	 * @pre The playerindex is valid (0-4). The player has a monument card. It is the player's turn
	 * @param playerIndex Index of the player playing the monument card
     */
	public MovesMonumentCommand(int playerIndex) {
		this.playerIndex = playerIndex;
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
