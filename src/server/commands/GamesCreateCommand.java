package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the games/create endpoint
 * @author S Jacob Powell
 *
 */
public class GamesCreateCommand extends ICatanCommand {
	private String randomTiles;
	private String randomNumbers;
	private String randomPorts;
	private String name;
	
	/**
	 * @pre The server is running and games can be created
	 * @post A game with the name "name" is added to the server, generated according to the boolean parameters given.
	 * @param randomTiles Whether random tiles should be generated 
	 * @param randomNumbers Whether random numbers on the board should be generated or the default
	 * @param randomPorts Whether random port placement should be chosen
	 * @param name The name of the game to be created
	 */
	public GamesCreateCommand(boolean randomTiles, boolean randomNumbers,
			boolean randomPorts, String name) {
		this.randomTiles = "" + randomTiles;
		this.randomNumbers = "" + randomNumbers;
		this.randomPorts = "" + randomPorts;
		this.name = name;
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		String response = facade.gamesCreate(randomTiles, randomNumbers, randomPorts, name);
		
		// TODO: on success, store this command in database
		facade.storeCommand(this);
		return response;
	}
}
