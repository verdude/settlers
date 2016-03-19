package server.model;

import model.ClientModel;
import model.Player;

/**
 * Created by Sean_George on 3/17/16.
 */
public class Game {

    private int gameID;
    private String title;
    private ClientModel clientModel;
    boolean randomTiles;
    boolean randomNumbers;
    boolean randomPorts;


    public Game(int gameID, String title, Player[] players, ClientModel clientModel){
        this.gameID = gameID;
        this.title = title;
        this.clientModel = clientModel;
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
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
