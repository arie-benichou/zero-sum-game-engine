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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells;

import java.util.Map;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.positions.PositionsInterface;

/**
 * This is the interface for the game board cells factory.
 */
public interface CellsInterface {

    /**
     * Returns the cell related to a given position.
     * 
     * @param position
     *            a given position
     * 
     * @return the cell related to a given position
     */
    CellInterface cell(final PositionInterface position);

    /**
     * Returns all the cells of the game board.
     * 
     * @return all the cells of the game board
     */
    Map<PositionInterface, CellInterface> getAllCells();

    /**
     * Returns the positions factory.
     * 
     * @return the positions factory
     */
    PositionsInterface getBoardPositionFactory(); // TODO revoir la nécessité de cette méthode

    /**
     * Returns the null cell.
     * 
     * @return the null cell
     */
    CellInterface getNullCell(); // TODO revoir la nécessité de cette méthode

}