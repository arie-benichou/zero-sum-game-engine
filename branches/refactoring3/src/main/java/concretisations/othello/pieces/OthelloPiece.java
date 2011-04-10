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
import com.google.common.collect.ImmutableSet;

public abstract class OthelloPiece extends AbstractPiece implements OthelloPieceInterface {

    protected final static Set<? extends MutationTypeInterface> POTENTIAL_MUTATION_TYPES_SET = ImmutableSet
            .of(concretisations.othello.mutations.OthelloMutations.NEW_PAWN);

    // TODO ? avoir une méthode dans Cell qui pemet de récupérer toutes cases les voisines    
    public static List<? extends DirectionInterface> NEIGHBOURS_POSITIONS = ImmutableList.of(Direction.TOP, Direction.RIGHT, Direction.BOTTOM, Direction.LEFT,
            Direction.TOP_RIGHT, Direction.BOTTOM_RIGHT, Direction.TOP_LEFT, Direction.BOTTOM_LEFT);

    public OthelloPiece(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public boolean willItBeConnected(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction) {
        boolean willBeConnected = false;
        if (side.equals(cell.getPiece().getSide())) {
            willBeConnected = true;
        }
        else if (side.getNextSide().equals(cell.getPiece().getSide())) {
            final ManagedCellInterface nextCell = cell.getRelative(direction);
            willBeConnected = ((OthelloPiece) nextCell.getPiece()).willItBeConnected(nextCell, side, direction);
        }
        return willBeConnected;
    }

    protected boolean isMutable(final ManagedCellInterface cell, final SideInterface side) {

        boolean willBeConnected = false;

        for (int index = 0, maxIndex = OthelloPiece.NEIGHBOURS_POSITIONS.size(); index < maxIndex && !willBeConnected; ++index) {

            final DirectionInterface relativePosition = OthelloPiece.NEIGHBOURS_POSITIONS.get(index);

            final ManagedCellInterface nextCell = cell.getRelative(relativePosition);

            if (nextCell.isNull()) {
                continue; //TODO revoir la partie concernant la pièce nulle
            }

            if (side.getNextSide().equals(nextCell.getPiece().getSide())) {
                final ManagedCellInterface nextNextCell = nextCell.getRelative(relativePosition);
                final OthelloPiece nextNextPiece = (OthelloPiece) nextNextCell.getPiece();
                willBeConnected = nextNextPiece.willItBeConnected(nextNextCell, side, relativePosition);
            }

        }

        return willBeConnected;

    }

    @Override
    public abstract Set<? extends MutationTypeInterface> computePotentialMutationTypes(ManagedCellInterface cell, SideInterface side);

}