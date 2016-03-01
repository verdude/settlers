package client.map;

import client.base.Controller;
import client.base.IObserver;
import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import model.*;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import state.Context;
import state.FirstRoundState;
import state.RobbingState;
import state.SecondRoundState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController,IObserver {

	private IRobView robView;
	private ClientModel model;
	private HexLocation robber;

	public MapController(IMapView view, IRobView robView) {


		super(view);
		setRobView(robView);
		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			System.out.println("Error when adding to the mapController to the observer list");

			e.printStackTrace();
		}
	}

	public IMapView getView() {
		return (IMapView) super.getView();
	}

	private IRobView getRobView() {
		return robView;
	}

	private void setRobView(IRobView robView) {
		this.robView = robView;
	}

	protected void initFromModel() {



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
						getView().addHex(hexLoc, hexType);
						if (hexes.get(i).getNumber() == 0) {
							continue;
						}
						getView().addNumber(hexLoc, hexes.get(i).getNumber());
					}

					// Print WaterHexes
					for(int x = -3; x < 4; x++){

						// left side
						if(x == -3){
							for(int y = 0; y < 4; y++){
								getView().addHex(new HexLocation(x, y), HexType.WATER);
							}
						}

						// right side
						else if(x == 3){
							for(int y = 0; y > -4; y--){
								getView().addHex(new HexLocation(x, y), HexType.WATER);
							}
						}

						else if(x == -2){
							// y: -1, 3
							getView().addHex(new HexLocation(x, -1), HexType.WATER);
							getView().addHex(new HexLocation(x, 3), HexType.WATER);
						}

						else if(x == -1 ){
							// y: -2, 3
							getView().addHex(new HexLocation(x, -2), HexType.WATER);
							getView().addHex(new HexLocation(x, 3), HexType.WATER);
						}

						else if(x == 0){
							// y: -3, 3
							getView().addHex(new HexLocation(x, -3), HexType.WATER);
							getView().addHex(new HexLocation(x, 3), HexType.WATER);
						}

						else if(x == 1){
							// y: -3, 2
							getView().addHex(new HexLocation(x, -3), HexType.WATER);
							getView().addHex(new HexLocation(x, 2), HexType.WATER);
						}

						else if(x == 2){
							// y: -3, 1
							getView().addHex(new HexLocation(x, -3), HexType.WATER);
							getView().addHex(new HexLocation(x, 1), HexType.WATER);
						}

					}

					//Print ports
					for(int i = 0; i < ports.size(); i++){
						PortType type = ports.get(i).getResource();

						if(type == null){
							type = PortType.THREE;
						}
						getView().addPort(new EdgeLocation(ports.get(i).getLocation(), ports.get(i).getDirection()), type);
					}

					// Rounds

					TurnTracker turnTracker = ClientFacade.getSingleton().getClientModel().getTurnTracker();
					
					PlayerInfo localPlayer = ClientFacade.getSingleton().getLocalPlayer();
					int localPlayerIndex = localPlayer.getPlayerIndex();

//					if(turnTracker.getCurrentTurn() == localPlayerIndex){
//					getView().startDrop(PieceType.SETTLEMENT, localPlayer.getColor(), false);
//						getView().startDrop(PieceType.ROAD, localPlayer.getColor(), false);
//
//						ClientFacade.getSingleton().finishTurn(localPlayerIndex);
//					}

//					ClientFacade.getSingleton().getContext().startMove(PieceType.SETTLEMENT,true,true,getView());
//					ClientFacade.getSingleton().getContext().startMove(PieceType.SETTLEMENT,true,true,getView());


                    List<VertexObject> cities = model.getMap().getCities();
                    List<VertexObject> settlements = model.getMap().getSettlements();
                    List<Road> roads = model.getMap().getRoads();

                    //Place all of the cities front the model on the map
                    for (VertexObject city : cities) {

                        int playerIndex = city.getOwner();
                        CatanColor color = model.getPlayers()[playerIndex].getColor();
                        getView().placeCity(city.getVertexLocation(), color);
                    }

					boolean firstRounds = false;
					try {
						firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
								ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
					} catch (ClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //Place all of the settlements front the model on the map
                    for (VertexObject settlement : settlements) {
						List<HexLocation> settHexesNW = new ArrayList<>();
						List<HexLocation> settHexesNE = new ArrayList<>();

						if(firstRounds) {

							settHexesNW.add(new HexLocation(-3, 1));
							settHexesNW.add(new HexLocation(-3, 2));
							settHexesNW.add(new HexLocation(-3, 3));

							settHexesNE.add(new HexLocation(3, -2));
							settHexesNE.add(new HexLocation(3, -1));
							settHexesNE.add(new HexLocation(3, 0));

							if (settlement.getVertexLocation() == null) {


								HexLocation settHexLoc = new HexLocation(settlement.getLocation().getX(), settlement.getLocation().getX());
								settlement.setVertexLocation(new VertexLocation(settHexLoc,null));

								if(settHexesNE.contains(settHexLoc)){
									settlement.getVertexLocation().setVertexDirection(VertexDirection.NorthWest);
								}else if(settHexesNW.contains(settHexLoc)){
									settlement.getVertexLocation().setVertexDirection(VertexDirection.NorthWest);

								}

							}
						}


                        int playerIndex = settlement.getOwner();
                        CatanColor color = model.getPlayers()[playerIndex].getColor();
						getView().placeSettlement(settlement.getVertexLocation(), color);

                    }

                    //Place all of the roads front the model on the map
                    for (Road road : roads) {

                        int playerIndex = road.getOwner();

                        CatanColor color = model.getPlayers()[playerIndex].getColor();
                        getView().placeRoad(road.getLocation(), color);
                    }

                    getView().placeRobber(model.getMap().getRobber());
                    
				} catch (ClientException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {

		boolean canDo = false;
		try {
			canDo = ClientFacade.getSingleton().getContext().canPlaceRoad(edgeLoc);
		} catch (ClientException e) {
			e.printStackTrace();
		}

		return canDo;


	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {

		boolean canDo = false;
		try {
			canDo = ClientFacade.getSingleton().getContext().canPlaceSettlement(vertLoc);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return canDo;

	}

	public boolean canPlaceCity(VertexLocation vertLoc) {

		boolean canDo = false;
		try {
			canDo = ClientFacade.getSingleton().getContext().canPlaceCity(vertLoc);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return canDo;

	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		boolean canDo = false;
		try {
			canDo =  ClientFacade.getSingleton().getContext().canPlaceRobber(hexLoc);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return canDo;

	}

	public void placeRoad(EdgeLocation edgeLoc) {

		Context context = null;
		try {
			context = ClientFacade.getSingleton().getContext();
			context.placeRoad(edgeLoc,getView());
		}catch (ClientException e){
			e.getStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc) {
		Context context = null;
		try {
			context = ClientFacade.getSingleton().getContext();
			context.placeSettlement(vertLoc,getView());
		}catch (ClientException e){
			e.getStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc) {
		Context context = null;
		try {
			context = ClientFacade.getSingleton().getContext();
			context.placeCity(vertLoc,getView());
		}catch (ClientException e){
			e.getStackTrace();
		}
	}

	public void placeRobber(HexLocation hexLoc) {

		robber = hexLoc;
		try {
			ClientFacade.getSingleton().getClientModel().getMap().setRobber(robber);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		getView().placeRobber(hexLoc);
		getRobView().showModal();
		List<RobPlayerInfo> victims = new ArrayList<RobPlayerInfo>();
		try {
			ClientModel model = ClientFacade.getSingleton().getClientModel();
			int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
			for(int i = 0; i < model.getPlayers().length; i++) {
				if(model.canRobPlayer(i) && i != localPlayerIndex) {
					Player player = model.getPlayers()[i];
					RobPlayerInfo victim = new RobPlayerInfo();
					victim.setColor(player.getColor());
					victim.setId(player.getPlayerID());
					victim.setName(player.getName());
					victim.setNumCards(player.getResources().getTotal());
					victim.setPlayerIndex(player.getPlayerIndex());
					victims.add(victim);
				}
			}
			RobPlayerInfo[] victimArray = new RobPlayerInfo[victims.size()];
			for(int i = 0; i < victimArray.length; i++) {
				victimArray[i] = victims.get(i);
			}
			getRobView().setPlayers(victimArray);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

		try {
			ClientFacade.getSingleton().getContext().startMove(pieceType,isFree,allowDisconnected,getView());
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	public void cancelMove() {

	}

	public void playSoldierCard() {

	}

	public void playRoadBuildingCard() {

	}

	@Override
	public void robPlayer(RobPlayerInfo victim) {
		try {
			ClientFacade.getSingleton().getContext().robPlayer(victim, robber);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		try {
			if (ClientFacade.getSingleton().getGameStarted() == false) {
                System.out.println("Returned in turn tracker. Game not started");
                return;
            }
		} catch (ClientException e) {
			e.printStackTrace();
		}

		this.model = model;
		initFromModel();
		try {
			ClientFacade.getSingleton().getContext().initFromModel(getView());
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
		try {
			if(ClientFacade.getSingleton().getContext().getState() instanceof RobbingState) {
				startMove(PieceType.ROBBER, true, false);
				System.out.println("placing robber");
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

