package server.database;

import model.ClientModel;
import server.ICatanCommand;

public class SqlDAO implements IDAO{
	
	private Database database;
	
	public SqlDAO(String jar_filename){
		
	}

	@Override
	public void addUser(String username, String password) {

	}

	@Override
	public boolean verifyUser(String username, String password) {
		return false;

	}

	@Override
	public void storeCommand(ICatanCommand command) {

	}

	@Override
	public void storeModel(ClientModel clientModel) {

	}


}
