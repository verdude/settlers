package server.database;

import model.ClientModel;
import server.ICatanCommand;

public interface IDAO {

    /**
     * Adds a user to the users that will be stored persistently.
     * @param username String the is the username
     * @param password String that is the password
     * @pre None
     * @post A user will be added and stored in the chosen persistence model
     */
    public void addUser(String username, String password);

    /**
     * Verifies that a user exists
     * @param username String the username
     * @param password String the password
     * @pre None
     * @post returns false if the user isn't in the system, true otherwise
     */
    public boolean verifyUser(String username, String password);

    /**
     * Stores a command persistently
     * @param command ICatanCommand to be stored
     * @pre None
     * @post Stores a command persistently
     */
    public void storeCommand(ICatanCommand command);

    /**
     * Persistently stores a ClientModel
     * @param clientModel The ClientMOodel to be stored
     * @pre None
     * @post The ClientModel will be persistently stored
     */
    public void storeModel(ClientModel clientModel);
	
}
