package fr.designpattern.zerosumgames.extensions.checkers;

import fr.designpattern.zerosumgames.framework.build.game.components.board.GameBoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.board.cell.piece.IGamePiece;
import fr.designpattern.zerosumgames.framework.build.game.components.board.position.IGameBoardPosition;
import fr.designpattern.zerosumgames.framework.build.game.components.move.GameBoardMove;
import fr.designpattern.zerosumgames.framework.build.game.components.opponents.GamePlayersEnumeration;

// TODO ? implémenter une interface
public class CheckersMove extends GameBoardMove {
	// ---------------------------------------------------------------------	
	private GameBoardCardinalPosition direction;
	private final void setDirection(final GameBoardCardinalPosition direction) {
		this.direction = direction;
	}
	public final GameBoardCardinalPosition getDirection() {
		return direction;
	}	
	// ---------------------------------------------------------------------	
	private IGamePiece capturedPiece;
	public final void setCapturedPiece(IGamePiece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
	public final IGamePiece getCapturedPiece() {
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
	public CheckersMove(final GamePlayersEnumeration side, final IGameBoardPosition position, final GameBoardCardinalPosition direction) {
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