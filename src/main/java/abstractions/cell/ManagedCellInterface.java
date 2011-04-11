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

package abstractions.cell;

import java.util.Set;

import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

/**
 * This is the interface for a cell.
 */
public interface ManagedCellInterface extends Comparable<ManagedCellInterface> {

    /**
     * Returns the position of this cell.
     * 
     * @return the position of this cell
     */
    PositionInterface getPosition();

    /**
     * Returns the row for this cell.
     * 
     * @return the row for this cell
     */
    int getRow();

    /**
     * Returns the column for this cell.
     * 
     * @return the column for this cell
     */
    int getColumn();

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
    ManagedCellInterface setPiece(final PieceInterface piece);

    ManagedCellInterface setPiece(SideInterface side, PieceTypeInterface pieceType);

    /**
     * Returns true if this cell is the null object, false otherwise.
     * 
     * @return true if this cell is the null object, false otherwise
     */
    boolean isNull();

    boolean isEmpty();

    ManagedCellInterface getRelative(DirectionInterface direction);

    Set<? extends MutationInterface> getPotentialMutation(SideInterface side);

    ManagedCellInterface die();
}