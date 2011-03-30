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

package side;

/**
 * API related to sides.
 */
public class API {
    
    /**
     * The null object for a side.
     */
    public final static SideInterface NULL_SIDE = Side.NULL_SIDE;
    
    /**
     * The first side.
     */
    public final static SideInterface FIRST_SIDE = Side.FIRST_SIDE;
    
    /**
     * The second side.
     */
    public final static SideInterface SECOND_SIDE = Side.SECOND_SIDE;    

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
        abstract Side getNextSide();

        /**
         * Returns the negation of this side.
         * 
         * @return the negation of this side
         */
        abstract Side getNegation();

    }

    public static boolean isNull(final Side side) {
        return side.isNull();
    }

    public static boolean isFirstSide(final Side side) {
        return side.isFirstSide();
    }

    public static boolean isSecondSide(final Side side) {
        return side.isSecondSide();
    }

    public static boolean isOneSide(final Side side) {
        return side.isOneSide();
    }

    public static Side next(final Side side) {
        return side.getNextSide();
    }

    public static Side not(final Side side) {
        return side.getNegation();
    }

}