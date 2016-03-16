package server.commands;

import model.IFacade;
import server.ICatanCommand;

public class UserRegisterCommand extends ICatanCommand {
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
	public void execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}
}
