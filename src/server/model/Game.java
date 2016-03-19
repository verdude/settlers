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


}
