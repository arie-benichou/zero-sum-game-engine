
package abstractions.cell;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import abstractions.piece.PieceFactory;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionInterface;

import com.google.common.collect.Sets;

/**
 * The cell factory.
 */
public final class CellFactory {

    private PieceFactory pieceFactory;

    public CellFactory(PieceFactory pieceFactory) {
        this.pieceFactory = pieceFactory;
    }

    /**
     * Returns the null cell.
     * 
     * @return the null cell
     */
    public CellInterface getNullCell() {
        return CellInterface.NULL_CELL;
    }

    /**
     * Returns a new instance of a cell related to a given position.
     * 
     * @param position
     *            a legal position
     * 
     * @return a new instance of a cell related to a given position
     */
    public final CellInterface cell(final PositionInterface position) {
        checkNotNull(position, "Argument 'postion' is not intended to be null.");
        return new Cell(position);
    }

    /**
     * Returns a clone of a cell.
     * 
     * @param cell
     *            the cell to clone.
     * 
     * @return a clone of a cell
     */
    public final CellInterface clone(final CellInterface cell) {
        checkNotNull(cell, "Argument 'cell' is not intended to be null.");
        final CellInterface clone = this.cell(cell.getPosition());
        PieceInterface piece = cell.getPiece();
        clone.setPiece(piece);
        return clone;
    }

    /**
     * Returns a set of new cells for a given set of positions.
     * 
     * @param positions
     *            the legal positions
     * 
     * @return a set of new cells for a given set of positions
     */
    public final Set<CellInterface> cells(final Set<PositionInterface> positions) {
        checkNotNull(positions, "Argument 'positions' is not intended to be null.");
        final Set<CellInterface> cells = Sets.newHashSetWithExpectedSize(positions.size());
        for (final PositionInterface position : positions) {
            CellInterface cell = this.cell(position);
            cell.setPiece(this.pieceFactory.NullPiece());
            cells.add(cell);
        }
        return Collections.unmodifiableSet(cells);
    }
}