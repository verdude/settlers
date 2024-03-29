package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.definitions.ResourceType;

import java.util.ArrayList;

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
		super.setType(this.getClass().toString());

	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.discardCards(discardedCards);
		

		return response;
	}
}
