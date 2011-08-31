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

package concretisations.tictactoe.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public final class Null extends AbstractPiece {

    public Null(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public Set<MutationInterface> computePotentialMutations(final ManagedCellInterface cell, final SideInterface side) {
        return ImmutableSet.of(AtomicMutationFactory.newBirth(cell, side, TicTacToePieceSet.PAWN));
    }

    @Override
    public String toString() {
        return " "; // space
    }

}
