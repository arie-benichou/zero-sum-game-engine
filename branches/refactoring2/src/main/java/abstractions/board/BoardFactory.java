package abstractions.board;

import static abstractions.dimension.API.DimensionFactory.Dimension;
import static abstractions.position.API.PositionFactory.Positions;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import TicTacToe.TicTacToeCell;
import abstractions.board.API.BoardInterface;
import abstractions.board.API.IllegalBoardException;
import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCellFactory;
import abstractions.dimension.API.IllegalDimensionException;
import abstractions.piece.API.PieceFactory;

/**
 * The board factory.
 */
// TODO implémenter une interface
// TODO utiliser un cache des nouvelles cellules créées 
public final class BoardFactory {
    
    private AbstractCellFactory cellFactory;

    public BoardFactory(Class<? extends CellInterface> CellClass) {
        this.cellFactory = new AbstractCellFactory(CellClass);
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
    
    public static void main(String[] args) {
        BoardFactory bf = new BoardFactory(TicTacToeCell.class);
        BoardInterface board = bf.Board(10, 10);
        
        board.getCell(1, 1).setPiece(PieceFactory.Piece(abstractions.side.API.FIRST_SIDE));
        board.getCell(5, 5).setPiece(PieceFactory.Piece(abstractions.side.API.FIRST_SIDE));
        board.getCell(10, 10).setPiece(PieceFactory.Piece(abstractions.side.API.FIRST_SIDE));
        
        System.out.println(board);
        
        for(CellInterface cell : board) {
            if(!cell.isMutable()) {
                System.out.println("La cellule [" + cell.getRow() + "]" + "[" + cell.getColumn() + "]" + " n'est pas mutable.");
            }
        }
        
        
    }

}