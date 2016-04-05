package server.database;

import model.ClientModel;
import server.ICatanCommand;

/**
 * Created by Sean_George on 4/5/16.
 */
public class GameDAO implements IDAO {

    /**
     * Stores a command persistently
     * @param command ICatanCommand to be stored
     * @pre None
     * @post Stores a command persistently
     */
    public void storeCommand(ICatanCommand command){

    }

    /**
     * Persistently stores a ClientModel
     * @param clientModel The ClientMOodel to be stored
     * @pre None
     * @post The ClientModel will be persistently stored
     */
    public void storeModel(ClientModel clientModel){

    }
    @Override
    public void startTransaction() {

    }

    @Override
    public void endTransaction(boolean commit) {

    }
}
