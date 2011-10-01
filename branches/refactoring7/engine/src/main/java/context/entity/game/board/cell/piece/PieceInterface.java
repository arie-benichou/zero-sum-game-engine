/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General License for more details.
 * 
 * You should have received a copy of the GNU General License along with this
 * program. If not, see <http://www.gnu.org/licenses/>.
 */

package context.entity.game.board.cell.piece;

import util.interfaces.ImmutableInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.piece.type.PieceTypeInterface;

/**
 * This is the interface for a piece.
 */
public interface PieceInterface extends ImmutableInterface<PieceInterface> {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    PieceInterface apply();

    /*-------------------------------------8<-------------------------------------*/

    SideInterface side();

    PieceInterface apply(SideInterface side);

    /*-------------------------------------8<-------------------------------------*/

    PieceTypeInterface type();

    PieceInterface apply(PieceTypeInterface type);

    /*-------------------------------------8<-------------------------------------*/

    PieceInterface apply(SideInterface side, PieceTypeInterface type);

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