package fr.designpattern.zerosumgames.samples.checkers.pieces;

import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.Piece;
import fr.designpattern.zerosumgames.framework.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.OpponentsEnumeration;

public abstract class CheckersPiece extends Piece implements ICheckersPiece {
	// ------------------------------------------------------------
	public CheckersPiece(final PieceTypeInterface type, final OpponentsEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	protected boolean canJumpOver(final CellInterface cell, final BoardCardinalPosition cardinalPosition) {
		final CellInterface neighbourCell = cell.getNeighbour(cardinalPosition);
		return
			!
			(
				neighbourCell.isNull() || neighbourCell.isEmpty() ||
				(neighbourCell.getPiece().getSide() == this.getSide()) ||
				neighbourCell.getNeighbour(cardinalPosition).isNull()
			)
			&& neighbourCell.getNeighbour(cardinalPosition).isEmpty(); 
	}
	// ------------------------------------------------------------	
	protected boolean canWalkThrough(final CellInterface cell, final BoardCardinalPosition cardinalPosition) {
		return !cell.getNeighbour(cardinalPosition).isNull() && cell.getNeighbour(cardinalPosition).isEmpty();
	}
	// ------------------------------------------------------------
	//public abstract boolean isPromotable(final IGameBoardCell cell);
	// ------------------------------------------------------------	
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(OpponentsEnumeration.FIRST_PLAYER) ? "x" : "o";
	}
	// -----------------------------------------------------------
}