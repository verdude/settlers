package server.commands;

import server.ICommand;

/**
 * This class represents the game/addAI endpoint
 * @author S Jacob Powell
 *
 */
public class GameAddAICommand implements ICommand {
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
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
