/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.board;

import static abstractions.cell.API.*;
import static abstractions.cell.API.CellFactory.*;
import static abstractions.dimension.API.*;
import static abstractions.dimension.API.DimensionFactory.*;
import static abstractions.position.API.PositionFactory.*;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashSet;
import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.AbstractCellFactory;
import abstractions.side.API.SideInterface;

/**
 * API related to boards.
 */
public class API {

    /**
     * Class for illegal board exceptions.
     */
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
            return String.format(MESSAGE, this.numberOfRows, this.numberOfColumns);
        }

    }

    /**
     * This is the interface for a board.
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

        /**
         * Returns the top-neighbour cell of this cell.
         * 
         * @return the top-neighbour cell of this cell
         */
        // -------------
        // |   | x |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------
        //CellInterface topOf(CellInterface cell);

        /**
         * Returns the right-neighbour cell of this cell.
         * 
         * @return the right-neighbour cell of this cell
         */
        // -------------
        // |   |   |   |
        // -------------
        // |   | . | x |
        // -------------
        // |   |   |   |
        // -------------
        //CellInterface rightOf(CellInterface cell);

        /**
         * Returns the bottom-neighbour cell of this cell.
         * 
         * @return the bottom-neighbour cell of this cell
         */
        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   | x |   |
        // -------------
        //CellInterface bottomOf(CellInterface cell);

        /**
         * Returns the left-neighbour cell of this cell.
         * 
         * @return the left-neighbour cell of this cell
         */
        // -------------
        // |   |   |   |
        // -------------
        // | x | . |   |
        // -------------
        // |   |   |   |
        // -------------
        //CellInterface leftOf(CellInterface cell);

        /**
         * Returns the top-right-neighbour cell of this cell.
         * 
         * @return the top-right-neighbour cell of this cell
         */
        // -------------
        // |   |   | x |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------
        //CellInterface topRightOf(CellInterface cell);

        /**
         * Returns the top-left-neighbour cell of this cell.
         * 
         * @return the top-left-neighbour cell of this cell
         */
        // -------------
        // | x |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   |   |
        // -------------
        //CellInterface topLeftOf(CellInterface cell);

        /**
         * Returns the bottom-right-neighbour cell of this cell.
         * 
         * @return the bottom-right-neighbour cell of this cell
         */
        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // |   |   | x |
        // -------------
        //CellInterface bottomRightOf(CellInterface cell);

        /**
         * Returns the bottom-left-neighbour cell of this cell.
         * 
         * @return the bottom-left-neighbour cell of this cell
         */
        // -------------
        // |   |   |   |
        // -------------
        // |   | . |   |
        // -------------
        // | x |   |   |
        // -------------
        //CellInterface bottomLeftOf(CellInterface cell);
        
        public Set<CellInterface> getMutableCells(SideInterface side);
        
    }

    /**
     * The board factory.
     */
    public static final class BoardFactory {

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
        /*
        public static BoardInterface Board(final int numberOfRows, final int numberOfColumns) {
            try {
                return new Board(Cells(Positions(Dimension(numberOfRows, numberOfColumns))));
            }
            catch (IllegalDimensionException e) {
                throw new IllegalBoardException(numberOfRows, numberOfColumns);
            }
        }
        */
        
        /*
        public static BoardInterface Board(Class<? extends CellInterface> CellClass, final int numberOfRows, final int numberOfColumns) {
            // TODO avoir une instance de la BoardFactory à laquelle une instance de la CellFactory est injectée
            AbstractCellFactory cellFactory = new AbstractCellFactory(CellClass);
            try {
                return new Board(cellFactory.cells(Positions(Dimension(numberOfRows, numberOfColumns))));
            }
            catch (IllegalDimensionException e) {
                throw new IllegalBoardException(numberOfRows, numberOfColumns);
            }
        }
        */
        
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
            // TODO avoir une instance de la BoardFactory à laquelle une instance de la CellFactory est injectée
            AbstractCellFactory cellFactory = new AbstractCellFactory(CellClass);
            checkNotNull(board, "Argument 'board' is not intended to be null.");
            final Set<CellInterface> cells = new HashSet<CellInterface>();
            for (final CellInterface cell : board) {
                cells.add(this.clone(cell));
            }
            return new Board(cells);
        }
        */
    }

}
