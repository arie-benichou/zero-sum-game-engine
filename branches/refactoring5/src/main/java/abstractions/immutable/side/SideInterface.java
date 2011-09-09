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

package abstractions.immutable.side;

import abstractions.immutable.ImmutableInterface;

/**
 * This is the interface for a side.
 */
public interface SideInterface extends ImmutableInterface<SideInterface> {

    /*-------------------------------------8<-------------------------------------*/

    int value();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    SideInterface apply();

    SideInterface apply(int value);

    SideInterface opposite();

    /*-------------------------------------8<-------------------------------------*/

    boolean is(int value);

    boolean isNull();

    /*-------------------------------------8<-------------------------------------*/

}