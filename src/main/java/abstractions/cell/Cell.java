
package abstractions.cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.API.PositionInterface;
import abstractions.position.RelativePosition;

public class Cell extends AbstractCell {

    private BoardInterface board;

    public Cell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }

    public final void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        //checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
        this.piece = piece;
    }

    public final CellInterface getNext(int rowDelta, int columnDelta) {
        return this.board.getCell(this.getRow() + rowDelta, this.getColumn() + columnDelta);
    }

    public CellInterface getRelative(RelativePosition relativePosition) {
        return this.board.getCell(this.getRow() + relativePosition.getRow(), this.getColumn() + relativePosition.getColumn());
    }

    @Override
    public final boolean isNull() {
        return false;
    }

    public void setBoard(BoardInterface board) {
        this.board = board;
    }

    @Override
    public final boolean isEmpty() {
        return this.getPiece().isNull();
    }

    @Override
    public String toString() {
        return this.willGenerateMutations() ? "(" + this.getPiece() + ")|" : " " + this.getPiece() + " |";
    }

    @Override
    public boolean willGenerateMutations() {
        return this.willGenerateMutations;
    }

    @Override
    public void willGenerateMutations(boolean willItGenerateMutations) {
        this.willGenerateMutations = willItGenerateMutations;
    }

}