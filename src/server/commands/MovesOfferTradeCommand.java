package server.commands;

import model.IFacade;
import model.TradeOffer;
import server.ICatanCommand;

/**
 * This represents the moves/offerTrade endpoint
 * @author S Jacob Powell
 *
 */
public class MovesOfferTradeCommand extends ICatanCommand {

	// not sure the json will map to this.
	private TradeOffer offer;

	/**
	 * @pre It is the player's turn and the player has the required resources as well as the target player
	 * @post The Trade is sent to the target player
	 * @param offer The offer
     */
	public MovesOfferTradeCommand(TradeOffer offer) {
		this.offer = offer;
	}

	/**
	 * @pre The object is instantiated and a game exists on which to execute this command
	 * @post This command is executed on the model
	 */
	@Override
	public String execute(IFacade facade) {

		String response = facade.offerTrade(offer);
		
		// TODO: on success, store this command in database
		
		return response;
	}
}
