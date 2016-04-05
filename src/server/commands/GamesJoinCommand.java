package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the games/join endpoint
 * @author S Jacob Powell
 *
 */
public class GamesJoinCommand extends ICatanCommand {
	private int id;
	private String color;

	/**
	 * @pre There is a game to join with the id
	 * @post The player is added to the game with the id "id" as a player with the color "color"
	 * @param id The id of the game to join
	 * @param color The color of the player that is joining this game
	 */
	public GamesJoinCommand(int id, String color) {
		this.id = id;
		this.color = color;
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {

		String response = facade.gamesJoin(id, color);
		
		// TODO: on success, store this command in database
		
		return response;
	}
}
