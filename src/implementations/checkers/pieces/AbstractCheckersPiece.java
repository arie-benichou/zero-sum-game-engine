package implementations.checkers.pieces;

import core.AbstractGamePiece;
import core.interfaces.IGameBoardCell;
import core.interfaces.IGamePieceType;
import core.types.GameBoardCardinalPosition;
import core.types.GamePlayersEnumeration;

public abstract class AbstractCheckersPiece extends AbstractGamePiece implements ICheckersPiece {
	// ------------------------------------------------------------
	public AbstractCheckersPiece(final IGamePieceType type, final GamePlayersEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	protected boolean canJumpOver(final IGameBoardCell cell, final GameBoardCardinalPosition cardinalPosition) {
		
		IGameBoardCell neighbourCell = cell.getNeighbour(cardinalPosition);
		
		if(neighbourCell.isNull() || neighbourCell.isEmpty()) {
			return false;
		}
		
		if(neighbourCell.getPiece().getSide() == this.getSide()) {
			return false;
		}
		
		neighbourCell = neighbourCell.getNeighbour(cardinalPosition);
		
		if(neighbourCell.isNull() || !neighbourCell.isEmpty()) {
			return false;
		}
		
		return true;		
	}
	// ------------------------------------------------------------	
	protected boolean canWalkThrough(final IGameBoardCell cell, final GameBoardCardinalPosition cardinalPosition) {
		if(cell.getNeighbour(cardinalPosition).isNull()) {
			return false;
		}
		if(cell.getNeighbour(cardinalPosition).isEmpty()) {
			return true;
		}
		return false;
	}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		// TODO ? utiliser une map dans la factory
		return this.getSide().equals(GamePlayersEnumeration.FIRST_PLAYER) ? "x" : "o";
	}
	// -----------------------------------------------------------
}