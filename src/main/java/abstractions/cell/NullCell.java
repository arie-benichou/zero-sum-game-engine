
package abstractions.cell;

import static abstractions.position.API.*;
import static com.google.common.base.Preconditions.*;
import abstractions.piece.API.PieceInterface;

final class NullCell extends PotentialCell {

    public NullCell() {
        super(NULL_POSITION);
    }
    
    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        checkArgument(piece.isNull(), "Argument 'piece' must be the null piece object.");
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

    @Override
    public boolean isMutable() {
        return false;
    }

}