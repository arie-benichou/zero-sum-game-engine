
package abstractions.cell.old;

import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public final class NullCell extends AbstractCell {

    @Override
    public boolean willGenerateMutations() {
        return false;
    }

    @Override
    public CellInterface getRelative(final RelativePositionInterface relativePosition) {
        return this;
    }

    @Override
    public CellInterface setPiece(final PieceInterface piece) {
        //goes into the vacuum...
        return this;
    }

    @Override
    public CellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType) {
        //goes into the vacuum...
        return this;
    }

    @Override
    public String toString() {
        // returns vacuum ;)
        return "";
    }

}