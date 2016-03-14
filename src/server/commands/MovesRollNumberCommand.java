package server.commands;

import server.ICommand;

/**
 * This represents the moves/rollNumber endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRollNumberCommand implements ICommand {

	private int playerIndex;
	private int number;

	public MovesRollNumberCommand(int playerIndex, int number) {
		this.playerIndex = playerIndex;
		this.number = number;
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
