
package abstractions.piece;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

/**
 * This is the interface for a piece.
 */
public interface PieceInterface {

    /**
     * Returns the side related to this piece.
     * 
     * @return the side related to this piece
     */
    SideInterface getSide();

    /**
     * Returns true if this piece is the null object, false otherwise.
     * 
     * @return true if this piece is the null object, false otherwise
     */
    boolean isNull();

    Set<? extends MutationInterface> computeAvailableMutations(final CellInterface cell, SideInterface side);
    
    //PieceFactory getPieceFactory();

}