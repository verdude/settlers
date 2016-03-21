package server.model;

import model.Player;

/**
 * Created by Sean_George on 3/17/16.
 */
public class Game {

    private int gameID;
    private String title;
    private ServerModel serverModel;
    boolean randomTiles;
    boolean randomNumbers;
    boolean randomPorts;


    public Game(int gameID, String title, Player[] players, ServerModel serverModel){
        this.gameID = gameID;
        this.title = title;
        this.serverModel = serverModel;
    }


    public ServerModel getServerModel() {
        return serverModel;
    }

    public void setServerModel(ServerModel serverModel) {
        this.serverModel = serverModel;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRandomPorts() {
        return randomPorts;
    }

    public void setRandomPorts(boolean randomPorts) {
        this.randomPorts = randomPorts;
    }

    public boolean isRandomTiles() {
        return randomTiles;
    }

    public void setRandomTiles(boolean randomTiles) {
        this.randomTiles = randomTiles;
    }

    public boolean isRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(boolean randomNumbers) {
        this.randomNumbers = randomNumbers;
    }



}
