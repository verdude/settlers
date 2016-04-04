package server;

import server.database.IDAO;
import server.database.*;

public class Factory implements PeristenceProvider{

	private  IDAO currentDAO;


	@Override
	public  IDAO getDAO(String db_type, String jar_filename){

		switch (db_type) {
			case "sql":
				currentDAO = (IDAO) new SqlDAO(jar_filename);
				return currentDAO;
			case "ini":
				currentDAO = (IDAO) new IniDAO(jar_filename);
				return currentDAO;
			default:
				System.out.println("ERROR: Invalid databaseType!");
				System.out.println("Valid Types: \"sql\" | \"ini\" ");
				break;
		}
		return null;
	}


	@Override
	public void startTransaction() {

	}

	@Override
	public void endTransaction(boolean commit) {

	}


}
