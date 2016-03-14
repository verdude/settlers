package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/buyDevCard endpoint
 * @author S Jacob Powell
 *
 */
public class MovesBuyDevCardCommand implements ICatanCommand {

	int playerIndex;

	/**
	 * @pre The associated cando returns true
	 * @post the player will buy a dev card
	 * @param playerIndex index of the player who is buying the dev card
     */
	public MovesBuyDevCardCommand(int playerIndex){
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
