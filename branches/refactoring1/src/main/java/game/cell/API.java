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

package game.cell;

import static game.piece.API.*;
import static game.position.API.*;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * API related to cells.
 */
public final class API {

	/**
	 * The null object for a cell.
	 */
	public final static CellInterface NULL_CELL = new NullCell();
	
	// TODO IllegalCellException

	/**
	 * This is the interface for a cell.
	 */
	public static interface CellInterface extends Comparable<CellInterface> {

		/**
		 * Returns the position of this cell.
		 * 
		 * @return the position of this cell
		 */
		PositionInterface getPosition();

		/**
		 * Returns the row for this cell.
		 * 
		 * @return the row for this cell
		 */
		int getRow();

		/**
		 * Returns the column for this cell.
		 * 
		 * @return the column for this cell
		 */
		int getColumn();

		/**
		 * Returns true if this cell is empty.
		 * 
		 * @return true if this cell is empty
		 */
		boolean isEmpty();

		/**
		 * Returns the piece contained by this cell.
		 * 
		 * @return the piece contained by this cell
		 */
		PieceInterface getPiece();

		/**
		 * Assigns a piece to this cell.
		 * 
		 * @param piece
		 *            the piece to be contained by this cell
		 */
		void setPiece(final PieceInterface piece);

		/**
		 * Returns true if this cell is the null object, false otherwise.
		 * 
		 * @return true if this cell is the null object, false otherwise
		 */
		boolean isNull();

	}

	/**
	 * The cell factory.
	 */
	public static final class CellFactory {

		/**
		 * Returns the null cell.
		 * 
		 * @return the null cell
		 */
		public static CellInterface NullCell() {
			return NULL_CELL;
		}

		/**
		 * Returns a new instance of a cell related to a given position.
		 * 
		 * @param position
		 *            a legal position
		 * 
		 * @return a new instance of a cell related to a given position
		 */
		public static final CellInterface Cell(final PositionInterface position) {
			return new Cell(position);
		}

		/**
		 * Returns a clone of a cell.
		 * 
		 * @param cellToClone
		 *            the cell to clone.
		 * 
		 * @return a clone of a cell
		 */
		public static final CellInterface Clone(final CellInterface cellToClone) {
			final CellInterface clone = CellFactory.Cell(cellToClone.getPosition());
			clone.setPiece(cellToClone.getPiece());
			return clone;
		}

		/**
		 * Returns a list of new cells relateds to a given list of positions.
		 * 
		 * @param positions
		 *            the legal positions
		 * 
		 * @return a list of new cells relateds to a given list of positions
		 */
		public static final List<CellInterface> Cells(final List<PositionInterface> positions) {
			final List<CellInterface> cells = Lists.newArrayListWithExpectedSize(positions.size());
			for (final PositionInterface position : positions) {
				cells.add(CellFactory.Cell(position));
			}
			return Collections.unmodifiableList(cells);
		}

	}

}