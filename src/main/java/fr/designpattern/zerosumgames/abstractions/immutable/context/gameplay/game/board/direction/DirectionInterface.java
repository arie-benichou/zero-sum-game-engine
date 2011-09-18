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

package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.direction;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;

/**
 * This is the interface for a direction.
 */
public interface DirectionInterface extends ImmutableInterface<DirectionInterface> {

    /*-------------------------------------8<-------------------------------------*/

    /**
     * Returns the row index delta.
     * 
     * @return the row index delta
     */
    int rowDelta();

    /**
     * Returns the column index delta.
     * 
     * @return the column index delta
     */
    int columnDelta();

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public DirectionInterface apply();

    DirectionInterface apply(int rowDelta, int columnDelta);

    DirectionInterface apply(DirectionInterface direction);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    String toString();

    /*-------------------------------------8<-------------------------------------*/

}
