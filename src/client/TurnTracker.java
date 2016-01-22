package client;

public class TurnTracker {
	
	private int currentTurn;// (index): Who's turn it is (0-3),
	private String status;// (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or 'SecondRound']: What's happening now,
	private int longestRoad;// (index): The index of who has the longest road, -1 if no one has it
	private int largestArmy;// (index): The index of who has the biggest army (3 or more), -1 if no one has it
	
	
	
	/**
	 * @return index of the player who's turn it is (0-3)
	 */
	public int getCurrentTurn() {
		return currentTurn;
	}
	
	/**
	 * @param currentTurn player index to set the turn to (0-3)
	 */
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	/**
	 * @return (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or 'SecondRound']: What's happening now
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * @param status status to set (String)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return The index of who has the longest road, -1 if no one has it
	 */
	public int getLongestRoad() {
		return longestRoad;
	}
	
	/**
	 * @param longestRoad longestRoad to set (int)
	 */
	public void setLongestRoad(int longestRoad) {
		this.longestRoad = longestRoad;
	}
	
	/**
	 * @return The index of who has the biggest army (3 or more), -1 if no one has it
	 */
	public int getLargestArmy() {
		return largestArmy;
	}
	
	/**
	 * @param largestArmy largestArmy to set (int)
	 */
	public void setLargestArmy(int largestArmy) {
		this.largestArmy = largestArmy;
	}

	
	
}
