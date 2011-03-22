package fr.designpattern.zerosumgames.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoard;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardCellFactory;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardDimension;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPositionFactory;
import fr.designpattern.zerosumgames.core.interfaces.IGameBuilder;
import fr.designpattern.zerosumgames.core.interfaces.IGameOpponents;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.strategies.RandomStrategy;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class GameBuilder implements IGameBuilder {

	private transient final Class<? extends IGame> builderGameClass;
	private transient IGameBoardDimension builderBoardDimension;
	private transient IGamePlayer builderPlayer1 = new GamePlayer("Player 1", GamePlayerNature.COMPUTER, new RandomStrategy());
	private transient IGamePlayer builderPlayer2 = new GamePlayer("Player 2", GamePlayerNature.COMPUTER, new RandomStrategy());

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
		final IGameOpponents opponents = new GameOpponents(this.builderPlayer1, this.builderPlayer2);
		
		return newInstance(board, opponents);
		
	}

	private IGame newInstance(final IGameBoard board, final IGameOpponents opponents) {
		Constructor<? extends IGame> constructor = null;
		IGame instance = null;
		try {
			constructor = this.builderGameClass.getDeclaredConstructor(IGameBoard.class, IGameOpponents.class);
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