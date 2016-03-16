package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the games/model endpoint
 * @author S Jacob Powell
 *
 */
public class GamesModelCommand extends ICatanCommand {
	private int version;
	
	/**
	 * @pre There is a game joined
	 * @post The word "True" is returned if the version is the same, otherwise the model is returned
	 * @param version The version that the client currently has of the model
	 */
	public GamesModelCommand(int version) {
		this.version = version;
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public void execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}

}
