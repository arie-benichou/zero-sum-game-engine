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

package concretisations.life.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

import concretisations.life.mutations.LifeMutations;

public final class Null extends LifePiece {

    private final static Set<? extends MutationTypeInterface> POTENTIAL_MUTATION_TYPES_SET = ImmutableSet.of(LifeMutations.BIRTH_PAWN);

    private final static int NUMBER_OF_NEIGHBOUR_CELLS_WITH_A_PAWN_IN_ORDER_TO_BORN = 3;

    public Null(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public Set<? extends MutationTypeInterface> computePotentialMutationTypes(final ManagedCellInterface cell, final SideInterface side) {
        if (this.count(cell) == Null.NUMBER_OF_NEIGHBOUR_CELLS_WITH_A_PAWN_IN_ORDER_TO_BORN) {
            return Null.POTENTIAL_MUTATION_TYPES_SET;
        }
        return PieceInterface.NULL_POTENTIAL_MUTATION_TYPES_SET;
    }
}
