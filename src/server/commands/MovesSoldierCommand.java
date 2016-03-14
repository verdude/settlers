package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/Soldier endpoint
 * @author S Jacob Powell
 *
 */
public class MovesSoldierCommand implements ICatanCommand {

	private int playerIndex;
	private int victimIndex;
	private HexLocation location;

	public MovesSoldierCommand(int playerIndex, int victimIndex, HexLocation location) {
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
