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

package concretisations.othello.pieces;

import java.util.Set;

import abstractions.immutable.context.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;

public final class Pawn extends OthelloPiece {

    public Pawn(final SideInterface side, final OldPieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public Set<MutationInterface> computePotentialMutations(final ManagedCellInterface cell, final SideInterface side) {
        return MutationInterface.NULL_POTENTIAL_MUTATION_SET;
    }

    @Override
    public String toString() {
        String consoleView = "";
        if (this.side().isFirst()) {
            consoleView = "x";
        }
        else if (this.side().isSecondSide()) {
            consoleView = "o";
        }
        return consoleView;
    }

}