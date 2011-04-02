
package abstractions.piece;

import static abstractions.side.API.*;

final class NullPiece extends PotentialPiece {

    public NullPiece() {
        super(NULL_SIDE);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final String toString() {
        return " ";
    }

}