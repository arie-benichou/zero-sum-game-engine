package fr.designpattern.zerosumgames.framework.service.gameplay.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.Board;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.DimensionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.Cells;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellsInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.Positions;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionsInterface;

// TODO ? as a Game inner-class
// TODO ! gérer l'injection de préférences spécifiques à un jeu
// TODO ? un builder abstrait pourrait retourner un builder concret d'un jeu
public class GameBuilder implements GameBuilderInterface {

	private transient final Class<? extends GameInterface> builderGameClass;
	private transient DimensionInterface builderBoardDimension;

	public GameBuilder(final Class<? extends GameInterface> gameClass) {
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

	public final GameBuilder boardDimension(final DimensionInterface boardDimension) {
		this.builderBoardDimension = boardDimension;
		return this;
	}
	
	public GameInterface build() {
		final PositionsInterface positionFactory = new Positions(this.builderBoardDimension);
		final CellsInterface cellFactory = new Cells(positionFactory);
		final BoardInterface board = new Board(cellFactory);
		return newInstance(board);
	}

	private GameInterface newInstance(final BoardInterface board) {
		Constructor<? extends GameInterface> constructor = null;
		GameInterface instance = null;
		try {
			constructor = this.builderGameClass.getDeclaredConstructor(BoardInterface.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		try {
			instance = constructor.newInstance(board);
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