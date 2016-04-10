package server;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLClassLoader;

import org.ini4j.Ini;

import pluginInterfaces.IGameDAO;
import pluginInterfaces.IUserDAO;

public class Factory implements IPeristenceProvider{

	private  static IGameDAO gameDAO;
	private  static IUserDAO userDAO;
	private static  Factory singleton = null;



	public static Factory getSingleton(String persistenceType){
		if(singleton == null) {
			singleton = new Factory(persistenceType);
		}
		return singleton;
	}
	private Factory(String persistenceType){
		try {
			File config = new File(System.getProperty("user.dir") + "/plugins/config.ini");
			if(!config.exists()){
				System.out.println("This file doesn't exist!");
			}
			Ini ini = new Ini();
			FileReader fileReader = new FileReader(config);
			ini.load(new FileReader(config));
			Ini.Section section = ini.get("plugins");


			String jarPath = section.get(persistenceType);


			File file = new File("plugins/" + jarPath);

			String userDAOToLoad = "UserDAO";
			String gameDAOToLoad = "GameDAO";

			URL jarUrl = new URL("jar", "","file:" + file.getAbsolutePath()+"!/");
			URLClassLoader cl = new URLClassLoader(new URL[] {jarUrl}, Factory.class.getClassLoader());
			Class userDAOLoaded = cl.loadClass(userDAOToLoad);
			Class gameDAOLoaded = cl.loadClass(gameDAOToLoad);
			userDAO = (IUserDAO) userDAOLoaded.newInstance();
			gameDAO = (IGameDAO) gameDAOLoaded.newInstance();
//			userDAO.startTransaction();
//			userDAO.addUser("e","e",0);
//			userDAO.endTransaction(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


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
	public  void startTransaction() {
		gameDAO.startTransaction();
		userDAO.startTransaction();


	}

	@Override
	public  void endTransaction(boolean commit) {
		gameDAO.endTransaction(commit);
		userDAO.endTransaction(commit);
	}

	@Override
	public  void clearPersistence() {
		gameDAO.clearPersistence();
		userDAO.clearPersistence();
	}

	@Override
	public  IUserDAO getUserDAO() {
		return userDAO;
	}

	@Override
	public  IGameDAO getGameDAO() {
		return gameDAO;
	}


}
