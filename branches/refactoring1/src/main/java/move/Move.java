package move;

import piece.API.PieceInterface;
import position.API.PositionInterface;

public class Move extends AbstractMove {

	public Move(PositionInterface position, PieceInterface piece) {
		super(position, piece);
	}

	@Override
	public boolean isNull() {
		return false;
	}

}
