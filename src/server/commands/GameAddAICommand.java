package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This class represents the game/addAI endpoint
 * @author S Jacob Powell
 *
 */
public class GameAddAICommand extends ICatanCommand {
	private String AIType;
	
	/**
	 * Adds an AI to the current game
	 * @pre The AIType is a valid type as a String
	 * @post The AIType is set and the object is created
	 * @param aIType The type of AI to add
	 */
	public GameAddAICommand(String aIType) {
		AIType = aIType;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		return null;
	}
}
