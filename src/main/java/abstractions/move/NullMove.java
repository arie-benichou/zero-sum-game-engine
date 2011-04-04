package abstractions.move;

import static abstractions.piece.API.*;
import static abstractions.position.API.*;

@Deprecated
class NullMove extends AbstractMove {

	public NullMove() {
		super(NULL_POSITION, NULL_PIECE);
	}

	@Override
	final public boolean isNull() {
		return true;
	}

}