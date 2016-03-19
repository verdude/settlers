package server.commands;

import model.IFacade;
import server.ICatanCommand;

/**
 * This represents the moves/maritimeTrade endpoint
 * @author S Jacob Powell
 *
 */
public class MovesMaritimeTradeCommand extends ICatanCommand {

	int playerIndex;
	int ratio;
	String inputResource;
	String outputResource;


	/**
	 * @pre The associated cando returns true
	 * @post a maritime trade of the specified type is performed
	 * @param playerIndex the playerIndex of the player performing the maritime trade
	 * @param ratio the ratio of the trade (3 if a 3:1 trade for example)
	 * @param inputResource the type of resource the player is giving
	 * @param outputResource the type of resource the player is getting
     */
	public MovesMaritimeTradeCommand(int playerIndex, int ratio, String inputResource, String outputResource){
		this.playerIndex = playerIndex;
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}


	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {
		// TODO Auto-generated method stub
		
	}
}
