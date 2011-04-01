
package piece;

import static side.API.*;

final class NullPiece extends AbstractPiece {

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