import java.sql.*;

import pluginInterfaces.IGameDAO;

import java.io.File;
import java.io.IOException;


public class GameDAO implements IGameDAO {

	private static final String DATABASE_DIRECTORY 	= "sqlite";
	private static final String DATABASE_FILE 		= "persistence.sqlite";
	private static final String DATABASE_URL 		= "jdbc:sqlite:" + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE;
	private File databasePath;
	private Connection connection;

    public GameDAO(){
		try{
			final String driver = "org.sqlite.JDBC";
			Class.forName(driver);
			initDatabase();
		}
		catch(Exception e){
			e.printStackTrace();
		}
    }

    @Override
    public String getGames() {
    	StringBuilder games = new StringBuilder();
    	PreparedStatement stmt = null;
    	ResultSet result = null;
    	
    	games.append("[");
    	
    	try{
    		startTransaction();
    		stmt = connection.prepareStatement("SELECT game FROM Games;");
    		result = stmt.executeQuery();
    		Boolean leadingComma = false;
    		
    		while(result.next()){
    			if(leadingComma){
					games.append(",");
				}
    			
    			String myGame = result.getString(1);
    			myGame = myGame.replace('%', '\"');
    			
//    			System.out.println(myGame);
    			
    			games.append(myGame);
    			leadingComma = true;
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		endTransaction(false);
			safeClose(result);
			safeClose(stmt);
    	}
    	
    	games.append("]");
		return games.toString();
    }

    @Override
    public void storeCommands(String commands, int gameID) {
    	String commandJson = "{%commands%:%" + commands +"%," + "%gameID%:" + gameID + "}";

    	try{
    		startTransaction();
    		connection.prepareStatement("INSERT INTO Commands (gameID, commands) values("+ gameID +", \""+ commandJson +"\");").execute();  
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		endTransaction(false);
    	}
    	finally{
    		endTransaction(true);
    	}

    }

    @Override
    public void storeGame(String game, int gameID) {
      String gameJson = "{%game%:%" + game +"%," + "%gameID%:" + gameID + "}";
      
      try{
    	  startTransaction();
    	  connection.prepareStatement("INSERT INTO Games (gameID, game) values("+ gameID +", \""+ gameJson +"\");").execute();  
      }
      catch(Exception e){
    	  e.printStackTrace();
    	  endTransaction(false);
      }
      finally{
    	  endTransaction(true);
      }

    }

    @Override
    public void startTransaction() {
    	try 
		{		
			connection = DriverManager.getConnection(DATABASE_URL);
			connection.setAutoCommit(false);
		}
		catch (SQLException e) 
		{
			System.out.println("UserDAO startTransaction failed!");
			e.printStackTrace();
		}
    }

    @Override
    public void endTransaction(boolean commit) {
    	if (connection != null) 
		{		
			try 
			{
				if (commit) { connection.commit(); }
				else { connection.rollback(); }
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally 
			{
				safeClose(connection);
				connection = null;
			}
		}
    }

    @Override
    public void clearPersistence() {
    	databasePath.delete();
		initDatabase();
    }

    @Override
    public String getCommands(int gameID) {
//    	get Commands by gameID
    	StringBuilder commands = new StringBuilder();
    	PreparedStatement stmt = null;
    	ResultSet result = null;
    	
    	try{
    		startTransaction();
    		stmt 	= connection.prepareStatement("SELECT commands FROM Commands WHERE gameID = "+ gameID +";");
			result	= stmt.executeQuery();
			
			commands.append("[");
			Boolean leadingComma = false;
			
			while(result.next()){
				if(leadingComma){
					commands.append(",");
				}
				
				String myCommand = result.getString(1);
				myCommand = myCommand.replace('%', '\"');
				
//				System.out.println(myCommand);
				
				commands.append(myCommand);
				leadingComma = true;				
			}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	finally{
    		endTransaction(false);
    		safeClose(result);
    		safeClose(stmt);
    	}
    	
    	commands.append("]");
    	return commands.toString();
	}
    
	private void initDatabase(){
		try{
			databasePath = new File(System.getProperty("user.dir") + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE);

			// Create sqlite database if it doesn't exist
			if(!databasePath.exists()){
				System.out.println("Sqlite Database Created!");
				databasePath.createNewFile();

				startTransaction();
				connection.prepareStatement("CREATE TABLE Users (userID INTEGER, username TEXT, password TEXT);").execute();
				connection.prepareStatement("CREATE TABLE IF NOT EXISTS Games (gameID INTEGER, game TEXT);").execute();
				connection.prepareStatement("CREATE TABLE IF NOT EXISTS Commands (gameID INTEGER, commands TEXT);").execute();
				this.endTransaction(true);
			}
		}
		catch(Exception e){
			System.out.println("Exception in GamesDAO Constructor!");
			e.printStackTrace();
			this.endTransaction(false);
		}
	}
	
	
	private static void safeClose(Connection conn) 
	{
		if (conn != null) 
		{
			try 
			{
				conn.close();
			}
			catch (SQLException e) 
			{}
		}
	}
	

	private static void safeClose(PreparedStatement stmt) 
	{
		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			}
			catch (SQLException e) 
			{}
		}
	}

	private static void safeClose(ResultSet rs) 
	{
		if (rs != null) 
		{
			try 
			{
				rs.close();
			}
			catch (SQLException e) 
			{}
		}
	}

}