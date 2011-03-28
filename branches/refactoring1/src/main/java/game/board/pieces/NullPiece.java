
package game.board.pieces;

import opponents.Side;

final class NullPiece extends AbstractPiece {

    public NullPiece() {
        super(Side.NO_ONE);
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