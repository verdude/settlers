package server.model;

import model.ClientException;
import model.ClientModel;
import model.DevCardList;
import model.ResourceList;

/**
 * Created by Sean_George on 3/15/16.
 */
public class ServerModel {

    private ResourceList deck;
    private ClientModel clientModel;
    private DevCardList devCards;


    public ServerModel(){
        this.deck = new ResourceList(ResourceList.max);
        this.devCards = new DevCardList();
        this.clientModel = new ClientModel();
		this.clientModel.setVersion(0);
		this.clientModel.getTurnTracker().setStatus("FirstRound");
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

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
    }

    public ResourceList getDeck() {
        return deck;
    }

    public void setDeck(ResourceList deck) {
        this.deck = deck;
    }
    public DevCardList getDevCards() {
        return devCards;
    }

    public void setDevCards(DevCardList devCards) {
        this.devCards = devCards;
    }


}
