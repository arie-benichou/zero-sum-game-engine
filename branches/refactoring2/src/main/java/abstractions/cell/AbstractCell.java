
package abstractions.cell;

import static abstractions.position.API.*;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.piece.API.PieceInterface;

//TODO à mettre dans API
public abstract class AbstractCell extends PotentialCell {

    public AbstractCell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }

    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
        this.piece = piece;
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    @Override
    public abstract boolean isMutable(); // TODO regarder comment tester une classe abstraite (faire un test qui hérite de la classe ?)

    @Override
    public final String toString() {
        return " " + this.getPiece() + " |";

    }
    
}