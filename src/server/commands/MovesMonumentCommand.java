package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

/**
 * This represents the moves/Monument endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMonumentCommand extends ICatanCommand {

	private int playerIndex;

	/**
	 * @pre The playerindex is valid (0-4). The player has a monument card. It is the player's turn
	 * @param playerIndex Index of the player playing the monument card
     */
	public MovesMonumentCommand(int playerIndex) {
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

		String response = facade.monument();
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
