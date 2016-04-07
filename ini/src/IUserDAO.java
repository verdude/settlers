/**
 * Created by Sean_George on 4/6/16.
 */
public interface IUserDAO {
    /**
     * Adds a user to the users that will be stored persistently.
     * @param username String the is the username
     * @param password String that is the password
     * @pre None
     * @post A user will be added and stored in the chosen persistence model
     */
    public void addUser(String username, String password, int userID);

    /**
     * Verifies that a user exists
     * @param username String the username
     * @param password String the password
     * @pre None
     * @post returns false if the user isn't in the system, true otherwise
     */
    public boolean verifyUser(String username, String password);

    public String getUsers();

    /**
     * Begins a transaction on the database
     * @pre the database exists and is modifiable
     * @post a transaction will be started
     */
    public void startTransaction();

    /**
     * Ends transaction with the database
     * @param commit boolean, whether or not to commit the transaction
     * @pre a transaction has already started
     * @post transaction is ended, it gets committed if commit is true
     */
    public void endTransaction(boolean commit);

    public void clearPersistence();


}
