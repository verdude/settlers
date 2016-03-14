package server.commands;

import server.ICommand;

/**
 * This represents the moves/YearOfPlenty endpoint
 * @author S Jacob Powell
 *
 */
public class MovesYearOfPlentyCommand implements ICommand {

	private int playerIndex;
	private ResourceType resource1;
	private ResourceType resource2;

	public MovesYearOfPlentyCommand(int playerIndex, ResourceType resource1, ResourceType resource2) {
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
