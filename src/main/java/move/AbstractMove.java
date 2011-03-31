package move;

import move.API.MoveInterface;
import piece.API.PieceInterface;
import position.API.PositionInterface;

abstract class AbstractMove implements MoveInterface {

	private final PieceInterface piece;
	private final PositionInterface position;

	public AbstractMove(final PositionInterface position, final PieceInterface piece) {
		this.position = position;
		this.piece = piece;
	}

	public PositionInterface getPosition() {
		return this.position;
	}

	public PieceInterface getPiece() {
		return this.piece;
	}

	public abstract boolean isNull();

}
