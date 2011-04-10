
package abstractions.piece;

import java.util.Set;

import abstractions.cell.old.CellInterface;
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
     * Returns the type related to this piece.
     * 
     * @return the type related to this piece
     */
    PieceTypeInterface getType();

    Set<? extends PieceActionTypeInterface> computePotentialActionTypes(final CellInterface cell, SideInterface side);

}