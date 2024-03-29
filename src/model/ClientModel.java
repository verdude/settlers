package model;

import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.*;
import state.FirstRoundState;
import state.PlayingState;
import state.SecondRoundState;

import java.util.ArrayList;
import java.util.List;

public class ClientModel {

	private ResourceList deck;
	private MessageList chat;
	private MessageList log;
	private GameMap map;
	private Player[] players;
	private TradeOffer offer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;


	private DevCardList devCardList;
	private int roll;

	/**
	 * Default Constructor
	 *
	 */
	public ClientModel() {
		deck = new ResourceList(ResourceList.max);
		chat = new MessageList();
		log = new MessageList();
		map = new GameMap();
		players = new Player[4];
		turnTracker = new TurnTracker();
		version = -1;
		winner = -1;
		setDevCardList(new DevCardList());
		roll = 0;
	}


	public int getRoll(){
		return roll;
	}

	public void setRoll(int roll){
		this.roll = roll;
	}
	/**
	 * Decrease the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to remove from the bank
	 * @pre The resource must exist and there must be at least `amount` amount of that resource in the bank
	 * @post There will be `amount` less of that resource in the bank
	 * @throws ClientException if this function runs and dies when it shouldn't
	 */
	public void removeResource(String resource, int amount) throws ClientException {
		resource = resource.toLowerCase();
		switch(resource){
			case "brick":
				deck.setBrick(deck.getBrick() - amount);
				if(deck.getBrick() < ResourceList.min) {
					deck.setBrick(ResourceList.min);
				}
				break;
			case "ore":
				deck.setOre(deck.getOre() - amount);
				if(deck.getOre() < ResourceList.min) {
					deck.setOre(ResourceList.min);
				}
				break;
			case "sheep":
				deck.setSheep(deck.getSheep() - amount);
				if(deck.getSheep() < ResourceList.min) {
					deck.setSheep(ResourceList.min);
				}
				break;
			case "wheat":
				deck.setWheat(deck.getWheat() - amount);
				if(deck.getWheat() < ResourceList.min) {
					deck.setWheat(ResourceList.min);
				}
				break;
			case "wood":
				deck.setWood(deck.getWood() - amount);
				if(deck.getWood() < ResourceList.min) {
					deck.setWood(ResourceList.min);
				}
				break;
			default:
				throw new ClientException("Exception thrown in removeResource");
		}
	}

	/**
	 * Increase the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to add to the bank
	 * @pre The resource must exist and there must be at least `amount` less than 19 of that resource in the bank
	 * @post There will be `amount` more of that resource in the bank
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addResource(String resource, int amount) throws ClientException {
		resource = resource.toLowerCase();
		switch(resource){
			case "brick":
				deck.setBrick(deck.getBrick() + amount);
				if(deck.getBrick() > ResourceList.max) {
					deck.setBrick(ResourceList.max);
				}
				break;
			case "ore":
				deck.setOre(deck.getOre() + amount);
				if(deck.getOre() > ResourceList.max) {
					deck.setOre(ResourceList.max);
				}
				break;
			case "sheep":
				deck.setSheep(deck.getSheep() + amount);
				if(deck.getSheep() > ResourceList.max) {
					deck.setSheep(ResourceList.max);
				}
				break;
			case "wheat":
				deck.setWheat(deck.getWheat() + amount);
				if(deck.getWheat() > ResourceList.max) {
					deck.setWheat(ResourceList.max);
				}
				break;
			case "wood":
				deck.setWood(deck.getWood() + amount);
				if(deck.getWood() > ResourceList.max) {
					deck.setWood(ResourceList.max);
				}
				break;
			default:
				throw new ClientException("Exception thrown in addResource");
		}
	}

	/**
	 * Adds a MessageLine to the chat and log
	 * @param line: String, The line to be added to the chat/log
	 * @post The line will be added to both the chat and the log lists
	 * @throws ClientException when this function fails when it shouldn't
	 */
	public void addChatMessage(MessageLine line) throws ClientException {
		try {
			chat.addMessage(line);
			log.addMessage(line);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClientException();
		}
	}

	/**
	 * Switches to the next player; player 0 if currently on player 3
	 * @throws ClientException
	 * @pre None
	 * @post Calls Player.endTurn(), updates the version and the if there is one. Updates the turn tracker with the status. Updates the winner if the player has won.
	 */
	public void endTurn() throws ClientException {
		int currentTurn = turnTracker.getCurrentTurn();
		players[currentTurn].endTurn();
		if(currentTurn + 1 > players.length) {
			turnTracker.setCurrentTurn(0);
		} else {
			turnTracker.setCurrentTurn(currentTurn + 1);
		}
	}

	/**
	 * @return the bank
	 */
	public ResourceList getBank() {
		return deck;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(ResourceList bank) {
		this.deck = bank;
	}

	/**
	 * @return the chat
	 */
	public MessageList getChat() {
		return chat;
	}

	/**
	 * @param chat the chat to set
	 */
	public void setChat(MessageList chat) {
		this.chat = chat;
	}

	/**
	 * @return the log
	 */
	public MessageList getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(MessageList log) {
		this.log = log;
	}

	/**
	 * @return the map
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(GameMap map) {
		this.map = map;
	}

	/**
	 * @return the players
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}

	/**
	 * @return the tradeOffer
	 */
	public TradeOffer getTradeOffer() {
		return offer;
	}

	/**
	 * @param tradeOffer the tradeOffer to set
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.offer = tradeOffer;
	}

	/**
	 * @return the turnTracker
	 */
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	/**
	 * @param turnTracker the turnTracker to set
	 */
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}


	/**
	 * Checks the model to see if the client can send a chat message
	 * @pre None
	 * @post True if client can perform sendChat
	 * @return Whether the action is possible
	 */
	public boolean canSendChat(String message) {
		if(message.length() > 300){
			return false;
		}
		return true;
	}

	/**
	 * Checks the model to see if the client can accept a trade
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canAcceptTrade(int playerIndex ) {
		if(offer.getReceiver() != playerIndex){
			return false;
		}
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();

		List<Integer> offeredResources = new ArrayList<Integer>();
		List<Integer> requestedResources = new ArrayList<Integer>();

		sortOffer(offer, offeredResources, requestedResources);



		int oreRequested = 0;
		int wheatRequested = 0;
		int sheepRequested = 0;
		int brickRequested = 0;
		int woodRequested = 0;

		for(Integer i : requestedResources){
			switch(i){
				case -1:
					woodRequested++;
					break;
				case -2:
					brickRequested++;
					break;
				case -3:
					sheepRequested++;
					break;
				case -4:
					wheatRequested++;
					break;
				case -5:
					oreRequested++;
					break;

			}
		}
		if(resources.getBrick() < brickRequested || resources.getOre() < oreRequested || resources.getSheep() < sheepRequested
				|| resources.getWheat() < wheatRequested || resources.getWood() < woodRequested){
			return false;
		}

		return true;
	}
	/**
	 * Sees if the player has more than seven cards if a 7 is rolled
	 * @pre None
	 * @post True if client can perform discardCards
	 * @return Whether the action is possible
	 */
	public boolean canDiscardCards(int playerIndex ) {
		Player player = players[playerIndex];
		if(player.getResources().getTotal() > 7 && turnTracker.getStatus().equals("Discarding")){
			return true;
		}

		return false;
	}
	/**
	 * Checks the model to see if the client can build a road
	 * @param newLocation of type EdgeValue it is assumed that the vertexLocation is normalized.
	 * @param playerIndex the index of the player playing the road
	 * @pre None
	 * @post True if client can perform buildRoad
	 * @return Whether the action is possible
	 */
	public boolean canBuildRoad(int playerIndex, shared.locations.EdgeLocation newLocation, boolean isFree) {
		if(turnTracker.getStatus().equals("Robbing") || turnTracker.getStatus().equals("Discarding") || turnTracker.getStatus().equals("Rolling")){
			return false;
		}
			Player player = players[playerIndex];
			ResourceList resources = player.getResources();

			//EdgeValue newLocation = edgeValue.getVertexLocation();

			int roads = player.getRoads();
//		if(edgeValue.getOwner() >= 0){//If there is already an owner
//			return false;
//		}
			List<Road> roadList = map.getRoads();

			for(Road road : roadList){
				HexLocation roadhex = road.getLocation().getNormalizedLocation().getHexLoc();
				int x = roadhex.getX();
				int y = roadhex.getY();
				if(x == newLocation.getHexLoc().getX() && y == newLocation.getHexLoc().getY()){
					if(newLocation.getNormalizedLocation().equals(road.getLocation().getNormalizedLocation())){

						System.out.println("Invalid road vertexLocation!");
						return false;
					}
				}
			}


			// TODO: remember to do this for all candos
			boolean firstRounds = false;

			try {
				firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
						ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HexLocation roadHexLoc = newLocation.getNormalizedLocation().getHexLoc();
			EdgeDirection roadDirection = newLocation.getNormalizedLocation().getDirection();
			List<HexLocation> badWaterHexes = new ArrayList<HexLocation>();
			badWaterHexes.add(new HexLocation(-2,-1));
			badWaterHexes.add(new HexLocation(-1,-2));
			badWaterHexes.add(new HexLocation(0,-3));
			badWaterHexes.add(new HexLocation(1,-3));
			badWaterHexes.add(new HexLocation(2,-3));
			badWaterHexes.add(new HexLocation(3,-3));
			badWaterHexes.add(new HexLocation(-2,4));
			badWaterHexes.add(new HexLocation(-1,4));
			badWaterHexes.add(new HexLocation(-1,4));
			badWaterHexes.add(new HexLocation(0,4));
			badWaterHexes.add(new HexLocation(1,3));
			badWaterHexes.add(new HexLocation(2,2));
			badWaterHexes.add(new HexLocation(-3,4));
			badWaterHexes.add(new HexLocation(3,1));

			if(badWaterHexes.contains(roadHexLoc)){
				return false;
			}
			if(true){
//			List<HexLocation> badWaterHexes = new ArrayList<HexLocation>();
//			badWaterHexes.add(new HexLocation(-2,-1));
//			badWaterHexes.add(new HexLocation(-1,-2));
//			badWaterHexes.add(new HexLocation(0,-3));
//			badWaterHexes.add(new HexLocation(1,-3));
//			badWaterHexes.add(new HexLocation(2,-3));
//			badWaterHexes.add(new HexLocation(3,-3));
//			badWaterHexes.add(new HexLocation(-2,4));
//			badWaterHexes.add(new HexLocation(-1,4));
//			badWaterHexes.add(new HexLocation(-1,4));
//			badWaterHexes.add(new HexLocation(0,4));
//			badWaterHexes.add(new HexLocation(1,3));
//			badWaterHexes.add(new HexLocation(2,2));
//			badWaterHexes.add(new HexLocation(-3,4));
//			badWaterHexes.add(new HexLocation(3,1));
//
//			if(badWaterHexes.contains(roadHexLoc)){
//				return false;
//			}

				VertexLocation settlementLoc = new VertexLocation(new HexLocation(-2,3),VertexDirection.East);
				VertexDirection settlementDirection = settlementLoc.getDirection();
				Object seNeighbor = null;
				Object swNeighbor = null;
				switch (roadDirection){
					case NorthWest:


						badWaterHexes.add(new HexLocation(-2,3));
						badWaterHexes.add(new HexLocation(-1,3));
						badWaterHexes.add(new HexLocation(0,3));
//							badWaterHexes.add(new HexLocation(1,2));
//							badWaterHexes.add(new HexLocation(2,1));
						if(badWaterHexes.contains(roadHexLoc)){
							return false;
						}
						int roadY = roadHexLoc.getY();
						boolean isRightY = roadY == -2 || roadY == -1 || roadY == -3;
						if(settlementLoc.getHexLoc().equals(swNeighbor) &&
								settlementDirection.equals(VertexDirection.NorthEast) ){
							return false;
						}else if(settlementLoc.getHexLoc().equals(roadHexLoc) &&
								settlementDirection.equals(VertexDirection.NorthWest)){
							return false;
						}else if((roadHexLoc.getX() < -2  && !isRightY)|| roadHexLoc.getX() > 3 ){
							return false;
						}
						break;
					case North:
						if(settlementLoc.getHexLoc().equals(roadHexLoc) && (settlementDirection.equals(VertexDirection.NorthWest) ||
								settlementDirection.equals(VertexDirection.NorthEast))){
							return false;
						}else if(roadHexLoc.getX() < -2 || roadHexLoc.getX() > 2){
							return false;
						}
						break;
					case NorthEast:
						badWaterHexes.add(new HexLocation(2,1));
//							badWaterHexes.add(new HexLocation(-2,3));
//							badWaterHexes.add(new HexLocation(-1,3));
						badWaterHexes.add(new HexLocation(0,3));
						badWaterHexes.add(new HexLocation(1,2));
						badWaterHexes.add(new HexLocation(2,1));

						if(badWaterHexes.contains(roadHexLoc)){
							return false;
						}
						if(roadHexLoc.getX() < -3){
							return false;
						}
						roadY = roadHexLoc.getY();
						boolean isLeftY = roadY == 2 || roadY == 1 || roadY == 3 || roadY ==4;
						if(settlementLoc.getHexLoc().equals(seNeighbor) &&
								settlementDirection.equals(VertexDirection.NorthWest)){
							return false;
						}else if(settlementLoc.getHexLoc().equals(roadHexLoc) &&
								settlementDirection.equals(VertexDirection.NorthEast)){
							return false;
						}else if((roadHexLoc.getX() < -2 && !isLeftY)  || roadHexLoc.getX() > 2  ){
							return false;
						}
						break;

				}
			}
			if((((resources.getBrick() >= 1 && resources.getWood() >= 1 ) || isFree)
					&& roads > 0 && turnTracker.getCurrentTurn() == playerIndex) || firstRounds) {
				roadList = map.getRoads();
				for(Road r : roadList){
//				EdgeValue tempEdgeValue = r;
					shared.locations.EdgeLocation tempEdgeLocation = r.getLocation().getNormalizedLocation();

					if(tempEdgeLocation.equals(newLocation)){// If a road already has this edgeLocation
						System.out.println("There is a road at this vertexLocation!");
						return false;
					}

				}
				//
				for(VertexObject s : map.getSettlements()){
					roadDirection = newLocation.getNormalizedLocation().getDirection();
					VertexDirection settlementDirection = s.getVertexLocation().getNormalizedLocation().getDirection();
					VertexLocation settlementLoc = s.getVertexLocation().getNormalizedLocation();
					EdgeLocation roadEdgeLoc = newLocation.getNormalizedLocation();

					roadHexLoc = newLocation.getNormalizedLocation().getHexLoc();
//				HexLocation nwNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
//				HexLocation neNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthEast);
//				//				HexLocation nNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.North);
//				//				HexLocation sNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.South);
					HexLocation seNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthEast);
					HexLocation swNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthWest);
					List<Hex> hexList = map.getHexes();

					if(firstRounds){
						badWaterHexes = new ArrayList<HexLocation>();
						badWaterHexes.add(new HexLocation(-2,-1));
						badWaterHexes.add(new HexLocation(-1,-2));
						badWaterHexes.add(new HexLocation(0,-3));
						badWaterHexes.add(new HexLocation(1,-3));
						badWaterHexes.add(new HexLocation(2,-3));
						badWaterHexes.add(new HexLocation(3,-3));
						badWaterHexes.add(new HexLocation(-2,4));
						badWaterHexes.add(new HexLocation(-1,4));
						badWaterHexes.add(new HexLocation(-1,4));
						badWaterHexes.add(new HexLocation(0,4));
						badWaterHexes.add(new HexLocation(1,3));
						badWaterHexes.add(new HexLocation(2,2));
						badWaterHexes.add(new HexLocation(-3,4));
						badWaterHexes.add(new HexLocation(3,1));

						if(badWaterHexes.contains(roadHexLoc)){
							return false;
						}


						switch (roadDirection){
							case NorthWest:


								badWaterHexes.add(new HexLocation(-2,3));
								badWaterHexes.add(new HexLocation(-1,3));
								badWaterHexes.add(new HexLocation(0,3));
//							badWaterHexes.add(new HexLocation(1,2));
//							badWaterHexes.add(new HexLocation(2,1));
								if(badWaterHexes.contains(roadHexLoc)){
									return false;
								}
								int roadY = roadHexLoc.getY();
								boolean isRightY = roadY == -2 || roadY == -1 || roadY == -3;
								if(settlementLoc.getHexLoc().equals(swNeighbor) &&
										settlementDirection.equals(VertexDirection.NorthEast) ){
									return false;
								}else if(settlementLoc.getHexLoc().equals(roadHexLoc) &&
										settlementDirection.equals(VertexDirection.NorthWest)){
									return false;
								}else if((roadHexLoc.getX() < -2  && !isRightY)|| roadHexLoc.getX() > 3 ){
									return false;
								}
								break;
							case North:
								if(settlementLoc.getHexLoc().equals(roadHexLoc) && (settlementDirection.equals(VertexDirection.NorthWest) ||
										settlementDirection.equals(VertexDirection.NorthEast))){
									return false;
								}else if(roadHexLoc.getX() < -2 || roadHexLoc.getX() > 2){
									return false;
								}
								break;
							case NorthEast:
								badWaterHexes.add(new HexLocation(2,1));
//							badWaterHexes.add(new HexLocation(-2,3));
//							badWaterHexes.add(new HexLocation(-1,3));
								badWaterHexes.add(new HexLocation(0,3));
								badWaterHexes.add(new HexLocation(1,2));
								badWaterHexes.add(new HexLocation(2,1));

								if(badWaterHexes.contains(roadHexLoc)){
									return false;
								}
								if(roadHexLoc.getX() < -3){
									return false;
								}
								roadY = roadHexLoc.getY();
								boolean isLeftY = roadY == 2 || roadY == 1 || roadY == 3 || roadY ==4;
								if(settlementLoc.getHexLoc().equals(seNeighbor) &&
										settlementDirection.equals(VertexDirection.NorthWest)){
									return false;
								}else if(settlementLoc.getHexLoc().equals(roadHexLoc) &&
										settlementDirection.equals(VertexDirection.NorthEast)){
									return false;
								}else if((roadHexLoc.getX() < -2 && !isLeftY)  || roadHexLoc.getX() > 2  ){
									return false;
								}
								break;

						}
					}

					switch(roadDirection){
						case NorthWest:
							if((settlementDirection.equals(VertexDirection.NorthWest) || settlementDirection.equals(VertexDirection.West))
									&& s.getOwner() == player.getPlayerIndex()){
								if(s.getVertexLocation().getNormalizedLocation().getHexLoc().equals( newLocation.getHexLoc())){
									return true;
								}						}
							break;
						case North:
							if((settlementDirection.equals(VertexDirection.NorthWest) || settlementDirection.equals(VertexDirection.NorthEast))
									&& s.getOwner() == player.getPlayerIndex()){
								if(s.getVertexLocation().getNormalizedLocation().getHexLoc().equals( newLocation.getHexLoc())){
									return true;
								}						}
							break;
						case NorthEast:
							//HexLocation to the lower right of the hex that the road is considered on after normalizing
							HexLocation tempLoc = new HexLocation(newLocation.getHexLoc().getX()+1,newLocation.getHexLoc().getY());
							if(s.getOwner() == player.getPlayerIndex()
									&& (s.getVertexLocation().getNormalizedLocation().getHexLoc().equals(tempLoc)
									&& settlementDirection.equals(VertexDirection.NorthWest))){
								return true;
							}
							break;


						default:
							System.out.println("Direction Was Wrong!");
							return false;

					}


					//
					//
					//
				}

				for(VertexObject c : map.getCities()){
					roadDirection = newLocation.getNormalizedLocation().getDirection();
					VertexDirection cityDirection = c.getVertexLocation().getNormalizedLocation().getDirection();
					if(c.getVertexLocation().getHexLoc().equals( newLocation.getHexLoc())){

					}
					switch(roadDirection){
						case NorthWest:
							if((cityDirection.equals(VertexDirection.NorthWest) || cityDirection.equals(VertexDirection.West))
									&& c.getOwner() == player.getPlayerIndex()){
								if(c.getVertexLocation().getNormalizedLocation().getHexLoc().equals( newLocation.getHexLoc())){
									return true;
								}						}
							break;
						case North:
							if((cityDirection.equals(VertexDirection.NorthWest) || cityDirection.equals(VertexDirection.NorthEast))
									&& c.getOwner() == player.getPlayerIndex()){
								if(c.getVertexLocation().getNormalizedLocation().getHexLoc().equals( newLocation.getHexLoc())){
									return true;
								}						}
							break;
						case NorthEast:
							//HexLocation to the lower right of the hex that the road is considered on after normalizing
							HexLocation tempLoc = new HexLocation(newLocation.getHexLoc().getX()+1,newLocation.getHexLoc().getY());
							if(c.getOwner() == player.getPlayerIndex()
									&& (c.getVertexLocation().getNormalizedLocation().getHexLoc().equals(tempLoc)
									&& cityDirection.equals(VertexDirection.NorthWest))){
								return true;
							}
							break;


						default:
							System.out.println("Wrong: in relation to City");
							return false;

					}
				}

				for(Road r : roadList){
					roadDirection = newLocation.getNormalizedLocation().getDirection();
					EdgeDirection tempDirection = r.getLocation().getNormalizedLocation().getDirection();

					HexLocation tempHexLoc = r.getLocation().getNormalizedLocation().getHexLoc();
					roadHexLoc = newLocation.getHexLoc();
					HexLocation nwNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
					HexLocation neNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthEast);
					//				HexLocation nNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.North);
					//				HexLocation sNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.South);
					HexLocation seNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthEast);
					HexLocation swNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthWest);




					switch(roadDirection){
						case NorthWest:
							if(r.getOwner() == player.getPlayerIndex()){
								if(tempHexLoc.equals(roadHexLoc) &&  tempDirection.equals(EdgeDirection.North)){
									return true;
								}else if(tempHexLoc.equals(swNeighbor) &&
										(tempDirection.equals(EdgeDirection.North) ||
												tempDirection.equals(EdgeDirection.NorthEast)) ){
									return true;
								}else if(tempHexLoc.equals(nwNeighbor) && tempDirection.equals(EdgeDirection.NorthEast) ){
									return true;
								}

							}

							break;
						case North:
							if(r.getOwner() == player.getPlayerIndex()){
								if(tempHexLoc.equals(roadHexLoc)
										&&  (tempDirection.equals(EdgeDirection.NorthEast)
										|| tempDirection.equals(EdgeDirection.NorthWest))){
									return true;
								}else if(tempHexLoc.equals(nwNeighbor) &&
										tempDirection.equals(EdgeDirection.NorthEast) ){
									return true;
								}else if(tempHexLoc.equals(neNeighbor) && tempDirection.equals(EdgeDirection.NorthWest)){
									return true;
								}
							}
							break;
						case NorthEast:

							if(r.getOwner() == player.getPlayerIndex()){
								if(tempHexLoc.equals(roadHexLoc) &&  tempDirection.equals(EdgeDirection.North)){
									return true;
								}else if(tempHexLoc.equals(seNeighbor) &&
										(tempDirection.equals(EdgeDirection.North) ||
												tempDirection.equals(EdgeDirection.NorthWest)) ){
									return true;
								}else if(tempHexLoc.equals(neNeighbor) && tempDirection.equals(EdgeDirection.NorthWest) ){
									return true;
								}

							}
							break;


						default:
							System.out.println("Wrong: in relation to ROAD");
							return false;

					}
				}


				if(!firstRounds && playerIndex != 0) {
					return false;
				}
			}else{
				System.out.println("Big ELSE returned False!");
				return false;
			}

			return true;


		}

	/**
	 * Checks the model to see if the client can build a settlement
	 * @pre None
	 * @post True if client can perform buildSettlement
	 * @return Whether the action is possible
	 */
	public boolean canBuildSettlement(int playerIndex, VertexLocation vertex, boolean isFree) {
		if(turnTracker.getStatus().equals("Robbing") || turnTracker.getStatus().equals("Discarding") || turnTracker.getStatus().equals("Rolling")){
			return false;
		}
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();


		VertexLocation settLoc = vertex.getNormalizedLocation();
		VertexDirection settDir = settLoc.getDirection();

		boolean firstRounds = false;

		try {
			firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
					ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		if((turnTracker.getCurrentTurn() != playerIndex  ) && !firstRounds){
			return false;

		}

		List<VertexObject> settlementList  = map.getSettlements();

		for(VertexObject settlement : settlementList){
			HexLocation settHex = settlement.getVertexLocation().getNormalizedLocation().getHexLoc();
			int x = settHex.getX();
			int y = settHex.getY();
			if(x == settLoc.getHexLoc().getX() && y == settLoc.getHexLoc().getY()){
				if(settLoc.getDirection().equals(settlement.getVertexLocation().getDirection())){
					return false;
				}
			}


		}

		boolean hasRoadAttached = false;
		boolean twoAway = true;

		HexLocation settHexLoc = settLoc.getNormalizedLocation().getHexLoc();
		HexLocation nwNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
		HexLocation neNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthEast);
		HexLocation sNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.South);
		HexLocation seNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.SouthEast);
		HexLocation swNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.SouthWest);


		if(player.getSettlements() > 0 	&& ((resources.getWheat() >= 1 && resources.getSheep() >= 1
				&& resources.getBrick() >= 1 && resources.getWood() >= 1) || isFree )){

			for(Road r : map.getRoads()){

				if(r.getOwner() == player.getPlayerIndex()){
					EdgeLocation tempLocation = r.getLocation().getNormalizedLocation();
					HexLocation tempHexLoc = tempLocation.getHexLoc();
					EdgeDirection tempDir = tempLocation.getDirection();


					switch(settDir){

						case West:
							if(tempHexLoc.equals(settHexLoc) && tempDir.equals(EdgeDirection.NorthWest)){
								hasRoadAttached = true;
							}else if(tempHexLoc.equals(swNeighbor) &&
									(tempDir.equals(EdgeDirection.North)
											|| tempDir.equals(EdgeDirection.NorthEast))){
								hasRoadAttached = true;
							}

							break;
						case NorthWest:
							if(tempHexLoc.equals(settHexLoc) &&
									(tempDir.equals(EdgeDirection.NorthWest)
											|| tempDir.equals(EdgeDirection.North))){
								hasRoadAttached = true;
							}else if(tempHexLoc.equals(nwNeighbor) &&
									(tempDir.equals(EdgeDirection.NorthEast))){
								hasRoadAttached = true;
							}

							break;
						case NorthEast:

							if(tempHexLoc.equals(settHexLoc) &&
									(tempDir.equals(EdgeDirection.NorthEast)
											|| tempDir.equals(EdgeDirection.North))){
								hasRoadAttached = true;
							}else if(tempHexLoc.equals(neNeighbor) &&
									(tempDir.equals(EdgeDirection.NorthWest))){
								hasRoadAttached = true;
							}

							break;


						default:


					}
				}

			}

			for(VertexObject s : map.getSettlements()){

				HexLocation tempHexLoc = s.getVertexLocation().getNormalizedLocation().getHexLoc();
				VertexDirection tempDir = s.getVertexLocation().getNormalizedLocation().getDirection();

				switch(settDir){

					case West:
						if(tempHexLoc.equals(settHexLoc) && tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(swNeighbor) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(sNeighbor) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}

						break;
					case NorthWest:
						if(tempHexLoc.equals(settHexLoc) &&
								(tempDir.equals(VertexDirection.NorthEast))){
							twoAway = false;
						}else if(tempHexLoc.equals(nwNeighbor) &&
								tempDir.equals(VertexDirection.NorthEast)){
							twoAway = false;
						}else if ((tempDir.equals(VertexDirection.NorthEast) && tempHexLoc.equals(swNeighbor))){
							return false;
						}

						break;
					case NorthEast:

						if(tempHexLoc.equals(settHexLoc) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(seNeighbor) &&
								(tempDir.equals(VertexDirection.NorthWest))){
							twoAway = false;
						}else if(tempHexLoc.equals(neNeighbor) &&
								(tempDir.equals(VertexDirection.NorthWest))){
							twoAway = false;
						}


						break;


					default:


				}
			}

			for(VertexObject c : map.getCities()){

				HexLocation tempHexLoc = c.getVertexLocation().getHexLoc();
				VertexDirection tempDir = c.getVertexLocation().getDirection();

				switch(settDir){

					case West:
						if(tempHexLoc.equals(settHexLoc) && tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(swNeighbor) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(sNeighbor) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}

						break;
					case NorthWest:
						if(tempHexLoc.equals(settHexLoc) &&
								(tempDir.equals(VertexDirection.West)
										|| tempDir.equals(VertexDirection.NorthEast))){
							twoAway = false;
						}else if(tempHexLoc.equals(nwNeighbor) &&
								tempDir.equals(VertexDirection.NorthEast)){
							twoAway = false;
						}

						break;
					case NorthEast:

						if(tempHexLoc.equals(settHexLoc) &&
								tempDir.equals(VertexDirection.NorthWest)){
							twoAway = false;
						}else if(tempHexLoc.equals(seNeighbor) &&
								(tempDir.equals(VertexDirection.NorthWest))){
							twoAway = false;
						}else if(tempHexLoc.equals(neNeighbor) &&
								(tempDir.equals(VertexDirection.NorthWest))){
							twoAway = false;
						}


						break;


					default:


				}
			}



		}


		return twoAway && hasRoadAttached;
	}
	/**
	 * Checks the model to see if the client can build a city
	 * @pre None
	 * @post True if client can perform buildCity
	 * @return Whether the action is possible
	 */
	public boolean canBuildCity(int playerIndex, VertexLocation cityLoc) {
		if(!turnTracker.getStatus().equals("Playing")){
			return false;
		}
		Player player = players[playerIndex];
		System.out.println(""+(player==null));
		ResourceList resources = player.getResources();



		if(turnTracker.getCurrentTurn() != playerIndex ){

			return false;

		}


		if(player.getCities() > 0 && resources.getOre() >= 3 && resources.getWheat() >= 2){

			for(VertexObject s : map.getSettlements()){


				//If there is a settlement at the vertexLocation and the player is the owner of the settlement
				if(s.getVertexLocation().getNormalizedLocation().equals(cityLoc) && s.getOwner() == player.getPlayerIndex()){
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * Checks the model to see if the client can offer a trade
	 * @pre None
	 * @post True if client can perform offerTrade
	 * @return Whether the action is possible
	 */
	public boolean canOfferTrade(int playerIndex) {
		if(!turnTracker.getStatus().equals("Playing")){
			return false;
		}
		Player player = players[playerIndex];

		ResourceList resources = player.getResources();
		if(turnTracker.getCurrentTurn() != playerIndex ||
				offer.getSender() != playerIndex || offer.getReceiver() >3 || offer.getReceiver() < 0){
			return false;
		}

		List<Integer> offeredResources = new ArrayList<Integer>();
		List<Integer> requestedResources = new ArrayList<Integer>();

		sortOffer(offer, offeredResources, requestedResources);

		int oreOffered = 0;
		int wheatOffered = 0;
		int sheepOffered = 0;
		int brickOffered = 0;
		int woodOffered = 0;
		for(Integer i : offeredResources){
			switch(i){
				case 1:
					woodOffered++;
					break;
				case 2:
					brickOffered++;
					break;
				case 3:
					sheepOffered++;
					break;
				case 4:
					wheatOffered++;
					break;
				case 5:
					oreOffered++;
					break;

			}
		}

		//See if the player has the required resources
		if(resources.getBrick() < brickOffered || resources.getOre() < oreOffered || resources.getSheep() < sheepOffered
				|| resources.getWheat() < wheatOffered || resources.getWood() < woodOffered){
			return false;
		}
		return true;
	}

	public void sortOffer(TradeOffer offer, List<Integer> offeredResources, List<Integer> requestedResources){
		for(Integer i : offer.getOffer()){// Sort resources offered and those being requested from the offer
			if(i > 0){
				offeredResources.add(i);
			}else{
				requestedResources.add(i);
			}
		}
	}

	/**
	 * Checks if the player can do a 4 to 1 ratio maritime trade
	 * @param playerIndex The index of the localPlayer
	 * @param resource The resource type to check
     * @return whether the player is allowed to perform a maritime trade for that option
     */
	public boolean canFourToOneMaritime(int playerIndex, ResourceType resource) {
		if(!turnTracker.getStatus().equals("Playing")){
			return false;
		}
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();

		switch (resource){

			case WOOD:
				if(resources.getWood() > 3){
					return true;
				}
				break;
			case BRICK:
				if(resources.getBrick() > 3){
					return true;
				}
				break;
			case SHEEP:
				if(resources.getSheep() > 3){
					return true;
				}
				break;
			case WHEAT:
				if(resources.getWheat() > 3){
					return true;
				}
				break;
			case ORE:
				if(resources.getOre() > 3){
					return true;
				}
				break;
			default: return false;
		}
		return false;
	}

	/**
	 * Checks the model to see if the client can perform a maritime trade
	 * @pre None
	 * @post True if client can perform maritimeTrade
	 * @return Whether the action is possible
	 */
	public boolean canMaritimeTrade(int playerIndex, ResourceType resource) {
		if(!turnTracker.getStatus().equals("Playing")){
			return false;
		}
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();
		boolean access = false;
		boolean hasResources = false;

		switch (resource){

			case WOOD:
				if(resources.getWood() > 3){
					return true;
				}
			case BRICK:
				if(resources.getBrick() > 3){
					return true;
				}
			case SHEEP:
				if(resources.getSheep() > 3){
					return true;
				}
			case WHEAT:
				if(resources.getWheat() > 3){
					return true;
				}
			case ORE:
				if(resources.getOre() > 3){
					return true;
				}
		}


		List<PortType> portsWithAccess = new ArrayList<>();
		
		if(turnTracker.getCurrentTurn() != playerIndex){
			return false;
		}

		for(Port p : map.getPorts()){
			HexLocation portHex = p.getLocation();
			PortType portType = p.getResource();
			EdgeDirection portDir = p.getDirection();


			HexLocation seNeighbor = portHex.getNeighborLoc(EdgeDirection.SouthEast);

			for(VertexObject s : map.getSettlements()){				
				VertexLocation settLoc = s.getVertexLocation().getNormalizedLocation();

				HexLocation settHex = settLoc.getHexLoc();
				VertexDirection settDir = settLoc.getDirection();

				if(s.getOwner()== player.getPlayerIndex() ){
					switch(portDir){
						case North:
							if(settHex.equals(portHex) && (settDir.equals(VertexDirection.NorthEast)
									|| settDir.equals(VertexDirection.NorthEast))){
								portsWithAccess.add(portType);
								access = true;
							}
							break;
						case NorthEast:
							if(settHex.equals(portHex) && (settDir.equals(VertexDirection.NorthEast)
									|| settDir.equals(VertexDirection.NorthEast))){
								portsWithAccess.add(portType);

								access = true;
							}else if(settHex.equals(seNeighbor) && settDir.equals(VertexDirection.NorthWest)){
								portsWithAccess.add(portType);

								access = true;
							}
							break;
						case NorthWest:
							if(settHex.equals(portHex) && (settDir.equals(VertexDirection.West)
									|| settDir.equals(VertexDirection.NorthWest))){
								portsWithAccess.add(portType);

								access = true;
							}
							break;
						default:
							break;

					}

				}
			}
			for(VertexObject c : map.getCities()){				
				VertexLocation cityLoc = c.getVertexLocation().getNormalizedLocation();

				HexLocation citHyex = cityLoc.getHexLoc();
				VertexDirection cityDir = cityLoc.getDirection();

				if(c.getOwner()== player.getPlayerIndex() ){
					switch(portDir){
						case North:
							if(citHyex.equals(portHex) && (cityDir.equals(VertexDirection.NorthEast)
									|| cityDir.equals(VertexDirection.NorthEast))){
								portsWithAccess.add(portType);
								access = true;
							}
							break;
						case NorthEast:
							if(citHyex.equals(portHex) && (cityDir.equals(VertexDirection.NorthEast)
									|| cityDir.equals(VertexDirection.NorthEast))){
								portsWithAccess.add(portType);

								access = true;
							}else if(citHyex.equals(seNeighbor) && cityDir.equals(VertexDirection.NorthWest)){
								portsWithAccess.add(portType);

								access = true;
							}
							break;
						case NorthWest:
							if(citHyex.equals(portHex) && (cityDir.equals(VertexDirection.West)
									|| cityDir.equals(VertexDirection.NorthWest))){
								portsWithAccess.add(portType);

								access = true;
							}
							break;
						default:
							break;

					}

				}
			}


		}


		for(PortType type : portsWithAccess ){
			if (type == null) {
				// the three for one ports are null
				type = PortType.THREE;
			}

			if(type.equals(PortType.BRICK) && resource.equals(ResourceType.BRICK)){
				if(resources.getBrick() > 1){
					hasResources = true;
				}
			}else if(type.equals(PortType.WOOD) &&  resource.equals(ResourceType.WOOD)){
				if(resources.getWood() > 1){
					hasResources = true;
				}
			}else if(type.equals(PortType.ORE) && resource.equals(ResourceType.ORE)){
				if(resources.getOre() > 1){
					hasResources = true;
				}
			}else if(type.equals(PortType.WHEAT)&& resource.equals(ResourceType.WHEAT)){
				if(resources.getWheat() > 1){
					hasResources = true;
				}
			}else if(type.equals(PortType.SHEEP)){
				if(resources.getSheep() > 1){
					hasResources = true;
				}
			}else if(type.equals(PortType.THREE)){
				if(resources.getWood() > 2 ||resources.getWheat() > 2 ||resources.getBrick() > 2
						||resources.getOre() > 2 ||resources.getSheep() > 2 ){
					hasResources = true;
				}
			}
		}



		return access && hasResources;
	}
	/**
	 * Checks the model to see if the client can rob a player
	 * @pre None
	 * @post True if client can perform robPlayer
	 * @return Whether the action is possible
	 */
	public boolean canRobPlayer(int playerIndex) {
		if(!turnTracker.getStatus().equals("Robbing")){
			return false;
		}
		HexLocation robber = map.getRobber();
		Player player = players[playerIndex];
		 HexLocation robberLocation = robber;

		HexLocation seNeighbor = robberLocation.getNeighborLoc(EdgeDirection.SouthEast);
		HexLocation swNeighbor = robberLocation.getNeighborLoc(EdgeDirection.SouthWest);
		HexLocation sNeighbor = robberLocation.getNeighborLoc(EdgeDirection.South);

		for(VertexObject s : map.getSettlements()){

			VertexLocation settLoc = s.getVertexLocation().getNormalizedLocation();
			HexLocation settHex = settLoc.getHexLoc();
			VertexDirection settDir = settLoc.getDirection();
			if(s.getOwner() == player.getPlayerIndex()){
				if(settHex.equals(robberLocation) && (settDir.equals(VertexDirection.NorthWest)
						|| settDir.equals(VertexDirection.NorthEast) )){

					return true;
				}else if(settHex.equals(seNeighbor) && (settDir.equals(VertexDirection.NorthWest))){
					return true;

				} else if(settHex.equals(swNeighbor) && settDir.equals(VertexDirection.NorthEast)){
					return true;

				} else if(settHex.equals(sNeighbor) && (settDir.equals(VertexDirection.NorthEast)
						|| settDir.equals(VertexDirection.NorthWest))){
					return true;
	
				}
			}



		}

		for(VertexObject c : map.getCities()){

			VertexLocation cityLoc = c.getVertexLocation();
			HexLocation cityHex = cityLoc.getHexLoc();
			VertexDirection cityDir = cityLoc.getDirection();
			if(c.getOwner() == player.getPlayerIndex()){
				if(cityHex.equals(robberLocation) && (cityDir.equals(VertexDirection.West)
						||cityDir.equals(VertexDirection.NorthWest)
						|| cityDir.equals(VertexDirection.NorthEast) )){

					return true;
				}else if(cityHex.equals(seNeighbor) && (cityDir.equals(VertexDirection.West )
						|| cityDir.equals(VertexDirection.NorthWest))){
					return true;

				} else if(cityHex.equals(swNeighbor) && cityDir.equals(VertexDirection.NorthWest)){
					return true;

				}
			}



		}

		return false;
	}
	/**
	 * Checks the model to see if the client can finish their turn
	 * @pre None
	 * @post True if client can perform finishTurn
	 * @return Whether the action is possible
	 */
	public boolean canFinishTurn(int playerIndex) {
		boolean firstRounds = false;

		try {
			firstRounds = ClientFacade.getSingleton().getContext().getState() instanceof FirstRoundState ||
					ClientFacade.getSingleton().getContext().getState() instanceof SecondRoundState;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(turnTracker.getCurrentTurn() == playerIndex && (firstRounds || ClientFacade.getSingleton().getContext().getState() instanceof PlayingState)){
				return true;
			} else {
				return false;
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Checks the model to see if the client can play a soldier
	 * @pre None
	 * @post True if client can perform soldier
	 * @return Whether the action is possible
	 */
	public boolean canSoldier(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex && !player.getPlayedDevCard()
				&& player.getOldDevCards().getSoldier() > 0 && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")){
			return true;
		}
		return false;
	}
	/**
	 * Checks the model to see if the client can play a year of plenty card
	 * @pre None
	 * @post True if client can perform yearOfPlenty
	 * @return Whether the action is possible
	 */
	public boolean canYearOfPlenty(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex  && !player.getPlayedDevCard()
				&& player.getOldDevCards().getYearOfPlenty() > 0 && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")){
			return true;
		}
		return false;
	}
	/**
	 * Checks the model to see if the client can play a road building card
	 * @pre None
	 * @post True if client can perform roadBuilding
	 * @return Whether the action is possible
	 */
	public boolean canRoadBuilding(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex  && !player.getPlayedDevCard()
				&& player.getOldDevCards().getRoadBuilding() > 0 && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")){
			return true;
		}
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monument card
	 * @pre None
	 * @post True if client can perform monument
	 * @return Whether the action is possible
	 */
	public boolean canMonument(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex && !player.getPlayedDevCard()
				&& player.getOldDevCards().getMonument() > 0 && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")) {
			return true;
		}
		return false;
	}
	/**
	 * Checks the model to see if the client can play a monopoly card
	 * @pre None
	 * @post True if client can perform monopoly
	 * @return Whether the action is possible
	 */
	public boolean canMonopoly(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex &&  !player.getPlayedDevCard()
				&& player.getOldDevCards().getMonopoly() > 0 && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")){
			return true;
		}
		return false;
	}



	/**
	 * @param playerIndex (int)
	 * @return true if the player can roll a number, false otherwise
	 * @pre none
	 * @post true if the player can roll a number, false otherwise
	 */
	public boolean canRollNumber(int playerIndex){
		if(turnTracker.getCurrentTurn() == playerIndex && !turnTracker.getStatus().equals("FirstRound") && !turnTracker.getStatus().equals("SecondRound")){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * @param playerIndex (int)
	 * @return true if a player can buy a dev card, false otherwise
	 * @pre none
	 * @post true if a player can play a dev card, false otherwise
	 */
	public boolean canBuyDevCard(int playerIndex) {
		if(!turnTracker.getStatus().equals("Playing")){
			return false;
		}
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();

		if(resources.getOre() > 0 && resources.getSheep() > 0 && resources.getWheat() > 0
				&& devCardList.getTotal() > 0){
			return true;
		}else{
			return false;
		}

	}

	public DevCardList getDevCardList() {
		return devCardList;
	}

	public void setDevCardList(DevCardList devCardList) {
		this.devCardList = devCardList;
	}
	
	public boolean canPlaceRobber(HexLocation hexLoc){
		if(!turnTracker.getStatus().equals("Robbing")){
			return false;
		}
		try {
			HexLocation robber = ClientFacade.getSingleton().getClientModel().getMap().getRobber();
			if(robber != null && (robber.getX() != hexLoc.getX() || robber.getY() != hexLoc.getY())) {
				for(Hex hex : ClientFacade.getSingleton().getClientModel().getMap().getHexes()){
					HexLocation currentLocation = hex.getLocation();
					if(currentLocation.getX() == hexLoc.getX() && currentLocation.getY() == hexLoc.getY()) {
						return true;
					}
				}
			}
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


}
