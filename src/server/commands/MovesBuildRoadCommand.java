package server.commands;

import model.IFacade;
import server.ICatanCommand;
import shared.locations.EdgeLocation;

/**
 * This represents the moves/buildRoad endpoint
 * @author S Jacob Powell
 *
 */
public class MovesBuildRoadCommand extends ICatanCommand {

	int playerIndex;
	EdgeLocation roadLocation;
	String free;


	/**
	 * @pre A road can be built at the given edge location as given by the associated cando
	 * @post A road will be built for the given player at the given edge location
	 * @param playerIndex the index of the player trying to build the road
	 * @param edgeLocation the edge location at which the road will be built
	 * @param free whether or not the road is free to build
     */
	public MovesBuildRoadCommand(int playerIndex, EdgeLocation edgeLocation, boolean free){
		this .playerIndex = playerIndex;
		this.roadLocation = edgeLocation;
		this.free = "" + free;
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		String response = facade.buildRoad(roadLocation, free);
		
		// TODO: on success, store this command in database
		
		return response;
	}
}
