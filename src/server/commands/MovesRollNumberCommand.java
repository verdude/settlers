package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

/**
 * This represents the moves/rollNumber endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRollNumberCommand extends ICatanCommand {

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
		super.setType(this.getClass().toString());

	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.rollNumber(number);
		

		return response;
	}
}
