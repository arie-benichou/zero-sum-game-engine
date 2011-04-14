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

package abstractions.position;

import java.util.List;

import abstractions.direction.DirectionInterface;

public interface PositionManagerInterface extends Iterable<PositionInterface> {

    /**
     * Returns the null position.
     * 
     * @return the null position
     */
    PositionInterface getNullPosition();

    /**
     * Returns the position related to a couple of (row index, column index).
     * 
     * @param rowIndex
     *            a given row index
     * 
     * @param columnIndex
     *            a given column index
     * 
     * @return the position related to a couple of (row index, column index)
     */
    PositionInterface getPosition(int rowIndex, int columnIndex);

    /**
     * Returns the position related to a couple of (position, direction)
     * 
     * @param position
     *            a given position
     * 
     * @param direction
     *            a given direction
     * 
     * @return the position related to a couple of (position, direction)
     */
    PositionInterface getPosition(PositionInterface position, DirectionInterface direction);

    /**
     * Returns the 8 named directions in this order : TOP, TOP_RIGHT, RIGHT,
     * BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, TOP_LEFT.
     * 
     * @return the 8 named directions in this order : TOP, TOP_RIGHT,
     *         RIGHT,BOTTOM_RIGHT, BOTTOM, BOTTOM_LEFT, LEFT, TOP_LEFT.
     */
    List<? extends DirectionInterface> getNamedDirections();

}
