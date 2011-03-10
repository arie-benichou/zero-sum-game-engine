package main.java.games.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import main.java.games.core.interfaces.IGame;
import main.java.games.core.interfaces.IGameBoard;
import main.java.games.core.interfaces.IGameBoardCellFactory;
import main.java.games.core.interfaces.IGameBoardDimension;
import main.java.games.core.interfaces.IGameBoardPositionFactory;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.types.GamePlayerNature;
import main.java.games.core.types.GamePlayersEnumeration;

// TODO définir une interface
// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
public class GameBuilder {

	private final Class<? extends IGame> gameClass;
	private IGameBoardDimension boardDimension;
	private IGamePlayer player1 = new GamePlayer("Player 1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerRandomStrategy()); // TODO ? singleton pour une stratégie
	private IGamePlayer player2 = new GamePlayer("Player 2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerRandomStrategy()); // TODO ? singleton pour une stratégie

	public GameBuilder(final Class<? extends IGame> gameClass) {
		this.gameClass = gameClass;
		try {
			this.boardDimension((IGameBoardDimension) this.gameClass.getDeclaredField("BOARD_DIMENSION").get(IGameBoardDimension.class));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final GameBuilder boardDimension(final IGameBoardDimension boardDimension) {
		this.boardDimension = boardDimension;
		return this;
	}

	public final GameBuilder player1(final IGamePlayer player1) {
		this.player1 = player1;
		return this;
	}

	public final GameBuilder player2(final IGamePlayer player2) {
		this.player2 = player2;
		return this;
	}

	public IGame build() {
		final IGameBoardPositionFactory positionFactory = new GameBoardPositionFactory(this.boardDimension);
		final IGameBoardCellFactory cellFactory = new GameBoardCellFactory(positionFactory);
		final IGameBoard board = new GameBoard(cellFactory);
		final List<IGamePlayer> opponents = new ArrayList<IGamePlayer>();
		opponents.add(this.player1);
		opponents.add(this.player2);
		return newInstance(board, opponents);
	}

	private IGame newInstance(final IGameBoard board, final List<IGamePlayer> opponents) {
		Constructor<? extends IGame> constructor = null;
		IGame instance = null;
		try {
			constructor = this.gameClass.getDeclaredConstructor(IGameBoard.class, List.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			instance = constructor.newInstance(board, opponents);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
	}

}