package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/Monopoly endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMonopolyCommand implements ICatanCommand {

	private String resource;
	private int playerIndex;

	/**
	 * @pre The playerIndex is valid; the resource is valid, it is the player's turn
	 * @post The player receives all of the cards of that resource from all of the other players.
	 * @param resource The resource that the player wants
	 * @param playerIndex The index of the player playing the monument card
     */
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
