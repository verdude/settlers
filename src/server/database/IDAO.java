package server.database;

import model.ClientModel;
import server.ICatanCommand;

public interface IDAO {





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
