
package abstractions.board;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.piece.PieceFactory;
import abstractions.side.SideInterface;

/**
 * This is the interface for a board.
 */
public interface BoardInterface extends Iterable<CellInterface> {

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
    CellInterface getCell(final int clientRowIndex, final int clientColumnIndex);

    /**
     * Returns the lower bound of this board.
     * 
     * @return the lower bound of this board
     */
    CellInterface getLowerBound();

    /**
     * Returns the upper bound of this board.
     * 
     * @return the upper bound of this board
     */
    CellInterface getUpperBound();

    //TODO ! javadoc
    List<MutationInterface> getLegalMutations(SideInterface side);

    // TODO ? utiliser une interface
    PieceFactory getPieceFactory();

    //TODO ! javadoc
    void injectPieceFactory(PieceFactory pieceFactory);

}
