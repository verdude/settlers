/**
 * Created by Sean_George on 4/6/16.
 */
public class Main {

    public static void main(String[] arge){

        GameDAO gameDAO = new GameDAO();
        UserDAO userDAO = new UserDAO();

        userDAO.addUser("user","password",0);

        userDAO.addUser("e","3243",1);

        userDAO.addUser("Sean","3243",2);

        System.out.println(userDAO.getUsers());
        System.out.println(userDAO.verifyUser("e","e"));
        System.out.println(userDAO.verifyUser("e","fdfd"));
        System.out.println(userDAO.verifyUser("Sean", "3243"));

        gameDAO = new GameDAO();

        gameDAO.storeGame("jsonObject0",0);
        gameDAO.storeGame("jsonObject1",1);
        gameDAO.storeGame("jsonObject2",2);

        gameDAO.storeCommands("command0",0);
        gameDAO.storeCommands("command1",1);
        gameDAO.storeCommands("command2",1);
        gameDAO.storeCommands("command3",1);
        gameDAO.storeCommands("command4",1);

        System.out.println(gameDAO.getGames());
        System.out.println(gameDAO.getCommands(1));
        
        System.out.println(gameDAO.getCommands(10));


    }
}
