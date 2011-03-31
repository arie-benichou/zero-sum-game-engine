package move;

import static piece.API.*;
import static position.API.*;

public class NullMove extends AbstractMove {

	public NullMove() {
		super(NULL_POSITION, NULL_PIECE);
	}

	@Override
	public boolean isNull() {
		return true;
	}

}