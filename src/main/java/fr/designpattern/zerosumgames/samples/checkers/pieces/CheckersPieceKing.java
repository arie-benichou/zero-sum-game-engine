package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;

public class CheckersPieceKing extends CheckersPiece {
	// ------------------------------------------------------------
	private static final BoardCardinalPosition[] POSITIONS =
	{
		BoardCardinalPosition.TOP_RIGHT,
		BoardCardinalPosition.BOTTOM_RIGHT,
		BoardCardinalPosition.TOP_LEFT,
		BoardCardinalPosition.BOTTOM_LEFT
	};
	// ------------------------------------------------------------
	public CheckersPieceKing(final PieceTypeInterface type, final OpponentsEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	public List<BoardCardinalPosition> getWalkOptions(final CellInterface cell) {
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		for (BoardCardinalPosition direction : POSITIONS) {
			if (this.canWalkThrough(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------	
	public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		for (BoardCardinalPosition direction : POSITIONS) {
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
	public boolean isPromotable(final CellInterface cell) {
		return false;
	}
}