package state;

import client.data.RobPlayerInfo;
import model.ClientException;
import model.ClientFacade;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class PlayingState implements IState {


    @Override
    public boolean canPlaceRoad(EdgeLocation edgeLoc) {
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
//			EdgeValue edgeValue = new EdgeValue();
//			edgeValue.setLocation(edgeLoc.getNormalizedLocation());

            return ClientFacade.getSingleton().getClientModel().canBuildRoad(playerIndex, edgeLoc.getNormalizedLocation());

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {

        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
            return ClientFacade.getSingleton().getClientModel().canBuildSettlement(playerIndex, vertLoc.getNormalizedLocation());

        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
            return ClientFacade.getSingleton().getClientModel().canBuildCity(playerIndex, vertLoc.getNormalizedLocation());

        } catch (ClientException e) {
            e.printStackTrace();
        }
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
