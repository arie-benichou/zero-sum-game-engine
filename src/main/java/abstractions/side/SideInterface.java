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

package abstractions.side;

/**
 * This is the interface for a side.
 */
public interface SideInterface {

    /**
     * Returns true if this is the null side, false otherwise.
     * 
     * @return true if this is the null side, false otherwise
     */
    boolean isNull();

    /**
     * Return true if this is the first side, false otherwise.
     * 
     * @return true if this is the first side, false otherwise
     */
    boolean isFirstSide();

    /**
     * Return true if this is the second side, false otherwise.
     * 
     * @return true if this is the second side, false otherwise
     */
    boolean isSecondSide();

    /**
     * Return true if this is the first or second side, false otherwise.
     * 
     * @return true if this is the first or second side, false otherwise
     */
    boolean isOneSide();

    /**
     * Returns the side after this one.
     * 
     * @return the side after this one
     */
    abstract SideInterface getNextSide();

    /**
     * Returns the negation of this side.
     * 
     * @return the negation of this side
     */
    abstract SideInterface getNegation();

}