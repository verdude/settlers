package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.locations.HexLocation;

/**
 * This represents the moves/Soldier endpoint
 * @author S Jacob Powell
 *
 */
public class MovesSoldierCommand extends ICatanCommand {

	private int playerIndex;
	private int victimIndex;
	private HexLocation location;

	/**
	 *
	 * @pre The player has a soldier card that is in their old dev cards list. It is their turn
	 * @post The soldier card will be played
	 * @param playerIndex The index of the player playing the soldier command
	 * @param victimIndex The index of the victim
	 * @param location The hex on the the soldier is played.
     */
	public MovesSoldierCommand(int playerIndex, int victimIndex, HexLocation location) {
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
		super.setType(this.getClass().toString());

	}

	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.soldier(victimIndex, location);
		

		return response;
	}
}
