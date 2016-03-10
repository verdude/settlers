package server.commands;

import server.ICommand;

public class UserLoginCommand implements ICommand {
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
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
