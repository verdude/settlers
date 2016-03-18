package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the games/list endpoint
 * This command has no input parameters, so will use the default constructor
 * @author S Jacob Powell
 *
 */
public class GamesListCommand extends ICatanCommand {


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public void execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}
}