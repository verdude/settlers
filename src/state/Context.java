package state;

import client.data.RobPlayerInfo;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class Context implements IState {

    private IState state;


    public Context(){
        this.state = null;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState IState) {
        this.state = IState;
    }


    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        return this.state.canPlaceRoad(edgeLoc);
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {
        return this.state.canPlaceSettlement(vertLoc);
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        return this.state.canPlaceCity(vertLoc);
    }

    @Override
    public boolean canPlaceRobber(HexLocation hexLoc) {
        return this.state.canPlaceRobber(hexLoc);
    }

    @Override
    public void placeRoad(EdgeLocation edgeLoc) {
        this.state.placeRoad(edgeLoc);
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc) {
            this.state.placeSettlement(vertLoc);
    }

    @Override
    public void placeCity(VertexLocation vertLoc) {
            this.state.placeCity(vertLoc);
    }

    @Override
    public void placeRobber(HexLocation hexLoc) {
            this.placeRobber(hexLoc);
    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {
            this.state.startMove(pieceType,isFree,allowDisconnected);
    }

    @Override
    public void cancelMove() {
        this.state.cancelMove();
    }

    @Override
    public void playSoldierCard() {
        this.state.playSoldierCard();
    }

    @Override
    public void playRoadBuildingCard() {
        this.state.playRoadBuildingCard();
    }

    @Override
    public void robPlayer(RobPlayerInfo victim) {
        this.state.robPlayer(victim);
    }
}
