package implementations.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import core.interfaces.IGameBoardCell;
import core.interfaces.IGamePieceType;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

public class CheckersPieceKing extends CheckersPiece {
	// ------------------------------------------------------------
	private static final GameBoardCardinalPosition[] POSITIONS =
	{
		GameBoardCardinalPosition.TOP_RIGHT,
		GameBoardCardinalPosition.BOTTOM_RIGHT,
		GameBoardCardinalPosition.TOP_LEFT,
		GameBoardCardinalPosition.BOTTOM_LEFT
	};
	// ------------------------------------------------------------
	public CheckersPieceKing(IGamePieceType type, GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	@Override
	public List<GameBoardCardinalPosition> getWalkOptions(IGameBoardCell cell) {
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
		for (GameBoardCardinalPosition direction : POSITIONS) {
			if (this.canWalkThrough(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------	
	@Override
	public List<GameBoardCardinalPosition> getJumpOptions(IGameBoardCell cell) {
		// TODO ? utiliser un EnumSet
		List<GameBoardCardinalPosition> options = new ArrayList<GameBoardCardinalPosition>();
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