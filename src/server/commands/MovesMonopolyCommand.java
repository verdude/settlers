package server.commands;

import server.ICommand;

/**
 * This represents the moves/Monopoly endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMonopolyCommand implements ICommand {

	private String resource;
	private int playerIndex;

	public MovesMonopolyCommand(String resource, int playerIndex) {
		this.resource = resource;
		this.playerIndex = playerIndex;
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
