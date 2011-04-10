
package abstractions.board;

import java.util.Set;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceSetFactory;
import abstractions.side.SideInterface;

/**
 * This is the interface for a board.
 */
public interface BoardInterface extends Iterable<ManagedCellInterface> {

    /**
     * Returns the cell related to a given row and a given column.
     * 
     * @param clientRowIndex
     *            a given row
     * 
     * @param clientColumnIndex
     *            a given column
     * 
     * @return the cell related to a given row index and a given column
     */
    ManagedCellInterface getCell(final int clientRowIndex, final int clientColumnIndex);

    /**
     * Returns the lower bound of this board.
     * 
     * @return the lower bound of this board
     */
    ManagedCellInterface getLowerBound();

    /**
     * Returns the upper bound of this board.
     * 
     * @return the upper bound of this board
     */
    ManagedCellInterface getUpperBound();

    //TODO ! javadoc
    Set<MutationInterface> getLegalMutations(SideInterface side);

    // TODO ? utiliser une interface
    PieceSetFactory getPieceFactory();

    //TODO ! javadoc
    void injectPieceFactory(PieceSetFactory pieceFactory);

}
