package client.map;

import client.base.Controller;
import client.base.IObserver;
import client.data.RobPlayerInfo;
import model.*;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.*;

import java.util.List;
import java.util.Random;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController,IObserver {

	private IRobView robView;


//	private Context context = ClientFacade.getSingleton().getContext();


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

		//initFromModel();
		try {
			ClientFacade.getSingleton().addObserver(this);
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

		//random placeholder
		// We need to load the map from the model
		// the random stuff needs to be replaced
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {

			int maxY = 3 - x;
			for (int y = -3; y <= maxY; ++y) {
				int r = rand.nextInt(HexType.values().length);
				HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				getView().addHex(hexLoc, hexType);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
						CatanColor.RED);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
						CatanColor.BLUE);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
						CatanColor.ORANGE);
				getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
				getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
			}

			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc, VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc, VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}

		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);

		getView().placeRobber(new HexLocation(0, 0));

		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);

		//</temp>
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



		return true;
	}

	public void placeRoad(EdgeLocation edgeLoc) {

		CatanColor color = null;
		try {
			color = ClientFacade.getSingleton().getLocalPlayer().getColor();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		getView().placeRoad(edgeLoc, color);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		CatanColor color = null;
		try {
			color = ClientFacade.getSingleton().getLocalPlayer().getColor();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		getView().placeSettlement(vertLoc, color);
	}

	public void placeCity(VertexLocation vertLoc) {
		CatanColor color = null;
		try {
			color = ClientFacade.getSingleton().getLocalPlayer().getColor();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		getView().placeCity(vertLoc, color);
	}

	public void placeRobber(HexLocation hexLoc) {

		getView().placeRobber(hexLoc);

		getRobView().showModal();
	}

	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {

		CatanColor color = null;
		try {
			color = ClientFacade.getSingleton().getLocalPlayer().getColor();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		//If it's the startup phase then the third param should be false
		getView().startDrop(pieceType, color, true);
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


		List<City> cities = model.getMap().getCityList();
		List<Settlement> settlements = model.getMap().getSettlementList();
		List<Road> roads = model.getMap().getRoadList();

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


//
//		String gameState = model.getTurnTracker().getStatus();
//		//int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
//
//		switch (gameState){
//			case "Rolling":
//				if(model.getRoll() == 7){
//					context.setState(robbingState);
//				}else {
//					context.setState(playingState);
//				}
//				break;
//			case "Discarding":
//				context.setState(robbingState);
//				break;
//			case "Playing":
//				context.setState(rollingState);
//				break;
//			case "Robbing":
//				context.setState(playingState);
//				break;
//			case "FirstRound":
//				context.setState(secondRoundState);
//				break;
//			case "SecondRound":
//				context.setState(rollingState);
//				break;
//			default:
//				break;
//
//
//			}
//
//		String gameState = model.getTurnTracker().getStatus();
//		//int localPlayerIndex = ClientFacade.getSingleton().getLocalPlayer().getPlayerIndex();
//
//		switch (gameState){
//			case "Rolling":
//				if(model.getRoll() == 7){
//					context.setState(robbingState);
//				}else {
//					context.setState(playingState);
//				}
//				break;
//			case "Discarding":
//				context.setState(robbingState);
//				break;
//			case "Playing":
//				context.setState(rollingState);
//				break;
//			case "Robbing":
//				context.setState(playingState);
//				break;
//			case "FirstRound":
//				context.setState(secondRoundState);
//				break;
//			case "SecondRound":
//				context.setState(rollingState);
//				break;
//			default:
//				break;
//
//
	}
}

