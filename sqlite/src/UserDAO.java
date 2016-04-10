import java.sql.*;

import pluginInterfaces.IUserDAO;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sean_George on 4/6/16.
 */
public class UserDAO implements IUserDAO {

	private static final String DATABASE_DIRECTORY 	= "sqlite";
	private static final String DATABASE_FILE 		= "persistence.sqlite";
	private static final String DATABASE_URL 		= "jdbc:sqlite:" + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE;
	private File databasePath;
	private Connection connection;

	public UserDAO(){
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
	public void addUser(String username, String password, int userID) {
		//        String json = "{%username%:%" + username +"%," + "%password%:%" + password + "%," + "%userID%:" + userID + "}";
		
		PreparedStatement  stmt = null;
		ResultSet 		 result = null;
		Boolean 	needsInsert = true;

		/* Check if user already exists
		 * 	- TRUE: update
		 * 	- FALSE: insert
		 */    	

		try{
			startTransaction();
			stmt 	= connection.prepareStatement("SELECT * FROM Users WHERE userID = " + userID);
			result 	= stmt.executeQuery();

			// test result for size
			while(result.next()){
				// Update
				connection.prepareStatement("UPDATE Users SET username = \""+ username +"\", password = \""+ password +"\" WHERE userID = "+ userID +";").executeUpdate();
				needsInsert = false;
				break;
			}

			if(needsInsert){
				connection.prepareStatement("INSERT INTO Users (userID, username, password) values("+ userID +", \""+ username +"\", \""+ password +"\");").execute();
			}
		}
		catch(Exception e){
			System.out.println("Exception in UserDAO addUser()");
			e.printStackTrace();
			endTransaction(false);
		}
		finally{
			endTransaction(true);
			safeClose(stmt);
		}
	}

	@Override
	public boolean verifyUser(String username, String password) {
		/*        String json = ini.get(SECTION, username);

        return json.contains("\"password\":\"" + password + "\"");*/

		PreparedStatement   stmt = null;
		ResultSet 		  result = null;
		Boolean 	userVerified = false;

		/* Verify:
		 * 1. Select all users with given username
		 * 2. test given password against array of username's password
		 */

		try{
			startTransaction();    		
			stmt = connection.prepareStatement("SELECT username, password FROM Users WHERE username = \""+ username +"\";");
			result = stmt.executeQuery();


			while(result.next()){
				String result_username = result.getString(1);
				String result_password = result.getString(2);


				//    			System.out.println("GIVEN : " + username + ", " + password);
				//    			System.out.println("RESULT: " + result_username + ", " + result_password);

				if(result_username.equals(username) && result_password.equals(password)){
					userVerified = true;
					break;
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception in UserDAO verifyUser()");
			e.printStackTrace();
		}
		finally{
			endTransaction(true);
			safeClose(result);
			safeClose(stmt);
		}

		return userVerified;
	}

	@Override
	public String getUsers() {  	
		StringBuilder users = new StringBuilder();
		PreparedStatement stmt = null;
		ResultSet result = null;
		users.append("[");

		// Add Users to JSON Array
		try{
			startTransaction();
			stmt 	= connection.prepareStatement("SELECT username, password, userID FROM Users;");
			result	= stmt.executeQuery();
			Boolean leadingComma = false;
			
			while(result.next()){
				if(leadingComma){
					users.append(",");
				}

				//    	        "{%username%:%" + username +"%," + "%password%:%" + password + "%," + "%userID%:" + userID + "}";


				StringBuilder myUser = new StringBuilder();

				String result_username = result.getString(1);
				String result_password = result.getString(2);
				int 	 result_userID = result.getInt(3);

				myUser.append("{\"username\":\""+ result_username +"\","
						+ "\"password\":\""+ result_password +"\","
						+ "\"userID\": "   + result_userID   + "}");

				users.append(myUser);
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

		users.append("]");
		return users.toString();
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

	private void initDatabase(){
		try{
			databasePath = new File(System.getProperty("user.dir") + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_DIRECTORY + File.separator + DATABASE_FILE);

			// Create sqlite database if it doesn't exist
			if(!databasePath.exists()){
				System.out.println("Sqlite Database Created!");
				databasePath.createNewFile();

				startTransaction();
				connection.prepareStatement("CREATE TABLE IF NOT EXISTS Users (userID INTEGER, username TEXT, password TEXT);").execute();
				connection.prepareStatement("CREATE TABLE IF NOT EXISTS Games (gameID INTEGER, game TEXT);").execute();
				connection.prepareStatement("CREATE TABLE IF NOT EXISTS Commands (gameID INTEGER, commands TEXT);").execute();
				this.endTransaction(true);
			}
		}
		catch(Exception e){
			System.out.println("Exception in UserDAO Constructor!");
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
