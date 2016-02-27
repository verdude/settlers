package state;

import java.util.List;
import java.util.Random;

import model.ClientException;
import model.ClientFacade;
import model.GameMap;
import model.Hex;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.data.RobPlayerInfo;
import client.map.IMapView;

/**
 * Created by Sean_George on 2/25/16.
 */
public class FirstRoundState implements IState {

    @Override
    public void initFromModel(IMapView view) {
    	// map init logic goes here!
    	
    	GameMap map;
    	List<Hex> hexes;
		try {
			map = ClientFacade.getSingleton().getClientModel().getMap();
			hexes = map.getHexes();
			
			if(hexes.size() < 1){
				return;
			}
			
			
			for(int i = 0; i < hexes.size(); i++){
				String resourceType = hexes.get(i).getResource();
				
				if(resourceType == null){
					resourceType = "DESERT";
				}
				
				HexType hexType = HexType.valueOf(resourceType.trim().toUpperCase());
				HexLocation hexLoc = new HexLocation(hexes.get(i).getLocation().getX(), hexes.get(i).getLocation().getY());
				view.addHex(hexLoc, hexType);
			}
			
			
			/*
			Random rand = new Random();
			
			for (int x = 0; x <= 3; ++x) {

				int maxY = 3 - x;
				for (int y = -3; y <= maxY; ++y) {
					
					System.out.println("X: " + x);
					System.out.println("Y: " + y);
					
					int r = rand.nextInt(HexType.values().length);
					System.out.println("HexType: " + HexType.values()[r]);
					
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(x, y);
					view.addHex(hexLoc, hexType);
					
								
	 				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
					
				}
				
				
//				if (x != 0) {
//					int minY = x - 3;
//					for (int y = minY; y <= 3; ++y) {
//						int r = rand.nextInt(HexType.values().length);
//						HexType hexType = HexType.values()[r];
//						HexLocation hexLoc = new HexLocation(-x, y);
//						getView().addHex(hexLoc, hexType);
//						getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//								CatanColor.RED);
//						getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//								CatanColor.BLUE);
//						getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//								CatanColor.ORANGE);
//						getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
//						getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
//					}
//				}
				
				
			}


//			PortType portType = PortType.BRICK;
//			getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
//			getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
//			getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//			getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//			getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//			getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//
//			getView().placeRobber(new HexLocation(0, 0));
//
//			getView().addNumber(new HexLocation(-2, 0), 2);
//			getView().addNumber(new HexLocation(-2, 1), 3);
//			getView().addNumber(new HexLocation(-2, 2), 4);
//			getView().addNumber(new HexLocation(-1, 0), 5);
//			getView().addNumber(new HexLocation(-1, 1), 6);
//			getView().addNumber(new HexLocation(1, -1), 8);
//			getView().addNumber(new HexLocation(1, 0), 9);
//			getView().addNumber(new HexLocation(2, -2), 10);
//			getView().addNumber(new HexLocation(2, -1), 11);
//			getView().addNumber(new HexLocation(2, 0), 12);
			
			*/

			//</temp>
			
			
			
			
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
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
