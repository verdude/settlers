package server.commands;

import model.IFacade;
import server.ICatanCommand;

public class UserLoginCommand extends ICatanCommand {
	private String username;
	private String password;
	
	/**
	 * @pre The server is started and the user with these credentials exists
	 * @post The user is logged in to the server
	 * @param username The user's username 
	 * @param password The user's password
	 */
	public UserLoginCommand(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String execute(IFacade facade) {
		String response = facade.userLogin(username, password);
		
		// TODO: on success, store this command in database
		
		return response;
	}
}
