package server;

import server.database.IDAO;

/**
 * Created by Sean_George on 4/4/16.
 */
public interface PeristenceProvider {

    /**
     * @param db_type The command line argument that determines what kind of persistence system will be used.
     * @param jar_filename The path to the jar file
     * @return The proper DAO as given by the command line argument.
     * @pre None
     * @post The proper DAO as given by the command line argument.
     */
    public  IDAO getDAO(String db_type, String jar_filename);

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
