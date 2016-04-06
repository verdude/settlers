package server.database;

import model.ClientModel;
import server.ICatanCommand;

/**
 * Created by Sean_George on 4/5/16.
 */
public interface IGameDAO  {

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

}
