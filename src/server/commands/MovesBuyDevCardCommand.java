package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

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
		super.setType(this.getClass().toString());

	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.buyDevCard();
		

		return response;
	}
}
