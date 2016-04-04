package server.database;

import model.ClientModel;
import server.ICatanCommand;

public class IniDAO implements IDAO{

	private Database database;

	private String jarName;
	
	public IniDAO(String jar_filename){
		jarName = jar_filename;
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
	
	public String getJarName() {
		return jarName;
	}

	public void setJarName(String jarName) {
		this.jarName = jarName;
	}

}