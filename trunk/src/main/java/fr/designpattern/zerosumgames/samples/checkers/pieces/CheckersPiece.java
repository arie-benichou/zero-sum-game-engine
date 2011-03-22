package fr.designpattern.zerosumgames.samples.checkers.pieces;

import fr.designpattern.zerosumgames.framework.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.IGameBoardCell;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.GamePiece;
import fr.designpattern.zerosumgames.framework.game.components.board.cell.piece.IGamePieceType;
import fr.designpattern.zerosumgames.framework.game.components.opponents.GamePlayersEnumeration;

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