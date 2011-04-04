package abstractions.board;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static abstractions.position.API.PositionFactory.Positions;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import abstractions.board.API.BoardInterface;
import abstractions.board.API.IllegalBoardException;
import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCellFactory;
import abstractions.dimension.API.IllegalDimensionException;

/**
 * The board factory.
 */
@Deprecated
public final class _BoardFactory {
    
    private AbstractCellFactory cellFactory;

    public BoardFactory(Class<? extends CellInterface> cellClass) {
        this.cellFactory = new AbstractCellFactory(cellClass);
    }

    /**
     * Returns a new instance of a board for a given number of rows and a
     * given number of columns.
     * 
     * @param numberOfRows
     *            the number of rows for the new board
     * @param numberOfColumns
     *            the number of columns for the new board
     * @return a new instance of a board for a given number of rows and a
     *         given number of columns
     */
    public final BoardInterface Board(final int numberOfRows, final int numberOfColumns) {
        try {
            return new Board(this.cellFactory.cells(Positions(Dimension(numberOfRows, numberOfColumns))));
        }
        catch (IllegalDimensionException e) {
            throw new IllegalBoardException(numberOfRows, numberOfColumns);
        }
    }

    /**
     * Returns a clone of an existing instance of a board.
     * 
     * @param board
     *            the board to clone.
     * 
     * @return a clone of an existing instance of a board
     */
    public final BoardInterface Clone(final BoardInterface board) {
        checkNotNull(board, "Argument 'board' is not intended to be null.");
        final Set<CellInterface> cells = new HashSet<CellInterface>();
        for (final CellInterface cell : board) {
            cells.add(this.cellFactory.clone(cell));
        }
        return new Board(cells);
    }

}