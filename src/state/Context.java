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
public class Context implements IState {

    private IState state;


    public Context(){
        this.state = new FirstRoundState();
    }

    public IState getState() {
        return state;
    }

    public void setState(IState IState) {
        this.state = IState;
    }


    @Override
    public void initFromModel(IMapView view) {
        this.state.initFromModel(view);
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
    public void placeRoad(EdgeLocation edgeLoc, IMapView view) {
        this.state.placeRoad(edgeLoc,view);
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc, IMapView view) {
            this.state.placeSettlement(vertLoc,view);
    }

    @Override
    public void placeCity(VertexLocation vertLoc, IMapView view) {
            this.state.placeCity(vertLoc,view);
    }

    @Override
    public void placeRobber(HexLocation hexLoc, IMapView view) {
            this.placeRobber(hexLoc,view);
    }

    @Override
    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected,IMapView view) {
            this.state.startMove(pieceType,isFree,allowDisconnected,view);
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

    @Override
    public void maritimeTrade() {

    }


    public void fromString(String status){

        switch (status) {
            case "Rolling":
                this.setState(new RollingState());
                break;
            case "Discarding":
                this.setState(new DiscardingState());
                break;
            case "Playing":
                this.setState(new PlayingState());
                break;
            case "Robbing":
                this.setState(new RobbingState());
                break;
            case "FirstRound":
                this.setState(new FirstRoundState());
                break;
            case "SecondRound":
                this.setState(new SecondRoundState());
                break;
            default:
                break;
        }

    }
}
