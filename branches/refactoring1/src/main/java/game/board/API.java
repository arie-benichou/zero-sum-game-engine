
package game.board;

import static game.dimension.API.*;
import static game.cell.API.*;

import static game.dimension.API.DimensionFactory.*;
import static game.cell.API.CellFactory.*;
import static game.position.API.PositionFactory.*;


import java.util.ArrayList;

import com.google.common.collect.Lists;

public class API {
    
    public static class IllegalBoardException extends RuntimeException {
        
        private static final String MESSAGE = "Board(numberOfRows=%d, numberOfColumns=%d) is not a legal Board.";
        
        private static final long serialVersionUID = 1L;
        
        private int numberOfRows;
        private int numberOfColumns;
        
        public IllegalBoardException(final int numberOfRows, final int numberOfColumns) {
            super();
            this.numberOfRows = numberOfRows;
            this.numberOfColumns = numberOfColumns;
        }
        
        @Override
        public String getMessage() {
            return String.format(IllegalBoardException.MESSAGE, this.numberOfRows, this.numberOfColumns);
        }               
                
    }    

    /**
     * This is the interface for a game board.
     */
    public static interface BoardInterface extends Iterable<CellInterface> {

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
        
        CellInterface getLowerBound();
        
        CellInterface getUpperBound();
        
     // TODO ? getCellsByColumn()
     // TODO ? getCellsByRow()

    }
    
    public static final class BoardFactory {

        public static BoardInterface Board(final int numberOfRows, final int numberOfColumns) {
            try {            
                return new Board(Cells(Positions(Dimension(numberOfRows, numberOfColumns))));
            }
            catch (IllegalDimensionException e) {
                throw new IllegalBoardException(numberOfRows, numberOfColumns);
            }
        }
        
        public static BoardInterface Clone(final BoardInterface board) {
            final ArrayList<CellInterface> cells = Lists.newArrayList();
            for (final  CellInterface cell : board) {
                cells.add(CellFactory.Clone(cell));
            }
            return new Board(cells);
        }        

    }    

}
