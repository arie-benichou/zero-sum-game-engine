
package abstractions.cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.board.BoardInterface;
import abstractions.piece.PieceFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.RelativePositionInterface;
import abstractions.side.SideInterface;

//TODO enlever la visibilité publique
public final class Cell extends AbstractCell {

    private BoardInterface board;
    
    private transient PieceInterface piece; //= this.board.getPieceFactory().getNullPiece();

    public Cell(final PositionInterface position) {
        super(position);
        checkArgument(!position.isNull(), "Argument 'position' is not intended to be the null position object");
    }

    public void setPiece(final PieceInterface piece) {
        checkNotNull(piece, "Argument 'piece' is not intended to be null.");
        //checkArgument(!piece.isNull(), "Argument 'piece' is not intended to be the null piece object.");
        this.piece = piece;
    }

    public void setPiece(SideInterface side, PieceTypeInterface pieceType) {
        this.setPiece(this.board.getPieceFactory().Piece(side, pieceType));
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
    public boolean isEmpty() {
        return this.getPiece().isNull();
    }

    @Override
    public String toString() {
        return this.willGenerateMutations() ? "(" + this.getPiece() + ")|" : " " + this.getPiece() + " |";
    }

    @Override
    public PieceFactory getPieceFactory() {
        return this.board.getPieceFactory();
    }

}