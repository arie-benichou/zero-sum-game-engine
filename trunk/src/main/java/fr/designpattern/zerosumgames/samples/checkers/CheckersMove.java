package fr.designpattern.zerosumgames.samples.checkers;

import fr.designpattern.zerosumgames.framework.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.game.board.dimension.cells.pieces.PieceInterface;
import fr.designpattern.zerosumgames.framework.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.moves.Move;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;

// TODO ? implémenter une interface
public class CheckersMove extends Move {
	// ---------------------------------------------------------------------	
	private BoardCardinalPosition direction;
	private final void setDirection(final BoardCardinalPosition direction) {
		this.direction = direction;
	}
	public final BoardCardinalPosition getDirection() {
		return direction;
	}	
	// ---------------------------------------------------------------------	
	private PieceInterface capturedPiece;
	public final void setCapturedPiece(PieceInterface capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
	public final PieceInterface getCapturedPiece() {
		return capturedPiece;
	}	
	// ---------------------------------------------------------------------
	private boolean isDone;
	public boolean isDone() {
		return this.isDone;
	}
	public boolean isDone(boolean isDone) {
		return this.isDone = isDone;
	}	
	// ---------------------------------------------------------------------
	private boolean hasBeenCrowned;
	public boolean hasBeenCrowned () {
		return this.hasBeenCrowned;
	}
	public void hasBeenCrowned (boolean hasBeenCrowned) {
		this.hasBeenCrowned = hasBeenCrowned;
	}	
	// ---------------------------------------------------------------------	
	public CheckersMove(final OpponentsEnumeration side, final PositionInterface position, final BoardCardinalPosition direction) {
		super(side, position);
		this.setDirection(direction);
	}
	// ---------------------------------------------------------------------
	@Override
	public final String toString() {
		return super.toString() + " " + this.getDirection();
	}
	// ---------------------------------------------------------------------
}