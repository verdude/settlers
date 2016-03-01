package state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class DiscardingState implements IState {

    @Override
    public void initFromModel(IMapView view) {

    }

    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return false;
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        return false;
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return false;
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc, IMapView view) {

    }

    @Override
    public void placeSettlement(VertexLocation vertLoc, IMapView view) {

    }

    @Override
    public void placeCity(VertexLocation vertLoc, IMapView view) {

    }

    @Override
    public void placeRobber(HexLocation hexLoc, IMapView view) {

    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected, IMapView view) {

    }

    @Override
    public void cancelMove() {

    }

    @Override
    public void playSoldierCard() {

    }

    @Override
    public void playRoadBuildingCard() {

    }

    @Override
    public void robPlayer(RobPlayerInfo victim, HexLocation hexLoc) {

    }

    @Override
    public void maritimeTrade() {

    }
}
