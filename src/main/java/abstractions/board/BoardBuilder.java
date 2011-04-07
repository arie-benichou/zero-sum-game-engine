
package abstractions.board;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

import abstractions.cell.CellFactory;
import abstractions.cell.CellInterface;
import abstractions.dimension.API.DimensionInterface;
import abstractions.dimension.API.IllegalDimensionException;
import abstractions.piece.PieceFactory;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.absolute.AbsolutePositionInterface;
import abstractions.position.absolute.AbsolutePositions;

public class BoardBuilder {

    @SuppressWarnings("rawtypes")
    private Class pieces;

    private DimensionInterface dimension;

    private AbsolutePositions positionFactory;

    private CellFactory cellFactory;

    public <T extends Enum<T> & PieceTypeInterface> BoardBuilder(Class<T> pieces, DimensionInterface dimension) {
        this.dimension = dimension;
        this.pieces = pieces;
    }

    public <T extends Enum<T> & PieceTypeInterface> BoardBuilder(Class<T> pieces, int numberOfRows, int numberOfColumns) {
        try {
            // TODO ? différer la construction au build
            this.dimension = Dimension(numberOfRows, numberOfColumns);
        }
        catch (IllegalDimensionException e) {
            // TODO ? utiliser une IllegalStateException
            throw new IllegalBoardException(numberOfRows, numberOfColumns);
        }
        this.pieces = pieces;
    }

    // TODO créer une interface pour la positionFactory
    public BoardBuilder positionFactory(AbsolutePositions positionFactory) {
        this.positionFactory = positionFactory;
        return this;
    }

    // TODO ? créer une interface pour la cellFactory
    public BoardBuilder cellFactory(CellFactory cellfactory) {
        this.cellFactory = cellfactory;
        return this;
    }

    /**
     * Returns a new instance of a board for a given number of rows and a given
     * number of columns.
     * 
     * @param numberOfRows
     *            the number of rows for the new board
     * @param numberOfColumns
     *            the number of columns for the new board
     * @return a new instance of a board for a given number of rows and a given
     *         number of columns
     */
    @SuppressWarnings("unchecked")
    public BoardInterface build() {

        // Il doit être possible de fournir une autre factory de positions,
        // en attendant de pouvoir gérer d'autres dimensions que des quadrilatères
        if (this.positionFactory == null) {
            this.positionFactory = AbsolutePositions.getInstance();
        }
        Set<AbsolutePositionInterface> positions = this.positionFactory.getAllPositions(this.dimension);

        PieceFactory pieceFactory = new PieceFactory(this.pieces);

        if (this.cellFactory == null) {
            this.cellFactory = new CellFactory(pieceFactory);
        }

        Set<CellInterface> cells = Sets.newHashSetWithExpectedSize(positions.size());
        for (final AbsolutePositionInterface position : positions) {
            CellInterface cell = this.cellFactory.cell(position)
                .setPiece(pieceFactory.NullPiece()
                        
            );
            cells.add(cell);
        }

        Board board = new Board(Collections.unmodifiableSet(cells));

        // TODO ? utiliser la pieceFactory injectée dans la cellFactory, ou bien injecter la cellFactory au board
        board.injectPieceFactory(pieceFactory);

        return board;

    }

    /**
     * Returns a clone of an existing instance of a board.
     * 
     * @param board
     *            the board to clone.
     * 
     * @return a clone of an existing instance of a board
     */
    /*
    public static BoardInterface Clone(final BoardInterface board) {
        checkNotNull(board, "Argument 'board' is not intended to be null.");
        final Set<CellInterface> cells = new HashSet<CellInterface>();
        for (final CellInterface cell : board) {
            cells.add(CellFactory.clone(cell));
        }
        return new Board(cells);
    }
    */

}