
package abstractions.cell;

import static com.google.common.base.Preconditions.checkArgument;
import abstractions.board.BoardInterface;
import abstractions.piece.PieceSetFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

// TODO enlever la visibilité publique
public final class Cell extends AbstractCell {

    private BoardInterface board;

    private transient PieceInterface piece; //= this.board.getPieceFactory().getNullPiece();

    public Cell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }

    public CellInterface setPiece(final PieceInterface piece) {
        //checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        //checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
        this.piece = piece;
        return this;
    }

    public CellInterface setPiece(SideInterface side, PieceTypeInterface pieceType) {
        this.setPiece(this.board.getPieceFactory().Piece(side, pieceType));
        return this;
    }

    public PieceInterface getPiece() {
        return this.piece;
    }

    public CellInterface getRelative(RelativePositionInterface relativePosition) {
        return this.board.getCell(relativePosition.computeRow(this.getRow()), relativePosition.computeColumn(this.getColumn()));
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public void setBoard(BoardInterface board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return this.willGenerateMutations() ? "(" + this.getPiece() + ")|" : " " + this.getPiece() + " |";
    }

    @Override
    public PieceSetFactory getPieceFactory() {
        return this.board.getPieceFactory();
    }

}