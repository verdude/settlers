package client;

import java.util.List;


public class MessageList {

	private List<MessageLine> lines;
	
	/** Adds a message to the MessageList 
	 * @pre none
	 * @post a MessageLine will be added to the List<MessageLine> in MessageList
	 */
	public void addMessage(MessageLine message){}

	/**
	 * @return gets all of the current MessageLines (List<MessgeLine>)
	 */
	public List<MessageLine> getLines() {
		return lines;
	}

	/**
	 * @param lines  the list of MessageLines to set lines(List<MessageLine> to)
	 */
	public void setLines(List<MessageLine> lines) {
		this.lines = lines;
	} 
	
}
