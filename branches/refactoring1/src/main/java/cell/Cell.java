
package cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static position.API.*;
import piece.API.PieceInterface;

final class Cell extends AbstractCell {

    public Cell(final PositionInterface position) {
        super(position);
        if (position.isNull()) {
            throw new IllegalArgumentException("Argument 'position' must be a legal position");
        }
    }
    
    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' must not be null.");
        checkArgument(!piece.isNull(), "Argument 'piece' must not be a null object.");
        this.piece = piece;
    }
    

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return " " + this.getPiece() + " |";

    }
}