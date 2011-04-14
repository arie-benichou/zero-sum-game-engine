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
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionInterface;
import abstractions.side.SideInterface;

public interface CellManagerInterface extends Iterable<ManagedCellInterface> {

    /**
     * Returns the null cell.
     * 
     * @return the null cell
     */
    ManagedCellInterface getNullCell();

    /**
     * Returns the null piece.
     * 
     * @return the null piece
     */
    PieceInterface getNullPiece();

    /**
     * Returns the cell related to a couple of (rowIndex, columnIndex).
     * 
     * @param rowIndex
     *            the row index
     * 
     * @param columnIndex
     *            the column index
     * 
     * @return the cell related to the position(rowIndex, columnIndex)
     */
    ManagedCellInterface getCell(final int rowIndex, final int columnIndex);

    /**
     * Returns the cell related to a position.
     * 
     * @param position
     *            a given position
     * 
     * @return the cell related to a position
     */
    ManagedCellInterface getCell(final PositionInterface position);

    /**
     * Returns a set of potential mutations for this cell.
     * 
     * @param side
     *            the side to play
     * 
     * @return a set of potential mutations for this cell
     */
    Map<ManagedCellInterface, Set<? extends MutationInterface>> getPotentialMutations(final SideInterface side);

    /////////////////////////////////////////////////////////////////
    // PieceManager facade
    /////////////////////////////////////////////////////////////////
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
    PieceInterface piece(SideInterface side, PieceTypeInterface pieceType);

    /////////////////////////////////////////////////////////////////
    // PositionManager facade
    /////////////////////////////////////////////////////////////////    
    /**
     * Façade du PositionManager
     * 
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    PositionInterface position(final int rowIndex, final int columnIndex);

    /**
     * Façade du PositionManager
     * 
     * @param position
     * @param direction
     * @return
     */
    PositionInterface position(final PositionInterface position, final DirectionInterface direction);

    /////////////////////////////////////////////////////////////////
    // DirectionManager facade
    /////////////////////////////////////////////////////////////////    

    /**
     * Façade du DirectionManager
     * 
     * @return
     */
    List<? extends DirectionInterface> getNamedDirections();

}
