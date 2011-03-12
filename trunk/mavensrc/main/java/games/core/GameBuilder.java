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
import main.java.games.core.interfaces.IGameBuilder;
import main.java.games.core.interfaces.IGamePlayer;
import main.java.games.core.strategies.GamePlayerRandomStrategy;
import main.java.games.core.types.GamePlayerNature;
import main.java.games.core.types.GamePlayersEnumeration;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class GameBuilder implements IGameBuilder {

	private transient final Class<? extends IGame> builderGameClass;
	private transient IGameBoardDimension builderBoardDimension;
	private transient IGamePlayer builderPlayer1 = new GamePlayer("Player 1", GamePlayersEnumeration.FIRST_PLAYER, GamePlayerNature.COMPUTER, new GamePlayerRandomStrategy()); // TODO ? singleton pour une stratégie
	private transient IGamePlayer builderPlayer2 = new GamePlayer("Player 2", GamePlayersEnumeration.SECOND_PLAYER, GamePlayerNature.HUMAN, new GamePlayerRandomStrategy()); // TODO ? singleton pour une stratégie

	public GameBuilder(final Class<? extends IGame> gameClass) {
		this.builderGameClass = gameClass;
		try {
			this.boardDimension((IGameBoardDimension) this.builderGameClass.getDeclaredField("BOARD_DIMENSION").get(IGameBoardDimension.class));
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
		this.builderBoardDimension = boardDimension;
		return this;
	}

	public final GameBuilder player1(final IGamePlayer player1) {
		this.builderPlayer1 = player1;
		return this;
	}

	public final GameBuilder player2(final IGamePlayer player2) {
		this.builderPlayer2 = player2;
		return this;
	}

	public IGame build() {
		final IGameBoardPositionFactory positionFactory = new GameBoardPositionFactory(this.builderBoardDimension);
		final IGameBoardCellFactory cellFactory = new GameBoardCellFactory(positionFactory);
		final IGameBoard board = new GameBoard(cellFactory);
		final List<IGamePlayer> opponents = new ArrayList<IGamePlayer>();
		opponents.add(this.builderPlayer1);
		opponents.add(this.builderPlayer2);
		return newInstance(board, opponents);
	}

	private IGame newInstance(final IGameBoard board, final List<IGamePlayer> opponents) {
		Constructor<? extends IGame> constructor = null;
		IGame instance = null;
		try {
			constructor = this.builderGameClass.getDeclaredConstructor(IGameBoard.class, List.class);
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