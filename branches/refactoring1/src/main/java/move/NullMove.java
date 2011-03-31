package move;

import static piece.API.*;
import static position.API.*;

class NullMove extends AbstractMove {

	public NullMove() {
		super(NULL_POSITION, NULL_PIECE);
	}

	@Override
	final public boolean isNull() {
		return true;
	}

}