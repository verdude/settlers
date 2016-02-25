package state;

import state.State;

/**
 * Created by Sean_George on 2/25/16.
 */
public class Context implements State {

    private State state;


    public Context(){
        this.state = null;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


    @Override
    public void sendChat() {
        this.state.sendChat();
    }

    @Override
    public int rollNumber() {
        return this.state.rollNumber();
    }

    @Override
    public void robPlayer() {
        this.state.robPlayer();
    }

    @Override
    public void finishTurn() {
        this.state.finishTurn();
    }

    @Override
    public void buyDevCard() {
        this.state.buyDevCard();
    }

    @Override
    public void Year_of_Plenty() {
        this.state.Year_of_Plenty();
    }

    @Override
    public void Road_Building() {
        this.state.Road_Building();
    }

    @Override
    public void Soldier() {
        this.state.Soldier();
    }

    @Override
    public void Monopoly() {
        this.state.Monopoly();
    }

    @Override
    public void Monument() {
        this.state.Monument();
    }

    @Override
    public void buildRoad() {
        this.state.buildRoad();
    }

    @Override
    public void buildSettlement() {
        this.state.buildSettlement();
    }

    @Override
    public void buildCity() {
        this.state.buildCity();
    }

    @Override
    public void acceptTrade() {
        this.state.acceptTrade();
    }

    @Override
    public void offerTrade() {
        this.state.offerTrade();
    }

    @Override
    public void maritimeTrade() {
        this.state.maritimeTrade();
    }

    @Override
    public void discardCards() {
        this.state.discardCards();
    }
}
