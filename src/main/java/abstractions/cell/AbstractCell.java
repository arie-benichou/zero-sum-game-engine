
package abstractions.cell;

import static abstractions.position.API.*;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.side.API.SideInterface;

//TODO à mettre dans API
public abstract class AbstractCell extends PotentialCell {

    private BoardInterface board;

    public AbstractCell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }

    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
        this.piece = piece;
    }

    public final CellInterface getNext(int rowDelta, int columnDelta) {
        return this.board.getCell(this.getRow() + rowDelta, this.getColumn() + columnDelta);
    }

    @Override
    public final boolean isNull() {
        return false;
    }
    
    public void setBoard(BoardInterface board) {
        this.board = board;
    }

    @Override
    public abstract boolean isMutable(SideInterface side); // TODO regarder comment tester une classe abstraite (faire un test qui hérite de la classe ?)

    @Override
    public final boolean isEmpty() {
        return this.getPiece().isNull();
    }

    @Override
    public String toString() {
        return " " + this.getPiece() + " |";

    }
    
}