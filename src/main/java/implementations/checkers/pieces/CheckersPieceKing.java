package main.java.implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import main.java.core.interfaces.IGameBoardCell;
import main.java.core.interfaces.IGamePieceType;
import main.java.core.types.GameBoardCardinalPosition;
import main.java.core.types.GamePlayersEnumeration;

public class CheckersPieceKing extends AbstractCheckersPiece {
	// ------------------------------------------------------------
	private static final GameBoardCardinalPosition[] POSITIONS =
	{
		GameBoardCardinalPosition.TOP_RIGHT,
		GameBoardCardinalPosition.BOTTOM_RIGHT,
		GameBoardCardinalPosition.TOP_LEFT,
		GameBoardCardinalPosition.BOTTOM_LEFT
	};
	// ------------------------------------------------------------
	public CheckersPieceKing(final IGamePieceType type, final GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getWalkOptions(final IGameBoardCell cell) {
		// TODO ? utiliser un EnumSet
		final List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		for (GameBoardCardinalPosition direction : POSITIONS) {
			if (this.canWalkThrough(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------	
	@Override
	public List<GameBoardCardinalPosition> getJumpOptions(final IGameBoardCell cell) {
		// TODO ? utiliser un EnumSet
		final List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		for (GameBoardCardinalPosition direction : POSITIONS) {
			if (this.canJumpOver(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		return super.toString().toUpperCase();
	}
	// ------------------------------------------------------------	
}