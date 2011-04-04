
package abstractions.cell;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.API.PositionInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

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
        this.setPiece(this.board.getPieceFactory().getPiece(side, pieceType));
    }
    
    public PieceInterface getPiece() {
        return this.piece;
    }    

    public CellInterface getRelative(RelativePosition relativePosition) {
        return this.board.getCell(this.getRow() + relativePosition.getRow(), this.getColumn() + relativePosition.getColumn());
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



}