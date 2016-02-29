package state;

import java.awt.EventQueue;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.*;
import model.EdgeValue;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
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
				try {
					// Rounds
					TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
					PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
					int localPlayerIndex = localPlayer.getPlayerIndex();

					if(turnTracker.getCurrentTurn() == localPlayerIndex){
						// Wait for the road to be placed
						if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 15) {
							startMove(PieceType.ROAD, true, true, view);
						}
						if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 5) {
							startMove(PieceType.SETTLEMENT, true, true, view);
						}

						Timer roadTimer = new Timer();
						roadTimer.schedule(new TimerTask() {
							@Override
							public void run() {
								try {
									System.out.println("Checking road");
									if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() < 15 &&
											ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() < 5) {
										System.out.println("Timer finishing turn");
                                        ClientFacade.getSingleton().finishTurn();
										this.cancel();
                                    }
								} catch (ClientException e) {
									e.printStackTrace();
								}
							}
						}, 0, 500);
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


	 				getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.South),
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
//						getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.NorthWest),
//								CatanColor.RED);
//						getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.SouthWest),
//								CatanColor.BLUE);
//						getView().placeRoad(new EdgeValue(hexLoc, EdgeDirection.South),
//								CatanColor.ORANGE);
//						getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
//						getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
//					}
//				}


			}


//			PortType portType = PortType.BRICK;
//			getView().addPort(new EdgeValue(new HexLocation(0, 3), EdgeDirection.North), portType);
//			getView().addPort(new EdgeValue(new HexLocation(0, -3), EdgeDirection.South), portType);
//			getView().addPort(new EdgeValue(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//			getView().addPort(new EdgeValue(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//			getView().addPort(new EdgeValue(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//			getView().addPort(new EdgeValue(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//
//			getView().placeRobber(new HexLocation(0, 0));
//
//			getView().addNumber(new HexLocation(-2, 0), 2);

		 */
	}

	@Override
	public boolean canPlaceRoad(shared.locations.EdgeLocation edgeLoc) {
		boolean canDo = false;
		try {
			int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			canDo = ClientFacade.getSingleton().getClientModel().canBuildRoad(localPlayerIndex, edgeLoc, true);
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return canDo;
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		boolean canDo = false;
		try {
			int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			canDo = ClientFacade.getSingleton().getClientModel().canBuildSettlement(localPlayerIndex, vertLoc, true);
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return canDo;
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
	public void placeRoad(shared.locations.EdgeLocation edgeLoc, IMapView view) {
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
		// TODO: Call ClientFacade place Settlement
		int playerIndex;
		try {
			playerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();

			VertexObject vertexObject = new VertexObject();
			vertexObject.setVertexLocation(vertLoc);
			vertexObject.setOwner(playerIndex);
			EdgeLocation edgeLocation = new EdgeLocation(vertLoc.getHexLoc(), EdgeDirection.North.fromString(vertLoc.getDirection().toString()));
			vertexObject.setLocation(edgeLocation);

			ClientFacade.getSingleton().buildSettlement(vertexObject, "true");
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
