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
        abstract SideInterface getNextSide();

        /**
         * Returns the negation of this side.
         * 
         * @return the negation of this side
         */
        abstract SideInterface getNegation();

    }

    // TODO ? à virer
    public static boolean isNull(final SideInterface side) {
        return side.isNull();
    }

    // TODO ? à virer    
    public static boolean isFirstSide(final SideInterface side) {
        return side.isFirstSide();
    }

    // TODO ? à virer    
    public static boolean isSecondSide(final SideInterface side) {
        return side.isSecondSide();
    }

    // TODO ? à virer    
    public static boolean isOneSide(final SideInterface side) {
        return side.isOneSide();
    }

    // TODO ? à virer    
    public static SideInterface next(final SideInterface side) {
        return side.getNextSide();
    }

    // TODO ? à virer    
    public static SideInterface not(final SideInterface side) {
        return side.getNegation();
    }

}