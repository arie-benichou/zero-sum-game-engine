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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.cells;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.pieces.PieceInterface;

/**
 * This is the interface for a game board cell.
 */
public interface BoardCellInterface extends Comparable<BoardCellInterface> {

    /**
     * Returns the position of this cell.
     * 
     * @return the position of this cell
     */
    //BoardPositionInterface getPosition();

    int getRow();

    int getColumn();

    /**
     * Returns true if this cell is empty.
     * 
     * @return true if this cell is empty
     */
    boolean isEmpty();

    /**
     * Returns the piece contained by this cell.
     * 
     * @return the piece contained by this cell
     */
    PieceInterface getPiece();

    /**
     * Assigns a piece to this cell.
     * 
     * @param piece
     *            the piece to be contained by this cell
     */
    void setPiece(final PieceInterface piece);

    boolean isNull();

}