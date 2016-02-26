package state;

/**
 * Created by Sean_George on 2/25/16.
 */
public class Context implements IState {

    private IState IState;


    public Context(){
        this.IState = null;
    }

    public IState getState() {
        return IState;
    }

    public void setState(IState IState) {
        this.IState = IState;
    }


    @Override
    public void sendChat() {
        this.IState.sendChat();
    }

    @Override
    public int rollNumber() {
        return this.IState.rollNumber();
    }

    @Override
    public void robPlayer() {
        this.IState.robPlayer();
    }

    @Override
    public void finishTurn() {
        this.IState.finishTurn();
    }

    @Override
    public void buyDevCard() {
        this.IState.buyDevCard();
    }

    @Override
    public void Year_of_Plenty() {
        this.IState.Year_of_Plenty();
    }

    @Override
    public void Road_Building() {
        this.IState.Road_Building();
    }

    @Override
    public void Soldier() {
        this.IState.Soldier();
    }

    @Override
    public void Monopoly() {
        this.IState.Monopoly();
    }

    @Override
    public void Monument() {
        this.IState.Monument();
    }

    @Override
    public void buildRoad() {
        this.IState.buildRoad();
    }

    @Override
    public void buildSettlement() {
        this.IState.buildSettlement();
    }

    @Override
    public void buildCity() {
        this.IState.buildCity();
    }

    @Override
    public void acceptTrade() {
        this.IState.acceptTrade();
    }

    @Override
    public void offerTrade() {
        this.IState.offerTrade();
    }

    @Override
    public void maritimeTrade() {
        this.IState.maritimeTrade();
    }

    @Override
    public void discardCards() {
        this.IState.discardCards();
    }
}
