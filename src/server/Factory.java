package server;

import org.ini4j.Ini;
import server.database.IGameDAO;
import server.database.IUserDAO;

import java.io.File;
import java.io.FileReader;

public class Factory implements IPeristenceProvider{

	private  static IGameDAO gameDAO;
	private  static IUserDAO userDAO;
	private static final Factory singleton = new Factory();



	public static Factory getSingleton(){
		return singleton;
	}
	private Factory(){
		try {
			File config = new File(System.getProperty("user.dir") + "/plugins/config.ini");
			if(!config.exists()){
				System.out.println("This file doesn't exist!");
			}
			Ini ini = new Ini();
			FileReader fileReader = new FileReader(config);
			ini.load(new FileReader(config));
			Ini.Section section = ini.get("plugins");


			String jarPath = section.get(ServerFacade.getSingleton().getPersistenceType());
			//System.out.println(jarPath);


//			File file = new File("plugins\\" + jarPath);
//
//			String userDAOToLoad = "UserDAO";
//			String gameDAOToLoad = "GameDAO";

//			URL jarUrl = new URL("jar", "","file:" + file.getAbsolutePath()+"!/");
//			URLClassLoader cl = new URLClassLoader(new URL[] {jarUrl}, TestJarLoading.class.getClassLoader());
//			Class loadedClass = cl.loadClass(classToLoad);
//			IUserDAO userDAO = (IUserDAO) loadedClass.newInstance();
//			IGameDAO gameDAO = (IGameDAO) loadedClass.newInstance();

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
