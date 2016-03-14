package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/rollNumber endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRollNumberCommand implements ICatanCommand {

	private int playerIndex;
	private int number;

	/**
	 * @pre The It is the rolling phase and it is the palyer's turn The number is >1 && <13
	 * @post The player rolls a number
	 * @param playerIndex The player's index
	 * @param number The number the player rolled
     */
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
