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

package abstractions.dimension;

/**
 * This is the interface for the dimension.
 */
public interface DimensionManagerInterface {

    /**
     * Returns the first row index.
     * 
     * @return the first row index
     */
    int lowerBoundForRows();

    /**
     * Returns the last row index.
     * 
     * @return the last row index
     */
    int upperBoundForRows();

    /**
     * Returns the first column index.
     * 
     * @return the first column index
     */
    int lowerBoundForColumns();

    /**
     * Returns the last column index.
     * 
     * @return the last column index
     */
    int upperBoundForColumns();

    /**
     * Returns the number of rows.
     * 
     * @return the number of rows
     */
    int numberOfRows();

    /**
     * Returns the number of columns.
     * 
     * @return the number of columns
     */
    int numberOfColumns();

    /**
     * Returns the capacity of a board with this dimension.
     * 
     * @return the capacity of a board with this dimension
     */
    int capacity();

    /**
     * Returns true if the row index and column index are contained in these
     * board dimension, false otherwise.
     * 
     * @param rowIndex
     *            a given row index
     * 
     * @param columnIndex
     *            a given column index
     * 
     * @return true if the row index and column index are contained in these
     *         board dimension, false otherwise
     */
    boolean contains(final int rowIndex, final int columnIndex);

}