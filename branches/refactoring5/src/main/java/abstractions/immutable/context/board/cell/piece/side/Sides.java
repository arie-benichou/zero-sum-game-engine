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

import annotations.Immutable;

@Immutable
public final class Sides {

    private Sides() {}

    /**
     * The null object for a side.
     */
    public static final SideInterface NULL = Side.NULL_SIDE;

    /**
     * The first side.
     */
    public static final SideInterface FIRST = Side.FIRST_SIDE;

    /**
     * The second side.
     */
    public static final SideInterface SECOND = Side.SECOND_SIDE;

}
