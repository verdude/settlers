package model;

import client.base.IObserver;
import client.data.PlayerInfo;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import state.*;

import java.util.ArrayList;
import java.util.List;

public class ClientFacade  {

	private static ClientModel  clientModel;
	private static ClientFacade singleton;
	private IProxy proxy;
	private PlayerInfo localPlayer;
	private List<IObserver> observers;
	private Context context;
	private boolean gameStarted;

	/* (non-Javadoc)
	 * @see model.IFacade#getContext()
	 */
	public Context getContext() {
		if(context == null){
			context = new Context();
			context.setState(new PlayingState());
		}
		String status = clientModel.getTurnTracker().getStatus();
		if(status != null){
			context.fromString(status);
		}
		return context;
	}



	/**
	 * Default Constructor
	 * 
	 */
	private ClientFacade(IProxy proxy) {
		clientModel	= new ClientModel();
		this.proxy = proxy;
		gameStarted = false;
		observers = new ArrayList<IObserver>();
	}

	/**
	 * Creates and returns the ClientFacade singleton if it doesn't already exist
	 * @return The client facade singleton
	 */
	public static ClientFacade getSingleton(IProxy proxy) {
		if (singleton == null) {
			singleton = new ClientFacade(proxy);
		}
		return singleton;
	}

	public static ClientFacade getSingleton() throws ClientException {
		if (singleton == null) {
			throw new ClientException();
		}
		return singleton;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#getGameStarted()
	 */
	public boolean getGameStarted() {
		return gameStarted;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#getVersion()
	 */
	public int getVersion() {
		return clientModel.getVersion();
	}

	/* (non-Javadoc)
	 * @see model.IFacade#updateModel(model.ClientModel)
	 */
	public void updateModel(ClientModel newModel) {
		clientModel = newModel;
		getContext();
		gameStarted = true;

		for(int i = 0; i < observers.size(); i++) {
			observers.get(i).notify(clientModel);
		}
	}

	/* (non-Javadoc)
	 * @see model.IFacade#notifyAllObservers()
	 */
	public void notifyAllObservers() {
		for(IObserver observer : observers) {
			observer.notify(clientModel);
		}
	}

	/* (non-Javadoc)
	 * @see model.IFacade#addObserver(client.base.IObserver)
	 */
	public void addObserver(IObserver newObserver) {
		observers.add(newObserver);
	}

	/* (non-Javadoc)
	 * @see model.IFacade#createLocalPlayer(java.lang.String)
	 */
	public void createLocalPlayer(String name) {
		localPlayer = new PlayerInfo();
		localPlayer.setName(name);
		try {
			System.out.println(ServerProxy.getSingleton().getPlayerID());
			localPlayer.setId(new Integer(ServerProxy.getSingleton().getPlayerID()));
		} catch (NumberFormatException e) {
			System.out.println("Could not set local player Id");
			e.printStackTrace();
		} catch (ClientException e) {
			System.out.println("User Id Error in cookie. ClientFacade createLocalPlayer.");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see model.IFacade#setLocalPlayer(client.data.PlayerInfo)
	 */
	public void setLocalPlayer(PlayerInfo localPlayer) {
		this.localPlayer = localPlayer;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#getLocalPlayer()
	 */
	public PlayerInfo getLocalPlayer() {
		return localPlayer;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#userLogin(java.lang.String, java.lang.String)
	 */
	public boolean userLogin(String username, String password) {
		String response = proxy.userLogin(username, password);
		createLocalPlayer(username);
		return response.contains("Success");
	}

	/* (non-Javadoc)
	 * @see model.IFacade#userRegister(java.lang.String, java.lang.String)
	 */
	public boolean userRegister(String username, String password) {
		String response = proxy.userRegister(username, password);
		return response.contains("Success");
	}

	/* (non-Javadoc)
	 * @see model.IFacade#gamesList()
	 */
	public String gamesList() {
		return proxy.gamesList();
	}


	/* (non-Javadoc)
	 * @see model.IFacade#gamesCreate(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String gamesCreate(String randomTiles, String randomNumbers, String randomPorts, String name) {
		return proxy.gamesCreate(randomTiles, randomNumbers, randomPorts, name);
	}

	/* (non-Javadoc)
	 * @see model.IFacade#gamesJoin(int, java.lang.String)
	 */
	public boolean gamesJoin(int ID, String color) {
		// do we need a canJoinGame?

		String response = proxy.gamesJoin(ID, color);
		return response.contains("Success");
	}	

	/* (non-Javadoc)
	 * @see model.IFacade#gamesSave(int, java.lang.String)
	 */
	public boolean gamesSave(int ID, String name) {
		String response = proxy.gamesSave(ID, name);

		return response.contains("Success");
	}

	/* (non-Javadoc)
	 * @see model.IFacade#gamesLoad(java.lang.String)
	 */
	public boolean gamesLoad(String name) {
		String response = proxy.gamesLoad(name);
		return response.contains("Success");
	}

	/* (non-Javadoc)
	 * @see model.IFacade#sendChat(java.lang.String, java.lang.String)
	 */

	public boolean sendChat(String playerName, String message) {
		boolean canDo = clientModel.canSendChat(message);
		if(canDo)
		{
			try {
				clientModel.addChatMessage(new MessageLine(message, playerName));
			} catch (ClientException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.sendChat(localPlayer.getPlayerIndex(), message);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#acceptTrade(boolean)
	 */

	public boolean acceptTrade(boolean willAccept) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canAcceptTrade(playerIndex);
		if(canDo)
		{
			String model = proxy.acceptTrade(playerIndex, willAccept);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#discardCards(java.util.List)
	 */

	public boolean discardCards(List<ResourceType> discardedCards) {
		System.out.println("discarding cardz");
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canDiscardCards(playerIndex);
        String model = proxy.discardCards(playerIndex, discardedCards);
        updateModel((ClientModel) Converter.deserializeClientModel(model));
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#rollNumber(int)
	 */

	public int rollNumber(int number) {
		int playerIndex = localPlayer.getPlayerIndex();
		// maybe the player index shouldn't even be passed in. We could just get it here.
		boolean canDo = clientModel.canRollNumber(playerIndex);

		if(canDo)
		{
			String model = proxy.rollNumber(playerIndex, number);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return number;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#buildRoad(model.EdgeValue, java.lang.String)
	 */

	public boolean buildRoad(EdgeValue roadLocation, String free) {
		boolean isFree = free.equals("true");
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canBuildRoad(playerIndex, roadLocation.getLocation(), isFree);
		if(canDo)
		{
			try {
				clientModel.getMap().placeRoad(roadLocation);
				clientModel.getPlayers()[playerIndex].playRoad();
			} catch (ClientException | GameMapException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.buildRoad(playerIndex, roadLocation, free);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#buildSettlement(model.VertexObject, java.lang.String)
	 */

	public boolean buildSettlement(VertexObject vertexObject, String free) {
		boolean isFree = free.equals("true");
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canBuildSettlement(playerIndex, vertexObject.getVertexLocation(), isFree);
		if(canDo)
		{
			try {
				clientModel.getMap().placeSettlement(vertexObject);
				clientModel.getPlayers()[playerIndex].playSettlement();
			} catch (ClientException | GameMapException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.buildSettlement(playerIndex, vertexObject, free);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#buildCity(model.VertexObject)
	 */

	public boolean buildCity(VertexObject vertexObject) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canBuildCity(playerIndex, vertexObject.getVertexLocation());
		if(canDo)
		{
			try {
				clientModel.getMap().placeCity(vertexObject);
				clientModel.getPlayers()[playerIndex].playCity();
			} catch (ClientException | GameMapException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.buildCity(playerIndex, vertexObject);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#offerTrade(model.TradeOffer)
	 */

	public boolean offerTrade(TradeOffer offer) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canOfferTrade(playerIndex);
		if(canDo)
		{
			String model = proxy.offerTrade(playerIndex, offer);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#maritimeTrade(int, shared.definitions.ResourceType, shared.definitions.ResourceType)
	 */

	public boolean maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canMaritimeTrade(playerIndex, inputResource);
		if(canDo)
		{
			String model = proxy.maritimeTrade(playerIndex, ratio, inputResource, outputResource);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#robPlayer(int, shared.locations.HexLocation)
	 */

	public boolean robPlayer(int victimIndex, HexLocation location) {
		
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo;
		if(victimIndex == -1) {
			canDo = true;
		} else{
			canDo = clientModel.canRobPlayer(victimIndex);
		}
		if(canDo)
		{
			try {
				clientModel.getMap().moveRobber(location);
			} catch (GameMapException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.robPlayer(playerIndex, victimIndex, location);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#finishTurn()
	 */

	public boolean finishTurn() {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canFinishTurn(playerIndex);
		if(canDo)
		{
			try {
				clientModel.endTurn();
			} catch (ClientException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.finishTurn(playerIndex);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#buyDevCard()
	 */

	public boolean buyDevCard() {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canBuyDevCard(playerIndex);
		if(canDo)
		{
			try {
				clientModel.getPlayers()[playerIndex].buyDevCard();
			} catch (ClientException e) {
				e.printStackTrace();
				return false;
			}
			String model = proxy.buyDevCard(playerIndex);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}
		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#soldier(int, shared.locations.HexLocation)
	 */

	public boolean soldier(int victimIndex, HexLocation location) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canSoldier(playerIndex);
		if(canDo)
		{
			String model = proxy.soldier(playerIndex, victimIndex, location);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}

		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#yearOfPlenty(shared.definitions.ResourceType, shared.definitions.ResourceType)
	 */

	public boolean yearOfPlenty(ResourceType resource1, ResourceType resource2) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canYearOfPlenty(playerIndex);
		if(canDo)
		{
			String model = proxy.yearOfPlenty(playerIndex, resource1, resource2);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}

		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#roadBuilding(shared.locations.EdgeLocation, shared.locations.EdgeLocation)
	 */

	public boolean roadBuilding(shared.locations.EdgeLocation spot1, shared.locations.EdgeLocation spot2) {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canRoadBuilding(playerIndex);
		if(canDo)
		{
			String model = proxy.roadBuilding(playerIndex, spot1, spot2);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}

		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#monopoly(shared.definitions.ResourceType, int)
	 */

	public boolean monopoly(ResourceType resource, int playerIndex) {
		boolean canDo = clientModel.canMonopoly(playerIndex);
		if(canDo)
		{
			String model = proxy.monopoly(resource, playerIndex);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}

		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#monument()
	 */

	public boolean monument() {
		int playerIndex = localPlayer.getPlayerIndex();
		boolean canDo = clientModel.canMonument(playerIndex);
		if(canDo)
		{
			String model = proxy.monument(playerIndex);
			updateModel((ClientModel) Converter.deserializeClientModel(model));
		}

		return canDo;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#getClientModel()
	 */
	public ClientModel getClientModel(){
		return clientModel;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#vertexLocToEdgeLoc(shared.locations.VertexLocation)
	 */
	public shared.locations.EdgeLocation vertexLocToEdgeLoc(VertexLocation vertexLocation){

		//		EdgeValue edgeLocation = new EdgeValue(vertexLocation.getHexLoc(),vertexLocation.getDirection());
		return null;
	}

	/* (non-Javadoc)
	 * @see model.IFacade#setGameStarted(boolean)
	 */
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
}
