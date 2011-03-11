package main.java.games.implementations.checkers.pieces;

import main.java.games.core.GamePiece;
import main.java.games.core.interfaces.IGameBoardCell;
import main.java.games.core.interfaces.IGamePieceType;
import main.java.games.core.types.GameBoardCardinalPosition;
import main.java.games.core.types.GamePlayersEnumeration;

public abstract class CheckersPiece extends GamePiece implements ICheckersPiece {
	// ------------------------------------------------------------
	public CheckersPiece(final IGamePieceType type, final GamePlayersEnumeration side) {
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
	//public abstract boolean isPromotable(final IGameBoardCell cell);
	// ------------------------------------------------------------	
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(GamePlayersEnumeration.FIRST_PLAYER) ? "x" : "o";
	}
	// -----------------------------------------------------------
}