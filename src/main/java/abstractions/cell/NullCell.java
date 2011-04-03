
package abstractions.cell;

import static abstractions.position.API.NULL_POSITION;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import abstractions.board.API.BoardInterface;
import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.API.PieceInterface;
import abstractions.position.RelativePosition;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;

final class NullCell extends AbstractCell {

    public NullCell() {
        super(NULL_POSITION);
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
    public void willGenerateMutations(boolean willItGenerateMutations) {
        //goes into the vacuum...
    }
    
    @Override
    public String toString() {
        return "";
    }    

}