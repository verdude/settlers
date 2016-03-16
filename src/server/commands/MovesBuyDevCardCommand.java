package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the moves/buyDevCard endpoint
 * @author S Jacob Powell
 *
 */
public class MovesBuyDevCardCommand extends ICatanCommand {

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
	public void execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}
}
