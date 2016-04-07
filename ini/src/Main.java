/**
 * Created by Sean_George on 4/6/16.
 */
public class Main {

    public static void main(String[] arge){

        GameDAO gameDAO = new GameDAO();
        UserDAO userDAO = new UserDAO();

        userDAO.startTransaction();
        userDAO.addUser("e","e",0);
        userDAO.endTransaction(true);

        userDAO.startTransaction();
        userDAO.addUser("e","3243",0);
        userDAO.endTransaction(false);

        userDAO.startTransaction();
        userDAO.addUser("Sean","3243",0);
        userDAO.endTransaction(true);

        System.out.println(userDAO.getUsers());
        System.out.println(userDAO.verifyUser("e","e"));
        System.out.println(userDAO.verifyUser("e","fdfd"));

//         gameDAO = new GameDAO();
//
//        gameDAO.startTransaction();
//        gameDAO.storeGame("jsonObject",0);
//        gameDAO.endTransaction(true);
//
//        gameDAO.startTransaction();
//        gameDAO.storeCommands("command",0);
//        gameDAO.endTransaction(true);
//
////        gameDAO.startTransaction();
////        gameDAO.addUser("Sean","3243",0);
////        gameDAO.endTransaction(true);
//
//        System.out.println(gameDAO.getGames());

//        System.out.println(userDAO.verifyUser("e","e"));
//        System.out.println(userDAO.verifyUser("e","fdfd"));


    }
}
