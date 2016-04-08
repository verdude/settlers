package server.commands;

import model.IFacade;
import server.ICatanCommand;
import server.ServerFacade;

/**
 * This represents the moves/acceptTrade endpoint
 * @author S Jacob Powell
 *
 */
public class MovesAcceptTradeCommand extends ICatanCommand {
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
	public String execute(IFacade facade) {
		ServerFacade.getSingleton().setPlayerIndex(playerIndex);

		String response = facade.acceptTrade(willAccept);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
