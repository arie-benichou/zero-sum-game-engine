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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;

/**
 * This is the interface for a game board.
 */
public interface BoardInterface extends Iterable<CellInterface[]> {

	/**
	 * Returns the cell related to a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the cell related to a given position
	 */
	CellInterface getCell(final PositionInterface position);

	/**
	 * Returns the cell related to a given position.
	 * 
	 * @param position a given position
	 * 
	 * @return the cell related to a given position
	 */
	CellInterface getCell(final int clientRowIndex, final int clientColumnIndex);

}