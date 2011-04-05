
package abstractions.cell;

import abstractions.board.BoardInterface;
import abstractions.piece.PieceFactory;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.piece.Pieces;
import abstractions.position.Positions;
import abstractions.position.RelativePositionInterface;
import abstractions.side.SideInterface;

public final class NullCell extends AbstractCell {
    
    private final static PieceInterface NULL_PIECE = PiecesAlphabetWithoutNullPiece.NULL_PIECE;

    public NullCell() {
        super(Positions.NULL_POSITION);
    }

    @Override
    public final boolean isNull() {
        return true;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
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
    public final void setPiece(final PieceInterface piece) {
        //goes into the vacuum...
    }
    
    @Override
    public void setPiece(SideInterface side, PieceTypeInterface pieceType) {
        //goes into the vacuum...
    }
    
    @Override
    public PieceInterface getPiece() {
        return NULL_PIECE;
    }    
        
    @Override
    public String toString() {
        // returns vacuum ;)
        return "";
    }

    @Override
    public PieceFactory getPieceFactory() {
        // TODO à revoir
        return null;
    }

}