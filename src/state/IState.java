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

    public void robPlayer(RobPlayerInfo victim, HexLocation hexLoc);

    public void maritimeTrade();



}
