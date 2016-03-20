package server.commands;

import java.util.ArrayList;

import model.IFacade;
import model.ResourceList;
import server.ICatanCommand;
import shared.definitions.ResourceType;

/**
 * This represents the moves/discardCards endpoint
 * @author S Jacob Powell
 *
 */
public class MovesDiscardCardsCommand extends ICatanCommand {

	int playerIndex;
	ArrayList<ResourceType> discardedCards;

	/**
	 * @pre If it is the discarding state and the associated cando returns true
	 * @param playerIndex index of the player discarding cards
	 * @param discardedCards the resource cards that the player is discarding
     */
	public MovesDiscardCardsCommand(int playerIndex, ArrayList<ResourceType> discardedCards){
		this.playerIndex = playerIndex;
		this.discardedCards = discardedCards;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		String response = facade.discardCards(discardedCards);
		
		// TODO: on success, store this command in database
		
		return response;
	}
}
