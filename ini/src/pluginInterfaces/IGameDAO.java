package pluginInterfaces;

/**
 * Created by Sean_George on 4/6/16.
 */
public interface IGameDAO {
    /**
     * Stores a command persistently
     * @param commands ICatanCommands to be stored
     * @pre None
     * @post Stores a command persistently
     */
    public void storeCommands(String commands, int gameID);
    /**
     * Persistently stores a ClientModel
     * @param game The Game to be store
     * @pre None
     * @post The ClientModel will be persistently stored
     */
    public void storeGame(String game, int gameID);

    /**
     * Begins a transaction on the database
     * @pre the database exists and is modifiable
     * @post a transaction will be started
     */
    public void startTransaction();

    public String getGames();

    /**
     * Ends transaction with the database
     * @param commit boolean, whether or not to commit the transaction
     * @pre a transaction has already started
     * @post transaction is ended, it gets committed if commit is true
     */
    public void endTransaction(boolean commit);
    public void clearPersistence();
    public String getCommands();


}
