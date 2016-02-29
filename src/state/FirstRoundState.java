package state;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import model.ClientException;
import model.ClientFacade;
import model.EdgeValue;
import model.TurnTracker;
import model.VertexObject;
import shared.definitions.CatanColor;
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

	private static boolean firstTimerRunning = false;
	private static boolean secondTimerRunning = false;

	@Override
	public void initFromModel(final IMapView view) {
		// map init logic goes here!
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					// Rounds

					TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
					PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
					final int localPlayerIndex = localPlayer.getPlayerIndex();
					System.out.println("Player Index: " + localPlayerIndex + ", turnTrackerTurn: " + turnTracker.getCurrentTurn());
					if(turnTracker.getCurrentTurn() == localPlayerIndex) {
						System.out.println("Ots' bug trn");
						// Wait for the road to be placed
                        if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 5 &&
									ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 14) {
							startMove(PieceType.SETTLEMENT, true, true, view);
						}
						if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 15) {
							startMove(PieceType.ROAD, true, true, view);
						}

						if (!firstTimerRunning) {
							System.out.println("Started the timer");
							firstTimerRunning = true;
							Timer roadTimer = new Timer();
							roadTimer.schedule(new TimerTask() {
								@Override
								public void run() {
									try {
										System.out.println("First Timer state one");
										if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 14 &&
												ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 4) {
											System.out.println("Timer finishing turn");
											if (localPlayerIndex != 3) {
												ClientFacade.getSingleton().finishTurn();
											}
											this.cancel();
										}
									} catch (ClientException e) {
										e.printStackTrace();
									}
								}
							}, 0, 500);
						}

						if (localPlayerIndex == 3) {
							// SECOND ROUND STATE FOR LAST PLAYER
							if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 4 &&
									ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 13) {
								startMove(PieceType.SETTLEMENT, true, true, view);
							}
							if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 14 &&
									ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 4) {
								startMove(PieceType.ROAD, true, true, view);
							}

							if (!secondTimerRunning) {
								secondTimerRunning = true;
								Timer timer2 = new Timer();
								timer2.schedule(new TimerTask() {
									@Override
									public void run() {
										try {
											System.out.println("Second timer checking");
											if (ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getRoads() == 13 &&
													ClientFacade.getSingleton().getClientModel().getPlayers()[localPlayerIndex].getSettlements() == 3) {
												System.out.println("Timer finishing second round turn");
												ClientFacade.getSingleton().finishTurn();
												this.cancel();
											}
										} catch (ClientException e) {
											e.printStackTrace();
										}
									}
								}, 0, 500);
							}
						}

					}
				} catch (ClientException e) {
					e.printStackTrace();
				}
			}
		});

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

	@Override
	public void maritimeTrade() {

	}
}
