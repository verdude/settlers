package client;

import java.util.ArrayList;
import java.util.List;

public class MessageList {

	private List<MessageLine> lines = new ArrayList<MessageLine>();
	
	/**
	 * @param message message of type MessageLine to be added to lines
	 * @return true if the message can be added, false otherwise
	 * @pre None
	 * @post true if the message can be added, false otherwise
	 */
	public boolean canAddMessage(MessageLine message){
		return false;
	}
	
	/** Adds a message to the MessageList 
	 * @pre none
	 * @post a MessageLine will be added to the List[MessageLine] in MessageList
	 * @param message, a MessageLine that will be added to the list
	 * @throws Exception if addMessage() runs and dies when it shouldn't 
	 */
	public void addMessage(MessageLine message) throws Exception {
		lines.add(message);
	}

	/**
	 * @return gets all of the current MessageLines (List[MessgeLine])
	 */
	public List<MessageLine> getLines() {
		return lines;
	}

	/**
	 * @param lines  the list of MessageLines to set lines(List[MessageLine]) to
	 */
	public void setLines(List<MessageLine> lines) {
		this.lines = lines;
	} 
	
}
