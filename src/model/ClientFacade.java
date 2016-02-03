package model;

import java.util.List;

public class ClientFacade {
	
	private ClientModel  clientModel;
	

	/**
	 * Default Constructor
	 * 
	 */
	public ClientFacade() {
		clientModel	= new ClientModel();
	}

	/**
	 * Decrease the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to remove from the bank
	 * @pre The resource must exist and there must be at least `amount` amount of that resource in the bank
	 * @post There will be `amount` less of that resource in the bank
	 */
	public void removeResource(String resource, int amount) {}

	/**
	 * Increase the amount of the resource by the specified amount.
	 * @param resource: String, the type of resource
	 * @param amount: int, The amount of the resource to add to the bank
	 * @pre The resource must exist and there must be at least `amount` less than 19 of that resource in the bank
	 * @post There will be `amount` more of that resource in the bank
	 */
	public void addResource(String resource, int amount) {}

	/**
	 * Adds a MessageLine to the chat and log
	 * @param line: String, The line to be added to the chat/log
	 * @post The line will be added to both the chat and the log lists
	 */
	public void addChatMessage(MessageLine line){}

	/**
	 * Sender makes a trade offer for any number of items to the receiver
	 * @param offer: List[Integer], posotive numbers are resources being offered, negative are resources being asked for
	 * @param sender: int, The index of the sender in the player array
	 * @param receiver: int, The index of the receiver in the player array
	 * @return Whether the trade was successfull.
	 * @pre The offer array must not be empty
	 * @pre All offered resources must be owned by the sender
	 * @pre All requested resources must be owned by the receiver
	 * @pre It must be the sender's turn
	 * @pre The sender and reveiver numbers must map to a player in the player array
	 * @pre The sender &ne; receiver
	 * @post If the receiver accepts, then the posotive resources will be given to the receiver and the negative will be 
	 * given to the sender
	 */
	public boolean offerTrade(List<Integer> offer, int sender, int receiver) {return false;}
	
	/**
	 * Switches to the next player; player 0 if currently on player 3
	 * @pre None
	 * @post Calls Player.endTurn(), updates the version and the if there is one. Updates the turn tracker with the status. Updates the winner if the player has won.
	 */
	public void endTurn() {}

	
}
