package model;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.DevCardType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.locations.*;

public class ClientModel {

	private ResourceList bank;
	private MessageList chat;
	private MessageList log;
	private GameMap map;
	private Player[] players;
	private TradeOffer tradeOffer;
	private TurnTracker turnTracker;
	private int version;
	private int winner;

	/**
	 * Default Constructor
	 * 
	 */
	public ClientModel() {
		bank = new ResourceList(ResourceList.max);
		chat = new MessageList();
		log = new MessageList();
		map = new GameMap();
		players = new Player[4];
		turnTracker = new TurnTracker();
		version = 0;
		winner = -1;
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
			bank.setBrick(bank.getBrick() - amount);
			if(bank.getBrick() < ResourceList.min) {
				bank.setBrick(ResourceList.min);
			}
			break;
		case "ore":
			bank.setOre(bank.getOre() - amount);
			if(bank.getOre() < ResourceList.min) {
				bank.setOre(ResourceList.min);
			}
			break;
		case "sheep":
			bank.setSheep(bank.getSheep() - amount);
			if(bank.getSheep() < ResourceList.min) {
				bank.setSheep(ResourceList.min);
			}
			break;
		case "wheat":
			bank.setWheat(bank.getWheat() - amount);
			if(bank.getWheat() < ResourceList.min) {
				bank.setWheat(ResourceList.min);
			}
			break;
		case "wood":
			bank.setWood(bank.getWood() - amount);
			if(bank.getWood() < ResourceList.min) {
				bank.setWood(ResourceList.min);
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
			bank.setBrick(bank.getBrick() + amount);
			if(bank.getBrick() > ResourceList.max) {
				bank.setBrick(ResourceList.max);
			}
			break;
		case "ore":
			bank.setOre(bank.getOre() + amount);
			if(bank.getOre() > ResourceList.max) {
				bank.setOre(ResourceList.max);
			}
			break;
		case "sheep":
			bank.setSheep(bank.getSheep() + amount);
			if(bank.getSheep() > ResourceList.max) {
				bank.setSheep(ResourceList.max);
			}
			break;
		case "wheat":
			bank.setWheat(bank.getWheat() + amount);
			if(bank.getWheat() > ResourceList.max) {
				bank.setWheat(ResourceList.max);
			}
			break;
		case "wood":
			bank.setWood(bank.getWood() + amount);
			if(bank.getWood() > ResourceList.max) {
				bank.setWood(ResourceList.max);
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
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(ResourceList bank) {
		this.bank = bank;
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
		return tradeOffer;
	}

	/**
	 * @param tradeOffer the tradeOffer to set
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
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
	public boolean canSendChat(MessageLine message) {
		
		
		return false;
	}	
	/**
	 * Checks the model to see if the client can accept a trade
	 * @pre None
	 * @post True if client can perform acceptTrade
	 * @return Whether the action is possible
	 */
	public boolean canAcceptTrade(int playerIndex, TradeOffer offer ) {
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
			 case 0:
				 woodRequested++;
				 break;
			 case 1:
				 brickRequested++;
				 break;
			 case 2:
				 sheepRequested++;
				 break;
			 case 3:
				 wheatRequested++;
				 break;
			 case 4:
				 oreRequested++;
				 break;
	
			}
		}
		if(resources.getBrick() < brickRequested || resources.getOre() < oreRequested || resources.getSheep() < sheepRequested 
				|| resources.getWheat() < wheatRequested || resources.getWood() < woodRequested){
			return false;
		}
		
		return false;
	}
	/**
	 * Checks the model to see if the client can discard cards
	 * @pre None
	 * @post True if client can perform discardCards
	 * @return Whether the action is possible
	 */
	public boolean canDiscardCards() {
		//TO-DO
		return false;
	}
	/**
	 * Checks the model to see if the client can build a road 
	 * @param location of type EdgeValue it is assumed that the location is normalized.
	 * @param playerIndex the index of the player playing the road
	 * @pre None
	 * @post True if client can perform buildRoad
	 * @return Whether the action is possible
	 */
	public boolean canBuildRoad(int playerIndex, EdgeValue edgeValue) {
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();		

		EdgeLocation newLocation = edgeValue.getLocation();

		int roads = player.getRoads();
		if(edgeValue.getOwner() >= 0){//If there is already an owner
			return false;
		}


		if(resources.getBrick() >= 1 && resources.getWood() >= 1
				&& roads > 0 && turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled()){
			List<Road> roadList = map.getRoadList();
			for(Road r : roadList){
				EdgeValue tempEdgeValue = r.getLocation();
				EdgeLocation tempEdgeLocation = tempEdgeValue.getLocation();

				if(tempEdgeLocation.equals(newLocation)){// If a road already has this edgeLocation
					return false;
				}

			}
			//			
			for(Settlement s : map.getSettlementList()){
				EdgeDirection roadDirection = newLocation.getDir();
				VertexDirection settlementDirection = s.getLocation().getLocation().getDir();

				switch(roadDirection){
				case NorthWest:
					if((settlementDirection.equals(VertexDirection.NorthWest) || settlementDirection.equals(VertexDirection.West))
							&& s.getPlayerId() == player.getPlayerID()){
						if(s.getLocation().getLocation().getHexLoc().equals( newLocation.getHexLoc())){
							return true;
						}						}
					break;
				case North:
					if((settlementDirection.equals(VertexDirection.NorthWest) || settlementDirection.equals(VertexDirection.NorthEast))
							&& s.getPlayerId() == player.getPlayerID()){
						if(s.getLocation().getLocation().getHexLoc().equals( newLocation.getHexLoc())){
							return true;
						}						}
					break;
				case NorthEast:
					//HexLocation to the lower right of the hex that the road is considered on after normalizing
					HexLocation tempLoc = new HexLocation(newLocation.getHexLoc().getX()+1,newLocation.getHexLoc().getY());
					if(s.getPlayerId() == player.getPlayerID()
							&& (s.getLocation().getLocation().getHexLoc().equals(tempLoc)
									&& settlementDirection.equals(VertexDirection.NorthWest))){
						return true;
					}
					break;


				default:
					return false;

				}


				//				
				//				
				//				
			}

			for(City c : map.getCityList()){
				EdgeDirection roadDirection = newLocation.getDir();
				VertexDirection cityDirection = c.getLocation().getLocation().getDir();
				if(c.getLocation().getLocation().getHexLoc().equals( newLocation.getHexLoc())){

				}
				switch(roadDirection){
				case NorthWest:
					if((cityDirection.equals(VertexDirection.NorthWest) || cityDirection.equals(VertexDirection.West))
							&& c.getPlayerId() == player.getPlayerID()){
						if(c.getLocation().getLocation().getHexLoc().equals( newLocation.getHexLoc())){
							return true;
						}						}
					break;
				case North:
					if((cityDirection.equals(VertexDirection.NorthWest) || cityDirection.equals(VertexDirection.NorthEast))
							&& c.getPlayerId() == player.getPlayerID()){
						if(c.getLocation().getLocation().getHexLoc().equals( newLocation.getHexLoc())){
							return true;
						}						}
					break;
				case NorthEast:
					//HexLocation to the lower right of the hex that the road is considered on after normalizing
					HexLocation tempLoc = new HexLocation(newLocation.getHexLoc().getX()+1,newLocation.getHexLoc().getY());
					if(c.getPlayerId() == player.getPlayerID()
							&& (c.getLocation().getLocation().getHexLoc().equals(tempLoc)
									&& cityDirection.equals(VertexDirection.NorthWest))){
						return true;
					}
					break;


				default:
					return false;

				}
			}

			for(Road r : roadList){
				EdgeDirection roadDirection = newLocation.getDir();
				EdgeDirection tempDirection = r.getLocation().getLocation().getDir();

				HexLocation tempHexLoc = r.getLocation().getLocation().getHexLoc();
				HexLocation roadHexLoc = newLocation.getHexLoc();
				HexLocation nwNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
				HexLocation neNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.NorthEast);
				//				HexLocation nNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.North);
				//				HexLocation sNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.South);
				HexLocation seNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthEast);
				HexLocation swNeighbor = roadHexLoc.getNeighborLoc(EdgeDirection.SouthWest);





				switch(roadDirection){
				case NorthWest:
					if(r.getPlayerId() == player.getPlayerID()){
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
					if(r.getPlayerId() == player.getPlayerID()){
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

					if(r.getPlayerId() == player.getPlayerID()){
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
					return false;

				}
			}


			return true;
		}else{
			return false;
		}


	}
	/**
	 * Checks the model to see if the client can build a settlement
	 * @pre None
	 * @post True if client can perform buildSettlement
	 * @return Whether the action is possible
	 */
	public boolean canBuildSettlement(int playerIndex, VertexObject vertex) {
		
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();		


		VertexLocation settLoc = vertex.getLocation();
		VertexDirection settDir = settLoc.getDir();

		if(turnTracker.getCurrentTurn() != playerIndex || vertex.getOwner() >=0 || !player.getHasRolled()){
			return false;

		}
		
		boolean hasRoadAttached = false;
		boolean twoAway = true;

		HexLocation settHexLoc = settLoc.getHexLoc();
		HexLocation nwNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthWest);
		HexLocation neNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.NorthEast);
		HexLocation sNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.South);
		HexLocation seNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.SouthEast);
		HexLocation swNeighbor = settHexLoc.getNeighborLoc(EdgeDirection.SouthWest);

		if(player.getSettlements() > 0 	&& resources.getWheat() >= 1 && resources.getSheep() >= 1
				&& resources.getBrick() >= 1 && resources.getWood() >= 1 ){

			for(Road r : map.getRoadList()){

				if(r.getPlayerId() == player.getPlayerID()){
					HexLocation tempHexLoc = r.getLocation().getLocation().getHexLoc();
					EdgeDirection tempDir = r.getLocation().getLocation().getDir();

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
			
			for(Settlement s : map.getSettlementList()){

					HexLocation tempHexLoc = s.getLocation().getLocation().getHexLoc();
					VertexDirection tempDir = s.getLocation().getLocation().getDir();

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
									tempDir.equals(VertexDirection.NorthWest)){
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
			
			for(City c : map.getCityList()){

				HexLocation tempHexLoc = c.getLocation().getLocation().getHexLoc();
				VertexDirection tempDir = c.getLocation().getLocation().getDir();

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
								tempDir.equals(VertexDirection.NorthWest)){
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
	public boolean canBuildCity(int playerIndex, VertexObject vertex) {
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();		


		VertexLocation cityLoc = vertex.getLocation();

		if(turnTracker.getCurrentTurn() != playerIndex || vertex.getOwner() == player.getPlayerIndex() || !player.getHasRolled()){
			return false;

		}
		

		if(player.getCities() > 0 && resources.getOre() >= 3 && resources.getWheat() >= 2){
		
			for(Settlement s : map.getSettlementList()){

				//If there is a settlement at the vertexLocation and the player is the owner of the settlement
				if(s.getLocation().getLocation().equals(vertex.getLocation()) && s.getPlayerId() == player.getPlayerID()){
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
	public boolean canOfferTrade(int playerIndex, TradeOffer offer) {
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();
		if(turnTracker.getCurrentTurn() != playerIndex || !player.getHasRolled()){
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
			 case 0:
				 woodOffered++;
				 break;
			 case 1:
				 brickOffered++;
				 break;
			 case 2:
				 sheepOffered++;
				 break;
			 case 3:
				 wheatOffered++;
				 break;
			 case 4:
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
	 * Checks the model to see if the client can perform a maritime trade
	 * @pre None
	 * @post True if client can perform maritimeTrade
	 * @return Whether the action is possible
	 */
	public boolean canMaritimeTrade(int playerIndex, ResourceType resource) {
		Player player = players[playerIndex];
		ResourceList resources = player.getResources();
		boolean access = false;
		boolean hasResources = false;
		
		List<PortType> portsWithAccess = new ArrayList<>();
		
		if(turnTracker.getCurrentTurn() != playerIndex || !player.getHasRolled()){
			return false;
		}
		
		for(Port p : map.getPortList()){
			HexLocation portHex = p.getLocation().getHexLoc();
			PortType portType = p.getResourceType();
			EdgeDirection portDir = p.getDirection();
			
		
			HexLocation seNeighbor = portHex.getNeighborLoc(EdgeDirection.SouthEast);
			
			for(Settlement s : map.getSettlementList()){
				
				VertexLocation settLoc = s.getLocation().getLocation();
				HexLocation settHex = settLoc.getHexLoc();
				VertexDirection settDir = settLoc.getDir();
				
				if(s.getPlayerId()== player.getPlayerID() ){
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
			for(City c : map.getCityList()){
				
				VertexLocation cityLoc = c.getLocation().getLocation();
				HexLocation citHyex = cityLoc.getHexLoc();
				VertexDirection cityDir = cityLoc.getDir();
				
				if(c.getPlayerId()== player.getPlayerID() ){
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
			
					}
					
				}
			}
			
			
		}
		
		
		for(PortType type : portsWithAccess ){
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
	public boolean canRobPlayer(int playerIndex, Robber robber) {
		
		Player player = players[playerIndex];
		HexLocation robberLocation = robber.getLocation();
		
		HexLocation seNeighbor = robberLocation.getNeighborLoc(EdgeDirection.SouthEast);
		HexLocation swNeighbor = robberLocation.getNeighborLoc(EdgeDirection.SouthWest);

		for(Settlement s : map.getSettlementList()){
			
			VertexLocation settLoc = s.getLocation().getLocation();
			HexLocation settHex = settLoc.getHexLoc();
			VertexDirection settDir = settLoc.getDir();
			if(s.getPlayerId() == player.getPlayerID()){
				if(settHex.equals(robberLocation) && (settDir.equals(VertexDirection.West) 
						||settDir.equals(VertexDirection.NorthWest) 
						|| settDir.equals(VertexDirection.NorthEast) )){
					
					return true;
				}else if(settHex.equals(seNeighbor) && (settDir.equals(VertexDirection.West )
						|| settDir.equals(VertexDirection.NorthWest))){
					return true;
					
				} else if(settHex.equals(swNeighbor) && settDir.equals(VertexDirection.NorthWest)){
					return true;
					
				} 
			}
			
			
			
		}
		
	for(City c : map.getCityList()){
				
				VertexLocation cityLoc = c.getLocation().getLocation();
				HexLocation cityHex = cityLoc.getHexLoc();
				VertexDirection cityDir = cityLoc.getDir();
				if(c.getPlayerId() == player.getPlayerID()){
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
		Player player = players[playerIndex];
		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Checks the model to see if the client can play a soldier
	 * @pre None
	 * @post True if client can perform soldier
	 * @return Whether the action is possible
	 */
	public boolean canSoldier(int playerIndex) {
		Player player = players[playerIndex];

		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled() && !player.getPlayedDevCard() 
				&& player.hasDevCard(DevCardType.SOLDIER)){
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

		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled() && !player.getPlayedDevCard() 
				&& player.hasDevCard(DevCardType.YEAR_OF_PLENTY)){
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

		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled() && !player.getPlayedDevCard() 
				&& player.hasDevCard(DevCardType.ROAD_BUILD)){
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

		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled() && !player.getPlayedDevCard() 
				&& player.hasDevCard(DevCardType.MONUMENT)){
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

		if(turnTracker.getCurrentTurn() == playerIndex && player.getHasRolled() && !player.getPlayedDevCard() 
				&& player.hasDevCard(DevCardType.MONOPOLY)){
			return true;
		}
		return false;
	}
	
	public boolean canRollNumber(int playerIndex){
		
		Player player = players[playerIndex];
		
		if(turnTracker.getCurrentTurn() == playerIndex && !player.getHasRolled()){
			return true;
		}else{
			return false;
		}
		
	}
	
	

}
