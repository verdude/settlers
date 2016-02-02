package model;

import java.util.List;


public class TradeOffer {
	
	
	private int sender; // The index of the person offering the trade,
	private int receiver;//The index of the person the trade was offered to.,
	private List<Integer> offer;// Positive numbers are resources being offered. Negative are resource being asked for.
	

	
	
	/**
	 * @return The index of the person offering the trade
	 */
	public int getSender() {
		return sender;
	}
	
	/**
	 * @param sender sender to set (int)
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}
	
	/**
	 * @return The index of the person the trade was offered to
	 */
	public int getReceiver() {
		return receiver;
	}
	
	/**
	 * @param receiver receiver to set (int)
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * @return gets the resources that are offered and which are requested
	 * Positive numbers are resources being offered. Negative are resource being asked for.
	 */
	public List<Integer> getOffer() {
		return offer;
	}
	
	/**
	 * @param offer offer to be set (List[Integer])
	 */
	public void setOffer(List<Integer> offer) {
		this.offer = offer;
	}
	
	



}
