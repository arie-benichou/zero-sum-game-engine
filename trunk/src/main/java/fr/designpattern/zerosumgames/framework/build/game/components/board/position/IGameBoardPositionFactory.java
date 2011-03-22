/*
 * @(#)IGameBoardPositionFactory.java	0.99
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

package fr.designpattern.zerosumgames.framework.build.game.components.board.position;

import fr.designpattern.zerosumgames.framework.build.game.components.board.dimension.IGameBoardDimension;

/**
 * This is the interface for the factory of game board positions.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGameBoardPositionFactory {

	/**
	 * Returns the board dimension.
	 * 
	 * @return the board dimension
	 */
	IGameBoardDimension getBoardDimension();

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
	IGameBoardPosition[][] getBoardPositions();
	
	
	IGameBoardPosition getNullPosition();

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
	IGameBoardPosition position(int clientRowIndex, int clientColumnIndex);

	/**
	 * Returns the left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the left position of a given position
	 */
	IGameBoardPosition leftOf(IGameBoardPosition position);

	/**
	 * Returns the right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the right position of a given position
	 */	
	IGameBoardPosition rightOf(IGameBoardPosition position);
	
	/**
	 * Returns the top position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top position of a given position
	 */	
	IGameBoardPosition topOf(IGameBoardPosition position);

	/**
	 * Returns the bottom position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom position of a given position
	 */	
	IGameBoardPosition bottomOf(IGameBoardPosition position);

	/**
	 * Returns the top-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-left position of a given position
	 */
	IGameBoardPosition topLeftOf(IGameBoardPosition position);

	/**
	 * Returns the top-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the top-right position of a given position
	 */	
	IGameBoardPosition topRightOf(IGameBoardPosition position);

	/**
	 * Returns the bottom-left position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-left position of a given position
	 */	
	IGameBoardPosition bottomLeftOf(IGameBoardPosition position);

	/**
	 * Returns the bottom-right position of a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the bottom-right position of a given position
	 */	
	IGameBoardPosition bottomRightOf(IGameBoardPosition position);

}