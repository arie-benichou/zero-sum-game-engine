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

package abstractions.piece;

import abstractions.side.SideInterface;

/**
 * This is the interface for the piece manager.
 */
public interface PieceManagerInterface {

    /**
     * Returns the null piece.
     * 
     * @return the null piece
     */
    PieceInterface getNullPiece();

    /**
     * Returns a piece for a given side and a given type of piece.
     * 
     * @param side
     *            the side for this piece
     * 
     * @param type
     *            the type of the piece
     * 
     * @return a piece for a given side and a given type of piece
     */
    PieceInterface getPiece(SideInterface side, PieceTypeInterface type);

}
