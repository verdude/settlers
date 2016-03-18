package server.model;

import model.ClientModel;
import model.Player;

/**
 * Created by Sean_George on 3/17/16.
 */
public class Game {

    private int gameID;
    private String name;
    private ClientModel clientModel;

    public Game(int gameID, String name, Player[] players, ClientModel clientModel){
        this.gameID = gameID;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
