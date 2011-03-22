package fr.designpattern.zerosumgames.framework.game.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.GameBoard;
import fr.designpattern.zerosumgames.framework.game.components.board.IGameBoard;
import fr.designpattern.zerosumgames.framework.game.components.board.cells.GameBoardCellFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.cells.IGameBoardCellFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.IGameBoardDimension;
import fr.designpattern.zerosumgames.framework.game.components.board.positions.GameBoardPositionFactory;
import fr.designpattern.zerosumgames.framework.game.components.board.positions.IGameBoardPositionFactory;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GameOpponents;
import fr.designpattern.zerosumgames.framework.game.components.opponents.IGameOpponents;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.GamePlayer;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.GamePlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.IGamePlayer;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.RandomMoveStrategy;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class Builder implements BuilderInterface {

	private transient final Class<? extends GameInterface> builderGameClass;
	private transient IGameBoardDimension builderBoardDimension;
	private transient IGamePlayer builderPlayer1 = new GamePlayer("Player 1", GamePlayerNature.COMPUTER, new RandomMoveStrategy());
	private transient IGamePlayer builderPlayer2 = new GamePlayer("Player 2", GamePlayerNature.COMPUTER, new RandomMoveStrategy());

	public Builder(final Class<? extends GameInterface> gameClass) {
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

	public final Builder boardDimension(final IGameBoardDimension boardDimension) {
		this.builderBoardDimension = boardDimension;
		return this;
	}

	public final Builder player1(final IGamePlayer player1) {
		this.builderPlayer1 = player1;
		return this;
	}

	public final Builder player2(final IGamePlayer player2) {
		this.builderPlayer2 = player2;
		return this;
	}

	public GameInterface build() {
		
		final IGameBoardPositionFactory positionFactory = new GameBoardPositionFactory(this.builderBoardDimension);
		final IGameBoardCellFactory cellFactory = new GameBoardCellFactory(positionFactory);
		
		final IGameBoard board = new GameBoard(cellFactory);
		final IGameOpponents opponents = new GameOpponents(this.builderPlayer1, this.builderPlayer2);
		
		return newInstance(board, opponents);
		
	}

	private GameInterface newInstance(final IGameBoard board, final IGameOpponents opponents) {
		Constructor<? extends GameInterface> constructor = null;
		GameInterface instance = null;
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