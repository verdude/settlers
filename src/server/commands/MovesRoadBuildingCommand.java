package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the moves/roadBuilding endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRoadBuildingCommand extends ICatanCommand {

	private int playerIndex;
	/*
	// I'm not sure what type these spots are supposed to be.
	spot1: {
		"x": int,
		"y": int,
		"direction": string
	}
	spot2: {
		"x": int,
		"y": int,
		"direction": string
	}
	*/

	/**
	 * @pre it is the player's turn and the player can place a road in the spots that are specified
	 * @post the Road building card effect is applied and the player is allowed to place roads
	 * @param playerIndex the index of the player
	 * @param spot1 The first road placement
	 * @param spot2 the second road placement
     */
	public MovesRoadBuildingCommand(int playerIndex /*The spots*/) {
		this.playerIndex = playerIndex;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}
}
