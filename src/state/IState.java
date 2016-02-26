package state;

/**
 * Created by Sean_George on 2/24/16.
 */
public interface IState {

    public abstract void sendChat();

    public abstract int rollNumber();

    public abstract void robPlayer();

    public abstract void finishTurn();

    public abstract void buyDevCard();

    public abstract void Year_of_Plenty();

    public abstract void Road_Building();

    public abstract void Soldier();

    public abstract void   Monopoly();

    public abstract void  Monument();

    public abstract void buildRoad();

    public abstract void buildSettlement();

    public abstract void buildCity();

    public abstract void acceptTrade();

    public abstract void offerTrade();

    public abstract void maritimeTrade();

    public abstract void discardCards();


}
