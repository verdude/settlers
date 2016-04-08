package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

/**
 * This represents the moves/finishTurn endpoint
 * @author S Jacob Powell
 *
 */
public class MovesFinishTurnCommand extends ICatanCommand {


	int playerIndex;

	/**
	 * @pre The associated cando returns true
	 * @post the player's turn is ended and the next player's turn begins
	 * @param playerIndex index of the player ending their turn
     */
	public MovesFinishTurnCommand(int playerIndex){
		this.playerIndex = playerIndex;
		super.setType(this.getClass().toString());

	}



	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		ServerFacade.getSingleton().setPlayerIndex(playerIndex);

		String response = facade.finishTurn();
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
