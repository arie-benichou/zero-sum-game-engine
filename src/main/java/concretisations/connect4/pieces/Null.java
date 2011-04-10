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

package concretisations.connect4.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.side.SideInterface;

public final class Null extends Connect4Piece {

    public Null(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public Set<? extends MutationTypeInterface> computePotentialMutationTypes(final ManagedCellInterface cell, final SideInterface side) {

        if (cell.isEmpty() && !cell.getRelative(Direction.BOTTOM).isEmpty()) {
            return Connect4Piece.POTENTIAL_MUTATION_TYPES_SET;
        }

        return Connect4Piece.NULL_POTENTIAL_MUTATION_TYPES_SET;
    }

}
