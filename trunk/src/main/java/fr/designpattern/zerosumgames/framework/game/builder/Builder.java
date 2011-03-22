package fr.designpattern.zerosumgames.framework.game.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.Board;
import fr.designpattern.zerosumgames.framework.game.components.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.Cells;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions.PositionsInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.Opponents;
import fr.designpattern.zerosumgames.framework.game.components.opponents.OpponentsInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.Player;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerNature;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.PlayerInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.RandomLegalMove;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class Builder implements BuilderInterface {

	private transient final Class<? extends GameInterface> builderGameClass;
	private transient DimensionInterface builderBoardDimension;
	private transient PlayerInterface builderPlayer1 = new Player("Player 1", PlayerNature.COMPUTER, new RandomLegalMove());
	private transient PlayerInterface builderPlayer2 = new Player("Player 2", PlayerNature.COMPUTER, new RandomLegalMove());

	public Builder(final Class<? extends GameInterface> gameClass) {
		this.builderGameClass = gameClass;
		try {
			this.boardDimension((DimensionInterface) this.builderGameClass.getDeclaredField("BOARD_DIMENSION").get(DimensionInterface.class));
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

	public final Builder boardDimension(final DimensionInterface boardDimension) {
		this.builderBoardDimension = boardDimension;
		return this;
	}

	public final Builder player1(final PlayerInterface player1) {
		this.builderPlayer1 = player1;
		return this;
	}

	public final Builder player2(final PlayerInterface player2) {
		this.builderPlayer2 = player2;
		return this;
	}

	public GameInterface build() {
		
		final PositionsInterface positionFactory = new Positions(this.builderBoardDimension);
		final CellsInterface cellFactory = new Cells(positionFactory);
		
		final BoardInterface board = new Board(cellFactory);
		final OpponentsInterface opponents = new Opponents(this.builderPlayer1, this.builderPlayer2);
		
		return newInstance(board, opponents);
		
	}

	private GameInterface newInstance(final BoardInterface board, final OpponentsInterface opponents) {
		Constructor<? extends GameInterface> constructor = null;
		GameInterface instance = null;
		try {
			constructor = this.builderGameClass.getDeclaredConstructor(BoardInterface.class, OpponentsInterface.class);
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