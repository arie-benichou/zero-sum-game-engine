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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimensions.cells.pieces;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

/**
 * This is the interface for the game pieces factory.
 */
public interface PiecesInterface {

    /**
     * Returns a piece for a given side and a given type of piece.
     * 
     * @param side
     *            the given side
     * 
     * @param type
     *            the given type of piece
     * 
     * @return a piece for a given side and a given type of piece
     */
    PieceInterface getPiece(final OpponentsEnumeration side,
            final PieceTypeInterface type);

}