package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/finishTurn endpoint
 * @author S Jacob Powell
 *
 */
public class MovesFinishTurnCommand implements ICatanCommand {


	int playerIndex;

	/**
	 * @pre The associated cando returns true
	 * @post the player's turn is ended and the next player's turn begins
	 * @param playerIndex index of the player ending their turn
     */
	public MovesFinishTurnCommand(int playerIndex){
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
