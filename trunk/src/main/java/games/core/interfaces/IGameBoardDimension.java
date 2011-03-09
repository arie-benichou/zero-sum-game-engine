/*
 * @(#)IGameBoardDimension.java	0.99
 *
 * Copyright 2011 Arie Benichou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.    
 */

package main.java.core.interfaces;

/**
 * This is the interface for the dimension of the game board.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGameBoardDimension {

	/**
	 * Returns the first row index.
	 * 
	 * @return the first row index
	 */
	int getMinRowIndex();

	/**
	 * Returns the first column index.
	 * 
	 * @return the first column index
	 */
	int getMinColumnIndex();

	/**
	 * Returns the last row index.
	 * 
	 * @return the last row index
	 */
	int getMaxRowIndex();

	/**
	 * Returns the last column index.
	 * 
	 * @return the last column index
	 */
	int getMaxColumnIndex();

	/**
	 * Returns the size of the row indexes range (the number of rows).
	 * 
	 * @return the size of the row indexes range (the number of rows)
	 */
	int getRowIndexRangeSize();

	/**
	 * Returns the size of the column indexes range (the number of columns).
	 * 
	 * @return the size of the column indexes range (the number of columns)
	 */
	int getColumnIndexRangeSize();

	/**
	 * Returns the capacity of this board dimension.
	 * 
	 * @return the capacity of this board dimension
	 */
	int getCapacity();

	/**
	 * Returns <tt>true</tt> if the row index and column index are contained in
	 * this board dimension.
	 * 
	 * @return <tt>true</tt> if the row index and column index are contained in
	 * this board dimension.
	 */
	boolean contains(int clientRowIndex, int clientColumnIndex);

}