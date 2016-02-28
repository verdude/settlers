package client.map;

import client.base.Controller;
import client.base.IObserver;
import client.data.GameInfo;
import client.data.RobPlayerInfo;
import model.*;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.*;
import state.Context;

import java.util.List;
import java.util.Random;

import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import com.sun.security.ntlm.Client;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController,IObserver {

	private IRobView robView;
	private GameMap map;


	public MapController(IMapView view, IRobView robView) {


		super(view);

		// Context context = ClientFacade.getSingleton().getContext();
//		 DiscardingState discardingState = new DiscardingState();
//		 FirstRoundState firstRoundState = new FirstRoundState();
//		 PlayingState playingState = new PlayingState();
//		 RobbingState robbingState = new RobbingState();
//		 RollingState rollingState = new RollingState();
//		 SecondRoundState secondRoundState = new SecondRoundState();


		setRobView(robView);
		
		
		try {
			ClientFacade.getSingleton().addObserver(this);
			map = ClientFacade.getSingleton().getClientModel().getMap();
		} catch (ClientException e) {
			System.out.println("Error when adding to the observer list");
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
		try {
			ClientFacade.getSingleton().getContext().initFromModel(getView());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			System.out.println("Something Broke in MapController!");
			e.printStackTrace();
		}
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
			ClientFacade.getSingleton().getContext().canPlaceSettlement(vertLoc);
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
		GameInfo[] games;
		try {
			games = Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());
            for (GameInfo game : games) {
                if (game.getId() == Integer.parseInt(ServerProxy.getSingleton().getGameID())) {
                    if (game.getPlayers().size() == 4) {
                    	return;
                    }
                    break;
                }
            }
		} catch (ClientException e) {
			System.out.println("Error getting game list");
			e.printStackTrace();
			return;
        }

		initFromModel();

		List<City> cities = model.getMap().getCities();
		List<Settlement> settlements = model.getMap().getSettlements();
		List<Road> roads = model.getMap().getRoads();

		//Place all of the cities front the model on the map
		for (City city : cities) {

			int playerIndex = city.getLocation().getOwner();
			CatanColor color = model.getPlayers()[playerIndex].getColor();
			getView().placeCity(city.getLocation().getLocation(), color);
		}

		//Place all of the settlements front the model on the map
		for (Settlement settlement : settlements) {

			int playerIndex = settlement.getLocation().getOwner();
			CatanColor color = model.getPlayers()[playerIndex].getColor();
			getView().placeSettlement(settlement.getLocation().getLocation(), color);
		}

		//Place all of the roads front the model on the map
		for (Road road : roads) {

			int playerIndex = road.getLocation().getOwner();
			CatanColor color = model.getPlayers()[playerIndex].getColor();
			getView().placeRoad(road.getLocation().getLocation(), color);
		}

		List<Hex> hexList = null;
		try {
			hexList = ClientFacade.getSingleton().getClientModel().getMap().getHexes();
		} catch (ClientException e) {
			e.printStackTrace();
		}



	}
}

