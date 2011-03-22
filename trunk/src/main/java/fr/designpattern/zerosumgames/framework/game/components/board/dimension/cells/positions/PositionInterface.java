/*
 * @(#)IGameBoardPosition.java	0.99
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

package fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.positions;

import fr.designpattern.zerosumgames.framework.game.components.NullableInterface;

/**
 * This is the interface for a game board position.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface PositionInterface extends NullableInterface {

	/**
	 * Returns the client column index.
	 * 
	 * @return the client column index
	 */
	int getClientColumnIndex();

	/**
	 * Returns the client row index.
	 * 
	 * @return the client row index
	 */	
	int getClientRowIndex();

	/**
	 * Returns the internal column index.
	 * 
	 * @return the internal column index
	 */	
	int getInternalColumnIndex();

	/**
	 * Returns the internal row index.
	 * 
	 * @return the internal row index
	 */		
	int getInternalRowIndex();

}