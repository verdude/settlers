package state;


import model.ClientException;
import model.ClientFacade;
import model.Player;

/**
 * Created by Sean_George on 2/25/16.
 */
public class RollingState implements IState {
    @Override
    public void sendChat() {

    }

    @Override
    public int rollNumber() {
        try {
            int roll = ClientFacade.getSingleton().rollNumber(ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex());
            return ClientFacade.getSingleton().rollNumber(ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex());


        } catch (ClientException e) {
            e.printStackTrace();
        }


        return 0;
    }

    @Override
    public void robPlayer() {

    }

    @Override
    public void finishTurn() {

    }

    @Override
    public void buyDevCard() {

    }

    @Override
    public void Year_of_Plenty() {

    }

    @Override
    public void Road_Building() {

    }

    @Override
    public void Soldier() {

    }

    @Override
    public void Monopoly() {

    }

    @Override
    public void Monument() {

    }

    @Override
    public void buildRoad() {

    }

    @Override
    public void buildSettlement() {

    }

    @Override
    public void buildCity() {

    }

    @Override
    public void acceptTrade() {

    }

    @Override
    public void offerTrade() {

    }

    @Override
    public void maritimeTrade() {

    }

    @Override
    public void discardCards() {

    }
}