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

/**
 * This is the interface for a position.
 */
public interface PositionInterface extends Comparable<PositionInterface> {

    /**
     * Returns the column index of this position.
     * 
     * @return the column index of this position
     */
    int getColumn();

    /**
     * Returns the row index of this position.
     * 
     * @return the row index of this position
     */
    int getRow();

    /**
     * Returns true if this position is the null object, false otherwise.
     * 
     * @return true if this position is the null object, false otherwise
     */
    boolean isNull();

}