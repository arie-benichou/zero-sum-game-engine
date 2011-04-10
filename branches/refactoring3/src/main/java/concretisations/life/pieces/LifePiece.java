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
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.Direction;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

public abstract class LifePiece extends AbstractPiece {

    // TODO ajouter une méthode dans ManagedCell qui permet de récupérer toutes cellules les voisines d'une cellule    
    protected static List<? extends DirectionInterface> NEIGHBOURS_POSITIONS = ImmutableList.of(
            Direction.TOP,
            Direction.RIGHT,

            Direction.BOTTOM,
            Direction.LEFT,
            Direction.TOP_RIGHT,
            Direction.BOTTOM_RIGHT,
            Direction.TOP_LEFT,
            Direction.BOTTOM_LEFT
            );

    protected static int MAXIMUM_NUMBER_OF_NEIGHBOUR_FOR_A_CELL = LifePiece.NEIGHBOURS_POSITIONS.size();

    protected final static int CUT_OFF = 4;

    public LifePiece(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    protected int count(final ManagedCellInterface cell) {
        int n = 0;
        for (int index = 0; index < LifePiece.MAXIMUM_NUMBER_OF_NEIGHBOUR_FOR_A_CELL && n < LifePiece.CUT_OFF; ++index) {
            if (!cell.getRelative(LifePiece.NEIGHBOURS_POSITIONS.get(index)).getPiece().getSide().isNull()) {
                ++n;
            }
        }
        return n;
    }

    @Override
    public abstract Set<? extends MutationTypeInterface> computePotentialMutationTypes(ManagedCellInterface cell, SideInterface side);

}
