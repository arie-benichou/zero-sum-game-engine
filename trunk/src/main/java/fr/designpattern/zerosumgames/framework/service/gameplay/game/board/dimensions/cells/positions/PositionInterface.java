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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.positions;

import fr.designpattern.zerosumgames.util.NullObjectAvailableInterface;

/**
 * This is the interface for a game board position.
 */
public interface _PositionInterface extends NullObjectAvailableInterface {

    /**
     * Returns the client column index.
     * 
     * @return the client column index
     */
    int getColumn();

    /**
     * Returns the client row index.
     * 
     * @return the client row index
     */
    int getRow();

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