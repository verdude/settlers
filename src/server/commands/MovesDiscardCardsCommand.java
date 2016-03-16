package server.commands;

import model.IFacade;
import model.ResourceList;
import server.ICatanCommand;

/**
 * This represents the moves/discardCards endpoint
 * @author S Jacob Powell
 *
 */
public class MovesDiscardCardsCommand extends ICatanCommand {

	int playerIndex;
	ResourceList discardedCards;

	/**
	 * @pre If it is the discarding state and the associated cando returns true
	 * @param playerIndex index of the player discarding cards
	 * @param discardedCards the resource cards that the player is discarding
     */
	public MovesDiscardCardsCommand(int playerIndex, ResourceList discardedCards){
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
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
