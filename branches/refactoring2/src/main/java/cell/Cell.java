
package cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static position.API.*;
import piece.API.PieceInterface;

final class Cell extends AbstractCell {

    public Cell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }
    
    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
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