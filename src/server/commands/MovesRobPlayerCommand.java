package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.locations.HexLocation;

/**
 * This represents the moves/robPlayer endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRobPlayerCommand extends ICatanCommand {

	private int playerIndex;
	private int victimIndex;
	private HexLocation location;

	/**
	 * @pre It is the player's turn. The location has the victim on it. The current phase is the robbing phase
	 * @post The current a resource will be removed from the victim and the player will gain one of that resource type.
 	 * @param playerIndex The index of the player
	 * @param victimIndex The index of the victim
	 * @param location The hexlocation where the robber was placed
     */
	public MovesRobPlayerCommand(int playerIndex, int victimIndex, HexLocation location) {
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		ServerFacade.getSingleton().setPlayerIndex(playerIndex);

		String response = facade.robPlayer(victimIndex, location);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
