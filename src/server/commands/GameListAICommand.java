package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the game/ListAI endpoint
 * There are no parameters for this command, so use the default constructor.
 * @author S Jacob Powell
 *
 */
public class GameListAICommand extends ICatanCommand {


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		return null;
	}
}
