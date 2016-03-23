package server;

import model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.model.Game;
import server.model.ServerModel;

import java.io.File;

import static org.junit.Assert.assertTrue;


/**
 * Created by Sean_George on 3/23/16.
 */ public class ServerFacadeTests {


    Game game;
    Player[] players;
    ServerModel serverModel;
//    List<Game> games;

    @Before
    public void setUp() {
        players = new Player[4];
        serverModel = new ServerModel();
        Player player = new Player("testPlayer");
        players[0] = player;
//        games = new ArrayList<>();
        game = new Game(0,"testGame",players,serverModel);

        ServerFacade.getSingleton().getGames().add(game);

    }

    @After
    public void tearDown() {

    }


    @Test
    public void testUserLogin() {

    }

    @Test
    public void testUserRegister() {

    }

    @Test
    public void testGamesList() {

    }

    @Test
    public void testGamesCreate() {

    }

    @Test
    public void testGamesJoin() {

    }

    @Test
    public void testGamesSave() {

        ServerFacade.getSingleton().gamesSave(0,"testSave");

        File file = new File( System.getProperty("user.dir") + "/saves/" + "testSave.txt");
        if(file.exists()){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

    @Test
    public void testGamesLoad() {
        ServerFacade.getSingleton().gamesSave(0,"testLoad");
        System.out.print(ServerFacade.getSingleton().gamesLoad("testLoad.txt"));

    }

    @Test
    public void testSendChat() {

    }

    @Test
    public void testAcceptTrade() {

    }

    @Test
    public void testDiscardCards() {

    }

    @Test
    public void testRollNumber() {

    }

    @Test
    public void testBuildRoad() {

    }

    @Test
    public void testBuildSettlement() {

    }

    @Test
    public void testBuildCity() {

    }

    @Test
    public  void testOfferTrade() {

    }

    @Test
    public void testMaritimeTrade() {

    }

    @Test
    public  void testRobPlayer() {

    }

    @Test
    public void testFinishTurn() {

    }

    @Test
    public void testBuyDevCard() {

    }

    @Test
    public void testSoldier() {

    }

    @Test
    public void testYearOfPlenty() {

    }

    @Test
    public void testRoadBuilding() {

    }

    @Test
    public  void testMonopoly() {

    }

    @Test
    public void testMonument() {

    }

    @Test
    public  void testStoreCommand() {

    }

    @Test
    public void testGamesModel() {

    }


}
