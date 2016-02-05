package model;

public class MessageLine {

	
	private String message;
	private int source;
	
	
	public MessageLine(String message, int source) {
		this.message = message;
		this.source = source;
	}
	
	/**
	 * @return current message(String)
	 */
	public String getMessage() {
		return message;
	}
	/** 
	 * @param message Message to set (String)
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/** 
	 * @return source of message (String)
	 */
	public int getSource() {
		return source;
	}
	/**
	 * @param source sets the source of the message (String)
	 */
	public void setSource(int source) {
		this.source = source;
	}
}
