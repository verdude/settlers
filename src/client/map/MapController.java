package client.map;

import java.awt.EventQueue;
import java.util.List;

import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.GameMap;
import model.Hex;
import model.Port;
import model.Road;
import model.VertexObject;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import state.Context;
import client.base.Controller;
import client.base.IObserver;
import client.data.RobPlayerInfo;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController,IObserver {

	private IRobView robView;
	private ClientModel model;


	public MapController(IMapView view, IRobView robView) {


		super(view);
		setRobView(robView);
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
                    List<VertexObject> cities = model.getMap().getCities();
                    List<VertexObject> settlements = model.getMap().getSettlements();
                    List<Road> roads = model.getMap().getRoads();

                    //Place all of the cities front the model on the map
                    for (VertexObject city : cities) {

                        int playerIndex = city.getOwner();
                        CatanColor color = model.getPlayers()[playerIndex].getColor();
                        getView().placeCity(city.getVertexLocation(), color);
                    }

                    //Place all of the settlements front the model on the map
                    for (VertexObject settlement : settlements) {

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

		getView().placeRobber(hexLoc);

		getRobView().showModal();
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
	}
}

