package server.commands;

import server.ICommand;

/**
 * This represents the games/join endpoint
 * @author S Jacob Powell
 *
 */
public class GamesJoinCommand implements ICommand {
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
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
