
package abstractions.cell;

import abstractions.board.BoardInterface;
import abstractions.piece.PieceSetFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionSetFactory;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

public final class NullCell extends AbstractCell {

    public NullCell() {
        super(PositionSetFactory.NULL_POSITION);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public boolean willGenerateMutations() {
        return false;
    }

    @Override
    public CellInterface getRelative(RelativePositionInterface relativePosition) {
        return this;
    }

    @Override
    public void setBoard(BoardInterface board) {
        //goes into the vacuum...  
    }

    @Override
    public CellInterface setPiece(final PieceInterface piece) {
        //goes into the vacuum...
        return this;
    }

    @Override
    public CellInterface setPiece(SideInterface side, PieceTypeInterface pieceType) {
        //goes into the vacuum...
        return this;
    }

    @Override
    public PieceInterface getPiece() {
        //return NULL_PIECE;
        return null;
    }

    @Override
    public String toString() {
        // returns vacuum ;)
        return "";
    }

    @Override
    public PieceSetFactory getPieceFactory() {
        // TODO à revoir
        return null;
    }

}