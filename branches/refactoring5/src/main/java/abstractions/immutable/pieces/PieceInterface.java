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

package abstractions.immutable.pieces;

import abstractions.immutable.ImmutableInterface;
import abstractions.immutable.side.SideInterface;

/**
 * This is the interface for a piece.
 */
public interface PieceInterface extends ImmutableInterface<PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PieceInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    SideInterface side();

    public PieceInterface apply(SideInterface side);

    /*-------------------------------------8<-------------------------------------*/

    PieceTypeInterface type();

    public PieceInterface apply(PieceTypeInterface type);

    /*-------------------------------------8<-------------------------------------*/

    public PieceInterface apply(SideInterface side, PieceTypeInterface type);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object obj);

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString();

    /*-------------------------------------8<-------------------------------------*/

}