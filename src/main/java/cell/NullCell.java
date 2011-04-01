
package cell;

import static com.google.common.base.Preconditions.*;
import static position.API.*;
import piece.API.PieceInterface;

final class NullCell extends AbstractCell {

    public NullCell() {
        super(NULL_POSITION);
    }
    
    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' must not be null.");
        checkArgument(piece.isNull(), "Argument 'piece' must be a null object.");
        //Anyway, your piece goes into the vacuum...
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}