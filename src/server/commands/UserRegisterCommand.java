package server.commands;

import server.ICommand;

public class UserRegisterCommand implements ICommand {
	private String username;
	private String password;
	
	/**
	 * @pre The server is started and the user with these credentials does not exist
	 * @post The user is created and logged in to the server
	 * @param username The user's username 
	 * @param password The user's password
	 */
	public UserRegisterCommand(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
