package client.join;

import client.misc.MessageView;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;
import model.Converter;
import model.Player;
import shared.definitions.CatanColor;
import client.base.Controller;
import client.base.IAction;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.misc.IMessageView;


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

		try {
			ClientFacade.getSingleton().addObserver(this);
		} catch (ClientException e) {
			e.printStackTrace();
		}
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
		// needs some serious refactoring
		joinedGame = game.getId();
		int index = 0;
		PlayerInfo localPlayer;
		try {
			localPlayer = ClientFacade.getSingleton().getLocalPlayer();
			for (PlayerInfo player : game.getPlayers()) {
				if (player.getId() == localPlayer.getId()) {
					localPlayer.setPlayerIndex(index);
					ClientFacade.getSingleton().setLocalPlayer(localPlayer);
					break;
				}
				index++;
			}
		} catch (ClientException e1) {
			System.out.println("Could not set the local Player. startJoinGame in JoinGameController.");
			e1.printStackTrace();
		}
		//Get all of the players' colors
		for (PlayerInfo player : game.getPlayers()) {
			// Disable all of the chosen colors except for the localPlayer's
			// This allows him to chose a color before he can join the game
			try {
				if (ClientFacade.getSingleton().getLocalPlayer().getId() != player.getId()) {
                    getSelectColorView().setColorEnabled(player.getColor(), false);
                }
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		// reset all of the color buttons
		for (CatanColor color : CatanColor.values()) {
			getSelectColorView().setColorEnabled(color, true);
		}
		getJoinGameView().closeModal();
	}

	private void resetColorModal(GameInfo game) {
		// to do this i had to make the reset button method public in the select color modal
		// I can't just use the set color enabled because in order to do that I would need to reset the modal entirely.
		for (int i = 1; i < 10; ++i) {
			((SelectColorView)getSelectColorView()).resetButton(i);
		}
	}

	@Override
	public void joinGame(CatanColor color) {
		try {
			GameInfo[] games = Converter.deserializeGamesArray(ClientFacade.getSingleton().gamesList());
			PlayerInfo newPlayer = ClientFacade.getSingleton().getLocalPlayer();
			for (GameInfo game : games) {
				if (game.getId() == joinedGame) {
					for (PlayerInfo player : game.getPlayers()) {
						if (player.getId() != newPlayer.getId() && color.equals(player.getColor())) {
							getSelectColorView().setColorEnabled(color, false);
							IMessageView error = new MessageView();
							error.setTitle("Error!");
							error.setMessage("Hey! "+ player.getName() +" already chose that color!");
							error.showModal();
							getSelectColorView().setColorEnabled(player.getColor(), false);
//							resetColorModal(game);
							return;
						}
					}
				}
			}
			newPlayer.setColor(color);
			ClientFacade.getSingleton().setLocalPlayer(newPlayer);
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
		// set local player on every notify just because
		PlayerInfo localPlayer;
		try {
			localPlayer = ClientFacade.getSingleton().getLocalPlayer();
			for (Player player : model.getPlayers()) {
				if (player.getPlayerID() == localPlayer.getId()) {
					System.out.println(player.getPlayerIndex());
					localPlayer.setPlayerIndex(player.getPlayerIndex());
					ClientFacade.getSingleton().setLocalPlayer(localPlayer);
					break;
				}
			}
		} catch (ClientException e1) {
			System.out.println("Could not set the local Player. join game controller.");
			e1.printStackTrace();
		}
		System.out.println("done");
	}

}

