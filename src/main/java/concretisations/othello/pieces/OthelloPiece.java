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
import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public abstract class OthelloPiece extends AbstractPiece implements OthelloPieceInterface {

    protected final static Set<? extends MutationTypeInterface> POTENTIAL_MUTATION_TYPES_SET = ImmutableSet
            .of(concretisations.othello.mutations.OthelloMutations.NEW_PAWN);

    // TODO ? avoir une méthode dans Cell qui pemet de récupérer toutes cases les voisines    
    public static List<NamedDirection> NEIGHBOURS_POSITIONS = ImmutableList.of(NamedDirection.TOP, NamedDirection.RIGHT, NamedDirection.BOTTOM,
            NamedDirection.LEFT, NamedDirection.TOP_RIGHT, NamedDirection.BOTTOM_RIGHT, NamedDirection.TOP_LEFT, NamedDirection.BOTTOM_LEFT);

    public OthelloPiece(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    public boolean isConnected(final ManagedCellInterface cell, final SideInterface side, final NamedDirection direction) {
        boolean willBeConnected = false;
        if (side.equals(cell.getPiece().getSide())) {
            willBeConnected = true;
        }
        else if (side.getNextSide().equals(cell.getPiece().getSide())) {
            final ManagedCellInterface nextCell = cell.getNeihgbour(direction);
            willBeConnected = ((OthelloPiece) nextCell.getPiece()).isConnected(nextCell, side, direction);
        }
        return willBeConnected;
    }

    public Set<ManagedCellInterface> getConnected(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction,
            Set<ManagedCellInterface> cellsToRevert) {
        if (side.equals(cell.getNeihgbour(direction).getPiece().getSide())) {
            return cellsToRevert;
        }
        final ManagedCellInterface nextCell = cell.getNeihgbour(direction);
        if (side.getNextSide().equals(nextCell.getPiece().getSide())) {
            cellsToRevert.add(nextCell);
            cellsToRevert = ((OthelloPiece) nextCell.getPiece()).getConnected(nextCell, side, direction, cellsToRevert);
        }
        else {
            cellsToRevert.clear();
        }
        return cellsToRevert;
    }

    // TODO à simplifier
    protected boolean isMutable(final ManagedCellInterface cell, final SideInterface side) {

        boolean willBeConnected = false;

        for (int index = 0, maxIndex = OthelloPiece.NEIGHBOURS_POSITIONS.size(); index < maxIndex && !willBeConnected; ++index) {

            final NamedDirection relativePosition = OthelloPiece.NEIGHBOURS_POSITIONS.get(index);

            final ManagedCellInterface nextCell = cell.getNeihgbour(relativePosition);

            if (nextCell.isNull()) {
                continue; //TODO revoir la partie concernant la pièce nulle
            }

            if (side.getNextSide().equals(nextCell.getPiece().getSide())) {
                final ManagedCellInterface nextNextCell = nextCell.getNeihgbour(relativePosition);
                final OthelloPiece nextNextPiece = (OthelloPiece) nextNextCell.getPiece();
                willBeConnected = nextNextPiece.isConnected(nextNextCell, side, relativePosition);
            }

        }

        return willBeConnected;

    }

    @Override
    public abstract Set<? extends MutationInterface> computePotentialMutations(ManagedCellInterface cell, SideInterface side);

}