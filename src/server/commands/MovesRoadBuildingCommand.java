package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.locations.EdgeLocation;

/**
 * This represents the moves/roadBuilding endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRoadBuildingCommand extends ICatanCommand {

	private int playerIndex;
	EdgeLocation spot1, spot2;
	
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
	public MovesRoadBuildingCommand(int playerIndex, EdgeLocation spot1, EdgeLocation spot2 /*The spots*/) {
		this.playerIndex = playerIndex;
		this.spot1 = spot1;
		super.setType(this.getClass().toString());

		this.spot2 = spot2;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.roadBuilding(spot1, spot2);

		return response;
	}
}
