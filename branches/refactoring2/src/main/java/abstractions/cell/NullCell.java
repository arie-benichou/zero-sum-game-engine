
package abstractions.cell;

import static abstractions.position.API.*;
import static com.google.common.base.Preconditions.*;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

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
    public boolean isMutable(SideInterface side) {
        return false;
    }

    @Override
    public void setBoard(BoardInterface board) {
        //no root  
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public CellInterface getNext(int rowDelta, int columnDelta) {
         return this;
    }

    public CellInterface getRelative(RelativePosition relativePosition) {
        return this;
    }

}