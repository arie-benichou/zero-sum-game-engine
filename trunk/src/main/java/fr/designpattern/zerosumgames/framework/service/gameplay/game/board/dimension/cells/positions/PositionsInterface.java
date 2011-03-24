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
 * This is the interface for the factory of game board positions.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
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
	
	
	PositionInterface getNullPosition();

	/**
	 * Returns a position for a given client row index and a given client column
	 * index, returns null if the position does not exist.
	 * 
	 * @param clientRowIndex the client row index
	 * 
	 * @param clientColumnIndex the client column index
	 * 
	 * @return a position for a given client row index and a given client column
	 * index, returns null if the position does not exist
	 */
	PositionInterface position(int clientRowIndex, int clientColumnIndex);

	/**
	 * Returns the left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the left position of a given position
	 */
	PositionInterface leftOf(PositionInterface position);

	/**
	 * Returns the right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the right position of a given position
	 */	
	PositionInterface rightOf(PositionInterface position);
	
	/**
	 * Returns the top position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top position of a given position
	 */	
	PositionInterface topOf(PositionInterface position);

	/**
	 * Returns the bottom position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom position of a given position
	 */	
	PositionInterface bottomOf(PositionInterface position);

	/**
	 * Returns the top-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-left position of a given position
	 */
	PositionInterface topLeftOf(PositionInterface position);

	/**
	 * Returns the top-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-right position of a given position
	 */	
	PositionInterface topRightOf(PositionInterface position);

	/**
	 * Returns the bottom-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-left position of a given position
	 */	
	PositionInterface bottomLeftOf(PositionInterface position);

	/**
	 * Returns the bottom-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-right position of a given position
	 */	
	PositionInterface bottomRightOf(PositionInterface position);

}