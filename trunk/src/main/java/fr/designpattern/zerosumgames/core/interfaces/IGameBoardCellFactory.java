/*
 * @(#)IGameBoardCellFactory.java	0.99
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

package fr.designpattern.zerosumgames.core.interfaces;

import java.util.Map;

/**
 * This is the interface for the factory of game board cells.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGameBoardCellFactory {

	/**
	 * Returns the corresponding cell for a given position.
	 * 
	 * @param position the position
	 * 
	 * @return the corresponding cell for a given position
	 */
	IGameBoardCell cell(IGameBoardPosition position);

	/**
	 * Returns all the cells of the game board.
	 * 
	 * @return all the cells of the game board
	 */
	Map<IGameBoardPosition, IGameBoardCell> getGameBoardCells();

	/**
	 * Returns the factory of positions.
	 * 
	 * TODO revoir la necessité de cette méthode.
	 * 
	 * @return the factory of positions
	 */
	IGameBoardPositionFactory getBoardPositionFactory();
	
	IGameBoardCell getNullCell();

}