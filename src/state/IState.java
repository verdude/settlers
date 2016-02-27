package state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/24/16.
 */
public interface IState {


    public void initFromModel(IMapView view);

    public boolean canPlaceRoad(EdgeLocation edgeLoc);

    public boolean canPlaceSettlement(VertexLocation vertLoc);

    public boolean canPlaceCity(VertexLocation vertLoc);

    public boolean canPlaceRobber(HexLocation hexLoc);

    public void placeRoad(EdgeLocation edgeLoc, IMapView view);

    public void placeSettlement(VertexLocation vertLoc, IMapView view);

    public void placeCity(VertexLocation vertLoc, IMapView view);

    public void placeRobber(HexLocation hexLoc, IMapView view);

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected, IMapView view);

    public void cancelMove();

    public void playSoldierCard();

    public void playRoadBuildingCard();

    public void robPlayer(RobPlayerInfo victim);






//    public abstract void sendChat();
//
//    public abstract int rollNumber();
//
//    public abstract void robPlayer();
//
//    public abstract void finishTurn();
//
//    public abstract void buyDevCard();
//
//    public abstract void Year_of_Plenty();
//
//    public abstract void Road_Building();
//
//    public abstract void Soldier();
//
//    public abstract void   Monopoly();
//
//    public abstract void  Monument();
//
//    public abstract void buildRoad();
//
//    public abstract void buildSettlement();
//
//    public abstract void buildCity();
//
//    public abstract void acceptTrade();
//
//    public abstract void offerTrade();
//
//    public abstract void maritimeTrade();
//
//    public abstract void discardCards();


}
