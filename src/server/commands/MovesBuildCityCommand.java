package server.commands;

import model.IFacade;
import server.ICatanCommand;
import shared.locations.VertexLocation;

/**
 * This represents the moves/buildCity endpoint
 * @author S Jacob Powell
 *
 */
public class MovesBuildCityCommand extends ICatanCommand {

	int playerIndex;
	VertexLocation vertexLocation;

	/**
	 * @pre The player can actually build a city at the vertex location as given by the cando method
	 * @post A city will be built at the given vertex location for the player at the given player index
	 * @param playerIndex index of the player building the city
	 * @param vertexLocation location of where the city will be built
     */
	public MovesBuildCityCommand(int playerIndex, VertexLocation vertexLocation){
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
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
