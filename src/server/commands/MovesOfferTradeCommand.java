package server.commands;

import server.ICatanCommand;

/**
 * This represents the moves/offerTrade endpoint
 * @author S Jacob Powell
 *
 */
public class MovesOfferTradeCommand implements ICatanCommand {

	// not sure the json will map to this.
	private TradeOffer offer;

	public MovesOfferTradeCommand(TradeOffer offer) {
		this.offer = offer;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
