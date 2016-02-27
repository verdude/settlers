package state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import model.ClientException;
import model.ClientFacade;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class FirstRoundState implements IState {

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
        CatanColor color = null;
        try {
            color = ClientFacade.getSingleton().getLocalPlayer().getColor();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //If it's the startup phase then the third param should be false
        view.startDrop(pieceType, color, false);
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
