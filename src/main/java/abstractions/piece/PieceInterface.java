
package abstractions.piece;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
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
     * Returns the type related to this piece.
     * 
     * @return the type related to this piece
     */
    PieceTypeInterface getType();

    /**
     * Returns the potential cell mutations induced by the piece (see a piece as
     * a kind of cell kernel).
     * 
     * @param cell
     *            the cell concerned by these mutations.
     * 
     * @param side
     *            the side to play
     * 
     * @return the potential cell mutations induced by the piece (see a piece as
     *         a kind of cell kernel)
     */
    Set<? extends MutationInterface> computePotentialMutations(final ManagedCellInterface cell, SideInterface side);

    // TODO ? add method isNull() to a piece interface    

}