package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/acceptTrade endpoint
 * @author S Jacob Powell
 *
 */
public class MovesAcceptTradeCommand implements ICatanCommand {
	private int playerIndex;
	private boolean willAccept;
	
	/**
	 * @pre There is a trade from another player to this player
	 * @post The trade is either accepted or not according to "willAccept"
	 * @param playerIndex The index of the player to which the trade accept is going
	 * @param willAccept Whether the player accepts the trade
	 */
	public MovesAcceptTradeCommand(int playerIndex, boolean willAccept) {
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
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
