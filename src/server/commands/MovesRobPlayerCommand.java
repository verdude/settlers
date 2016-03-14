package server.commands;

import server.ICommand;

/**
 * This represents the moves/robPlayer endpoint
 * @author S Jacob Powell
 *
 */
public class MovesRobPlayerCommand implements ICommand {

	private int playerIndex;
	private int victimIndex;
	private HexLocation location;

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
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
