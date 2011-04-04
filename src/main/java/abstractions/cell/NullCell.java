
package abstractions.cell;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

final class NullCell extends AbstractCell {
    
    private final static PieceInterface NULL_PIECE = abstractions.piece.API.NULL_PIECE;

    public NullCell() {
        super(abstractions.position.API.NULL_POSITION);
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
    public CellInterface getRelative(RelativePosition relativePosition) {
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

}