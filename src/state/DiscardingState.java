package state;

import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class DiscardingState implements IState {

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
    public void placeRoad(EdgeLocation edgeLoc) {

    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {

    }

    @Override
    public void placeCity(VertexLocation vertLoc) {

    }

    @Override
    public void placeRobber(HexLocation hexLoc) {

    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

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
    public void robPlayer(RobPlayerInfo victim) {

    }
}
