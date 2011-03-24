/*
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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.DimensionInterface;

/**
 * This is the interface for the positions factory.
 */
public interface PositionsInterface {

	/**
	 * Returns the board dimension.
	 * 
	 * @return the board dimension
	 */
	DimensionInterface getBoardDimension();

	/**
	 * Returns the number of positions.
	 * 
	 * @return the number of positions
	 */
	int getNumberOfPositions();

	/**
	 * Returns all the positions.
	 * 
	 * @return all the positions
	 */
	PositionInterface[][] getBoardPositions();

	/**
	 * Returns the null position.
	 * 
	 * @return the null position
	 */
	PositionInterface getNullPosition();

	/**
	 * Returns the position related to a given row and a given column.
	 * 
	 * @param clientRowIndex a given row
	 * 
	 * @param clientColumnIndex a given column
	 * 
	 * @return the position related to a given row index and a given column
	 */
	PositionInterface position(final int clientRowIndex, final int clientColumnIndex);

	/**
	 * Returns the left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the left position of a given position
	 */
	PositionInterface leftOf(final PositionInterface position);

	/**
	 * Returns the right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the right position of a given position
	 */
	PositionInterface rightOf(final PositionInterface position);

	/**
	 * Returns the top position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top position of a given position
	 */
	PositionInterface topOf(final PositionInterface position);

	/**
	 * Returns the bottom position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom position of a given position
	 */
	PositionInterface bottomOf(final PositionInterface position);

	/**
	 * Returns the top-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-left position of a given position
	 */
	PositionInterface topLeftOf(final PositionInterface position);

	/**
	 * Returns the top-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-right position of a given position
	 */
	PositionInterface topRightOf(final PositionInterface position);

	/**
	 * Returns the bottom-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-left position of a given position
	 */
	PositionInterface bottomLeftOf(final PositionInterface position);

	/**
	 * Returns the bottom-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-right position of a given position
	 */
	PositionInterface bottomRightOf(final PositionInterface position);

}