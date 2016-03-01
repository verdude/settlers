package state;

import client.data.RobPlayerInfo;
import client.map.IMapView;
import model.ClientException;
import model.ClientFacade;
import model.EdgeValue;
import model.VertexObject;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Created by Sean_George on 2/25/16.
 */
public class PlayingState implements IState {


    @Override
    public void initFromModel(IMapView view) {

    }

    @Override
    public boolean canPlaceRoad(shared.locations.EdgeLocation edgeLoc) {
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
//			EdgeValue edgeValue = new EdgeValue();
//			edgeValue.setVertexLocation(edgeLoc.getNormalizedLocation());

            return ClientFacade.getSingleton().getClientModel().canBuildRoad(playerIndex,
                    edgeLoc.getNormalizedLocation(),false);

        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canPlaceSettlement(VertexLocation vertLoc) {

        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
            return ClientFacade.getSingleton().getClientModel().canBuildSettlement(playerIndex,
                    vertLoc.getNormalizedLocation(),false);

        } catch (ClientException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean canPlaceCity(VertexLocation vertLoc) {
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
            return ClientFacade.getSingleton().getClientModel().canBuildCity(playerIndex,
                    vertLoc.getNormalizedLocation());

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
    public void placeRoad(shared.locations.EdgeLocation edgeLoc, IMapView view) {
        CatanColor color = null;
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
            color = ClientFacade.getSingleton().getLocalPlayer().getColor();
            EdgeValue edgeValue = new EdgeValue();
            edgeValue.setLocation(edgeLoc);
            edgeValue.setOwner(playerIndex);

            ClientFacade.getSingleton().buildRoad(edgeValue,"false");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        view.placeRoad(edgeLoc, color);
    }

    @Override
    public void placeSettlement(VertexLocation vertLoc, IMapView view) {
        CatanColor color = null;
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

            color = ClientFacade.getSingleton().getLocalPlayer().getColor();
            VertexObject vertexObject = new VertexObject();
            vertexObject.setOwner(playerIndex);
            vertexObject.setVertexLocation(vertLoc);
            ClientFacade.getSingleton().buildSettlement(vertexObject,"false");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        view.placeSettlement(vertLoc, color);
    }

    @Override
    public void placeCity(VertexLocation vertLoc, IMapView view) {
        CatanColor color = null;
        try {
            int playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

            color = ClientFacade.getSingleton().getLocalPlayer().getColor();
            VertexObject vertexObject = new VertexObject();
            vertexObject.setOwner(playerIndex);
            vertexObject.setVertexLocation(vertLoc);
            ClientFacade.getSingleton().buildCity(vertexObject);

        } catch (ClientException e) {
            e.printStackTrace();
        }
        view.placeCity(vertLoc, color);
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
        view.startDrop(pieceType, color, true);


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
