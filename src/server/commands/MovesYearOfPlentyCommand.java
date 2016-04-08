package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.definitions.ResourceType;

/**
 * This represents the moves/YearOfPlenty endpoint
 * @author S Jacob Powell
 *
 */
public class MovesYearOfPlentyCommand extends ICatanCommand {

	private int playerIndex;
	private ResourceType resource1;
	private ResourceType resource2;

	/**
	 *
	 * @pre The player has the year of plenty card and it is in the old dev cards list and it is the player's turn
	 * @post The player will no longer have the year of plenty card and it's effects will be applied
	 * @param playerIndex Index of the player in the playerList array in the model
	 * @param resource1 A resource to give the player
	 * @param resource2 A resource to give the player
     */
	public MovesYearOfPlentyCommand(int playerIndex, ResourceType resource1, ResourceType resource2) {
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public String execute(IFacade facade) {
		ServerFacade.getSingleton().setPlayerIndex(playerIndex);

		String response = facade.yearOfPlenty(resource1, resource2);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
