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

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

public final class Pawn extends Connect4Piece {

    public Pawn(SideInterface side) {
        super(side);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(ManagedCellInterface cell, SideInterface side) {
        return NULL_MUTATIONS;
    }

}