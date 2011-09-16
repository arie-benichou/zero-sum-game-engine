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

import abstractions.immutable.context.gameplay.game.board.cell.piece.AbstractPiece;
import abstractions.immutable.context.gameplay.game.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;

public abstract class LifePiece extends AbstractPiece {

    protected static final int CUT_OFF = 4;

    public LifePiece(final SideInterface side, final OldPieceTypeInterface type) {
        super(side, type);
    }

    protected final int count(final ManagedCellInterface cell) {
        int n = 0;
        for (int index = 0; index < ManagedCellInterface.MAXIMAL_NUMBER_OF_NEIGHBOURS && n < LifePiece.CUT_OFF; ++index) {
            if (!cell.getNeighbour(ManagedCellInterface.NEIGHBOUR_DIRECTIONS.get(index)).getPiece().side().isNull()) {
                ++n;
            }
        }
        return n;
    }

    @Override
    public abstract Set<MutationInterface> computePotentialMutations(ManagedCellInterface cell, SideInterface side);

}
