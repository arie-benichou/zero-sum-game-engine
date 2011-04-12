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
import abstractions.direction.NamedDirection;
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

    PieceInterface getNullPiece();

    ManagedCellInterface getCell(final int rowIndex, final int columnIndex);

    ManagedCellInterface getCell(final PositionInterface position);

    // Façade du PieceManager    
    PieceInterface piece(SideInterface side, PieceTypeInterface pieceType);

    // Façade du PositionManager    
    PositionInterface position(final int rowIndex, final int columnIndex);

    PositionInterface position(final PositionInterface position, final DirectionInterface direction);

    // Façade du PositionManager    
    PositionInterface position(final PositionInterface position, final NamedDirection direction);

    Map<ManagedCellInterface, Set<? extends MutationInterface>> getPotentialMutations(final SideInterface side);

    List<DirectionInterface> getDirections();

}
