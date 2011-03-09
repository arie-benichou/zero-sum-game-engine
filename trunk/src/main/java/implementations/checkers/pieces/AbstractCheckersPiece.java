package main.java.implementations.checkers.pieces;

import main.java.core.AbstractGamePiece;
import main.java.core.interfaces.IGameBoardCell;
import main.java.core.interfaces.IGamePieceType;
import main.java.core.types.GameBoardCardinalPosition;
import main.java.core.types.GamePlayersEnumeration;

public abstract class AbstractCheckersPiece extends AbstractGamePiece implements ICheckersPiece {
	// ------------------------------------------------------------
	public AbstractCheckersPiece(final IGamePieceType type, final GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	protected boolean canJumpOver(final IGameBoardCell cell, final GameBoardCardinalPosition cardinalPosition) {
		final IGameBoardCell neighbourCell = cell.getNeighbour(cardinalPosition);
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
	protected boolean canWalkThrough(final IGameBoardCell cell, final GameBoardCardinalPosition cardinalPosition) {
		return !cell.getNeighbour(cardinalPosition).isNull() && cell.getNeighbour(cardinalPosition).isEmpty();
	}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(GamePlayersEnumeration.FIRST_PLAYER) ? "x" : "o";
	}
	// -----------------------------------------------------------
}