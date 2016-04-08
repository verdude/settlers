package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.definitions.ResourceType;

/**
 * This represents the moves/Monopoly endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMonopolyCommand extends ICatanCommand {

	private ResourceType resource;
	private int playerIndex;

	/**
	 * @pre The playerIndex is valid; the resource is valid, it is the player's turn
	 * @post The player receives all of the cards of that resource from all of the other players.
	 * @param resource The resource that the player wants
	 * @param playerIndex The index of the player playing the monument card
     */
	public MovesMonopolyCommand(ResourceType resource, int playerIndex) {
		this.resource = resource;
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

		String response = facade.monopoly(resource, playerIndex);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
