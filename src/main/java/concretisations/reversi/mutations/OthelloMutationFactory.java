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

package concretisations.reversi.mutations;

import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public final class OthelloMutationFactory {

    private OthelloMutationFactory() {}

    public static MutationInterface newPawnMutation(final ManagedCellInterface cell, final SideInterface side) {
        return new NewPawnMutation(cell, OthelloMutations.NEW_PAWN, side, OthelloPieceSet.PAWN);
    }

}
