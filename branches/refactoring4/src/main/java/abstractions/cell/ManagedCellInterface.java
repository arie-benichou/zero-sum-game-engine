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

import java.util.List;
import java.util.Map;
import java.util.Set;

import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
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
     * 
     * @return this cell
     */
    ManagedCellInterface setPiece(final PieceInterface piece);

    /**
     * Assigns a piece to this cell.
     * 
     * @param side
     *            the side of the piece
     * 
     * @param pieceType
     *            the type of the piece
     * 
     * @return this cell.
     */
    ManagedCellInterface setPiece(final SideInterface side, final PieceTypeInterface pieceType);

    /**
     * Returns true if this cell is the null object, false otherwise.
     * 
     * @return true if this cell is the null object, false otherwise
     */
    boolean isNull();

    /**
     * Returns true if this cell is empty, false otherwise.
     * 
     * @return true if this cell is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns a neighbour of this cell in a given direction.
     * 
     * @param direction
     *            a given direction
     * 
     * @return a neighbour of this cell in a given direction
     */
    ManagedCellInterface getNeighbour(final DirectionInterface direction);

    /**
     * Returns a neighbour of this cell in a given named direction.
     * 
     * @param namedDirection
     *            a named direction
     * 
     * @return a neighbour of this cell in a given named direction
     */
    ManagedCellInterface getNeighbour(final NamedDirection namedDirection);

    /**
     * Returns a set of potential mutations for this cell and for a given side.
     * 
     * @param side
     *            the side to play
     * 
     * @return a set of potential mutations for this cell and for a given side
     */
    Set<? extends MutationInterface> getPotentialMutations(final SideInterface side);

    /**
     * Returns the neighbour cells to this cell.
     * 
     * @return the neighbour cells to this cell
     */
    Map<DirectionInterface, ManagedCellInterface> getNeighbourhood();

    /**
     * Returns a list of the 8 named directions.
     * 
     * @return a list of the 8 named directions
     */
    List<NamedDirection> getNamedDirections();

    /**
     * Death for this cell: this cell will become empty.
     * 
     * @return this cell
     */
    ManagedCellInterface die();

    /**
     * Returns a console view of this cell.
     * 
     * @return a console view of this cell
     */
    String render();

}