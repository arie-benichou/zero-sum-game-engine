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

package game.board;

import static game.dimension.API.*;
import static game.cell.API.*;

import static game.dimension.API.DimensionFactory.*;
import static game.cell.API.CellFactory.*;
import static game.position.API.PositionFactory.*;

import java.util.List;

import com.google.common.collect.Lists;

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
			return String.format(IllegalBoardException.MESSAGE, this.numberOfRows, this.numberOfColumns);
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

		// TODO ? getCellsByColumn()
		// TODO ? getCellsByRow()

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
		public static BoardInterface Board(final int numberOfRows, final int numberOfColumns) {
			try {
				return new Board(Cells(Positions(Dimension(numberOfRows, numberOfColumns))));
			} catch (IllegalDimensionException e) {
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
		public static BoardInterface Clone(final BoardInterface board) {
			final List<CellInterface> cells = Lists.newArrayList();
			for (final CellInterface cell : board) {
				cells.add(CellFactory.Clone(cell));
			}
			return new Board(cells);
		}

	}

}
