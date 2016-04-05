package server.database;

/**
 * Created by Sean_George on 4/5/16.
 */
public class UserDAO implements IDAO {

    /**
     * Adds a user to the users that will be stored persistently.
     * @param username String the is the username
     * @param password String that is the password
     * @pre None
     * @post A user will be added and stored in the chosen persistence model
     */
    public void addUser(String username, String password) {

    }

    /**
     * Verifies that a user exists
     * @param username String the username
     * @param password String the password
     * @pre None
     * @post returns false if the user isn't in the system, true otherwise
     */
    public boolean verifyUser(String username, String password) {
        return false;
    }


    @Override
    public void startTransaction() {

    }

    @Override
    public void endTransaction(boolean commit) {

    }
}
