package fr.designpattern.zerosumgames.extensions.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePieceType;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;

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
	public CheckersPieceKing(final IGamePieceType type, final GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
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
	public boolean isPromotable(final IGameBoardCell cell) {
		return false;
	}
}