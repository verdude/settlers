package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;
import shared.locations.VertexLocation;

/**
 * This represents the moves/buildSettlement endpoint
 * @author S Jacob Powell
 *
 */
public class MovesBuildSettlementCommand extends ICatanCommand {
	int playerIndex;
	VertexLocation vertexLocation;
	String free;

	/**
	 * @pre The player can actually build a settlement at the vertex location as given by the cando method
	 * @post A settlement will be built at the given vertex location for the player at the given player index
	 * @param playerIndex index of the player building the settlement
	 * @param vertexLocation location of where the settlement will be built
	 */
	public MovesBuildSettlementCommand(int playerIndex, VertexLocation vertexLocation, boolean free){
		this.playerIndex = playerIndex;
		this.vertexLocation = vertexLocation;
		this.free = "" + free;
		super.setType(this.getClass().toString());
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		((ServerFacade) facade).setPlayerIndex(playerIndex);

		String response = facade.buildSettlement(vertexLocation, free);

		return response;
	}
}
