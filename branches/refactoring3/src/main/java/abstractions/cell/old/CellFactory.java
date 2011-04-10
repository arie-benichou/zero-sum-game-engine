
package abstractions.cell.old;

import abstractions.piece.PieceInterface;

import com.google.common.base.Preconditions;

/**
 * The cell factory.
 */
public final class CellFactory {

    /**
     * Returns a clone of a cell.
     * 
     * @param cell
     *            the cell to clone.
     * 
     * @return a clone of a cell
     */
    public final ManagedCellInterface clone(final ManagedCellInterface cell) {
        Preconditions.checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        final ManagedCellInterface clone = this.cell(cell.getPosition());
        final PieceInterface piece = cell.getPiece();
        clone.setPiece(piece);
        return clone;
    }

}