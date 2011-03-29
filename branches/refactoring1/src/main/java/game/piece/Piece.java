
package game.piece;

import game.side.Side;

public class Piece extends AbstractPiece {

    public Piece(final Side side) {
        super(side);
        if (!side.isAPlayer()) {
            throw new IllegalArgumentException("Argument 'side' must be a player.");
        }
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    @Override
    public String toString() {
        return this.getSide() == Side.FIRST_PLAYER ? "x" : "o";
    }

}