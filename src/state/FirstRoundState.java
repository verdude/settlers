package state;

import java.awt.EventQueue;
import java.util.List;

import model.ClientException;
import model.ClientFacade;
import model.EdgeValue;
import model.GameMap;
import model.Hex;
import model.Port;
import model.TurnTracker;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import client.map.IMapView;

/**
 * Created by Sean_George on 2/25/16.
 */
public class FirstRoundState implements IState {

	@Override
	public void initFromModel(IMapView view) {
		// map init logic goes here!
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				GameMap map;
				List<Hex> hexes;
				List<Port> ports;
				try {
					map = ClientFacade.getSingleton().getClientModel().getMap();
					hexes = map.getHexes();
					ports = map.getPorts();

					if(hexes.size() < 1){
						return;
					}

					// Print Hexes
					for(int i = 0; i < hexes.size(); i++){
						String type = hexes.get(i).getResource();

						if(type == null){
							type = "DESERT";
						}

						HexType hexType = HexType.valueOf(type.trim().toUpperCase());
						HexLocation hexLoc = new HexLocation(hexes.get(i).getLocation().getX(), hexes.get(i).getLocation().getY());
						view.addHex(hexLoc, hexType);
					}

					// Print WaterHexes
					for(int x = -3; x < 4; x++){

						// left side
						if(x == -3){
							for(int y = 0; y < 4; y++){
								view.addHex(new HexLocation(x, y), HexType.WATER);
							}
						}

						// right side
						else if(x == 3){
							for(int y = 0; y > -4; y--){
								view.addHex(new HexLocation(x, y), HexType.WATER);
							}
						}

						else if(x == -2){
							// y: -1, 3
							view.addHex(new HexLocation(x, -1), HexType.WATER);
							view.addHex(new HexLocation(x, 3), HexType.WATER);
						}

						else if(x == -1 ){
							// y: -2, 3
							view.addHex(new HexLocation(x, -2), HexType.WATER);
							view.addHex(new HexLocation(x, 3), HexType.WATER);
						}

						else if(x == 0){
							// y: -3, 3
							view.addHex(new HexLocation(x, -3), HexType.WATER);
							view.addHex(new HexLocation(x, 3), HexType.WATER);
						}  

						else if(x == 1){
							// y: -3, 2
							view.addHex(new HexLocation(x, -3), HexType.WATER);
							view.addHex(new HexLocation(x, 2), HexType.WATER);
						} 

						else if(x == 2){
							// y: -3, 1
							view.addHex(new HexLocation(x, -3), HexType.WATER);
							view.addHex(new HexLocation(x, 1), HexType.WATER);
						} 

					}

					// Print Ports
					for(int i = 0; i < ports.size(); i++){
						PortType type = ports.get(i).getResource();

						System.out.println("PortType: " + type);

						if(type == null){
							type = PortType.THREE;
						}
						view.addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), type);
					}

					// Rounds
					TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
					PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
					int localPlayerIndex = localPlayer.getPlayerIndex();

					if(turnTracker.getCurrentTurn() == localPlayerIndex){
						view.startDrop(PieceType.SETTLEMENT, localPlayer.getColor(), false);
						view.startDrop(PieceType.ROAD, localPlayer.getColor(), false);

						ClientFacade.getSingleton().finishTurn();
					}


				} catch (ClientException e) {
					e.printStackTrace();
				}
			}
		});



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

		 */
	}

	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		boolean canDo = false;
		try {
			int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			canDo = ClientFacade.getSingleton().getClientModel().canBuildRoad(localPlayerIndex, edgeLoc, true);
		} catch (ClientException e) {
			e.printStackTrace();
		}

		System.out.println("CanBuildRoad: " + canDo);
		return canDo;
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
		// TODO: Call ClientFacade buildRoad
		int playerIndex;
		try {
			playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			EdgeValue edgeValue = new EdgeValue();
			edgeValue.setOwner(playerIndex);
			edgeValue.setLocation(edgeLoc);

			ClientFacade.getSingleton().buildRoad(edgeValue, "true");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void placeSettlement(VertexLocation vertLoc, IMapView view) {
		// TODO: Call ClientFacade placeSettlement
	}

	@Override
	public void placeCity(VertexLocation vertLoc, IMapView view) {
		// TODO: Call ClientFacade placeCity
	}

	@Override
	public void placeRobber(HexLocation hexLoc, IMapView view) {
		// Does nothing in FirstRoundState
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
