
package move;

import piece.API.PieceInterface;
import position.API.PositionInterface;

class Move extends AbstractMove {

    public Move(final PositionInterface position, final PieceInterface piece) {
        super(position, piece);
        if (this.getPosition().isNull()) {
            throw new IllegalArgumentException("Argument 'position' must be a legal position.");
        }
        if (this.getPiece().isNull()) {
            throw new IllegalArgumentException("Argument 'piece' must be a legal piece.");
        }
    }

    @Override
    final public boolean isNull() {
        return false;
    }

}
