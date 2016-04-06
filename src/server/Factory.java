package server;

import server.database.IGameDAO;
import server.database.IUserDAO;

public class Factory implements PeristenceProvider{

	private  IGameDAO gameDAO;
	private  IUserDAO userDAO;


//	@Override
//	public  IDAO getDAO(String db_type, String jar_filename){
//
//		switch (db_type) {
//			case "sql":
//				//currentDAO = (IDAO) new SqlDAO(jar_filename);
//				return currentDAO;
//			case "ini":
//				//currentDAO = (IDAO) new IniDAO(jar_filename);
//				return currentDAO;
//			default:
//				System.out.println("ERROR: Invalid databaseType!");
//				System.out.println("Valid Types: \"sql\" | \"ini\" ");
//				break;
//		}
//		return null;
//	}


	@Override
	public void startTransaction() {

	}

	@Override
	public void endTransaction(boolean commit) {

	}

	@Override
	public void clearPersistence() {
		
	}

	@Override
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public IGameDAO getGameDAO() {
		return gameDAO;
	}


}
