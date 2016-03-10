package server;

import server.commands.GameAddAICommand;
import server.commands.GameListAICommand;
import server.commands.GamesCreateCommand;
import server.commands.GamesJoinCommand;
import server.commands.GamesListCommand;
import server.commands.GamesModelCommand;
import server.commands.MovesAcceptTradeCommand;
import server.commands.MovesBuildCityCommand;
import server.commands.MovesBuildRoadCommand;
import server.commands.MovesBuildSettlementCommand;
import server.commands.MovesBuyDevCardCommand;
import server.commands.MovesDiscardCardsCommand;
import server.commands.MovesFinishTurnCommand;
import server.commands.MovesMaritimeTradeCommand;
import server.commands.MovesMonopolyCommand;
import server.commands.MovesMonumentCommand;
import server.commands.MovesOfferTradeCommand;
import server.commands.MovesRoadBuildingCommand;
import server.commands.MovesRobPlayerCommand;
import server.commands.MovesRollNumberCommand;
import server.commands.MovesSendChatCommand;
import server.commands.MovesSoldierCommand;
import server.commands.MovesYearOfPlentyCommand;
import server.commands.UserLoginCommand;
import server.commands.UserRegisterCommand;

/**
 * This is the class that contains all of the commands which are to be done on the server model.
 * Each method takes in the corresponding Command class. 
 * @author S. Jacob Powell
 *
 */

public class ServerFacade {

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void userLogin(UserLoginCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void userRegister(UserRegisterCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gamesList(GamesListCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gamesCreate(GamesCreateCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gamesJoin(GamesJoinCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gamesModel(GamesModelCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gameAddAI(GameAddAICommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void gameListAI(GameListAICommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesSendChat(MovesSendChatCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesRollNumber(MovesRollNumberCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesRobPlayer(MovesRobPlayerCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesFinishTurn(MovesFinishTurnCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesBuyDevCard(MovesBuyDevCardCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesYearOfPlenty(MovesYearOfPlentyCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesRoadBuilding(MovesRoadBuildingCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesSoldier(MovesSoldierCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesMonopoly(MovesMonopolyCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesMonument(MovesMonumentCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesBuildRoad(MovesBuildRoadCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesBuildSettlement(MovesBuildSettlementCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesBuildCity(MovesBuildCityCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesOfferTrade(MovesOfferTradeCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesAcceptTrade(MovesAcceptTradeCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesMaritimeTrade(MovesMaritimeTradeCommand command) {
		
	}

	/**
	 * @pre The commmand passed in is constructed with valid fields so the the execute method can be called.
	 * @post The command passed in is executed on the model and stored in the database as needed.
	 * @param command The command to be executed, of the type that corresponds to the method name.
	 */
	public void movesDiscardCards(MovesDiscardCardsCommand command) {
		
	}
	
}
