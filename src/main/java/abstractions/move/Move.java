
package abstractions.move;

import static com.google.common.base.Preconditions.*;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;

@Deprecated
class Move extends AbstractMove {

    public Move(final PositionInterface position, final PieceInterface piece) {
        super(position, piece);
        checkArgument(!this.getPosition().isNull(), "Argument 'position' is not intended to be the null position object.");
        checkArgument(!this.getPiece().isNull(), "Argument 'piece' is not intended to be the null piece object.");
    }

    @Override
    final public boolean isNull() {
        return false;
    }

}
