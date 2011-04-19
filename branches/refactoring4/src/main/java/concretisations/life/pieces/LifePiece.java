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

import java.util.List;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

public abstract class LifePiece extends AbstractPiece {

    protected static final int CUT_OFF = 4;

    // TODO ! réutiliser l'API d'une cellule
    private static final List<NamedDirection> NEIGHBOURS_POSITIONS = ImmutableList.of(
            NamedDirection.TOP,
            NamedDirection.TOP_RIGHT,
            NamedDirection.RIGHT,
            NamedDirection.BOTTOM_RIGHT,
            NamedDirection.BOTTOM,
            NamedDirection.BOTTOM_LEFT,
            NamedDirection.LEFT,
            NamedDirection.TOP_LEFT
            );

    protected static final int MAXIMUM_NUMBER_OF_NEIGHBOUR_FOR_A_CELL = LifePiece.NEIGHBOURS_POSITIONS.size();

    public LifePiece(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    protected final int count(final ManagedCellInterface cell) {
        int n = 0;
        for (int index = 0; index < LifePiece.MAXIMUM_NUMBER_OF_NEIGHBOUR_FOR_A_CELL && n < LifePiece.CUT_OFF; ++index) {
            if (!cell.getNeighbour(LifePiece.NEIGHBOURS_POSITIONS.get(index)).getPiece().getSide().isNull()) {
                ++n;
            }
        }
        return n;
    }

    @Override
    public abstract Set<? extends MutationInterface> computePotentialMutations(ManagedCellInterface cell, SideInterface side);

}
