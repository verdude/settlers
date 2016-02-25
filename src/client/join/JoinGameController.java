package client.join;

import client.base.Controller;
import client.base.IAction;
import client.data.GameInfo;
import client.misc.IMessageView;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.Converter;
import shared.definitions.CatanColor;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private int joinedGame;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}

	@Override
	public void start() {
		try {
			GameInfo[] games = (GameInfo[]) Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());
			getJoinGameView().setGames(games, ClientFacade.getSingleton().getLocalPlayer());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		String randomTiles = getNewGameView().getRandomlyPlaceHexes()+"";
		String randomNumbers = getNewGameView().getRandomlyPlaceNumbers()+"";
		String randomPorts = getNewGameView().getUseRandomPorts()+"";
		String gameName = getNewGameView().getTitle();
		try {
			String gameData = ClientFacade.getSingleton().gamesCreate(randomTiles, randomNumbers, randomPorts, gameName);
			GameInfo game = Converter.deserialize(gameData, GameInfo.class);
			boolean joinSuccess = ClientFacade.getSingleton().gamesJoin(game.getId(), "puce");
			if (joinSuccess) {
				System.out.println("Joined game.");
			} else {
				System.out.println("Failed to join the new game.");
			}
		} catch (ClientException e) {
			System.out.println("Could not create game");
			e.printStackTrace();
		}
		getNewGameView().closeModal();
		start();
	}

	@Override
	public void startJoinGame(GameInfo game) {
		joinedGame = game.getId();
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		try {
			ClientFacade.getSingleton().gamesJoin(joinedGame, color.toString().toLowerCase());
			// If join succeeded
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		} catch (ClientException e) {
			System.out.println("failed to join the game");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		
	}

}

