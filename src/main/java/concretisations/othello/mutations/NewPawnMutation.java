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

package concretisations.othello.mutations;

import java.util.List;
import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AbstractCompositeMutation;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import concretisations.othello.pieces.OthelloPiece;

public final class NewPawnMutation extends AbstractCompositeMutation {

    private final SideInterface side;
    private final PieceTypeInterface pieceType;

    public NewPawnMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final SideInterface side,
            final PieceTypeInterface pieceType) {
        super(cell, mutationType);
        this.side = side;
        this.pieceType = pieceType;
    }

    private final SideInterface getSide() {
        return this.side;
    }

    private final PieceTypeInterface getPieceType() {
        return this.pieceType;
    }

    @Override
    protected List<MutationInterface> sequence() {
        final List<MutationInterface> sequence = Lists.newArrayList(AtomicMutationFactory.newBirth(this.getCell(), this.getSide(), this.getPieceType()));
        final Set<ManagedCellInterface> cellsToRevert = Sets.newHashSet();
        final Set<ManagedCellInterface> cellsToRevertInOneDirection = Sets.newHashSet();
        for (final DirectionInterface direction : this.getCell().getNamedDirections()) {
            cellsToRevert.addAll(
                    ((OthelloPiece) this.getCell().getPiece()).
                            getConnected(this.getCell(), this.getSide(), direction, cellsToRevertInOneDirection)
                    );
            cellsToRevertInOneDirection.clear();
        }
        for (final ManagedCellInterface cell : cellsToRevert) {
            sequence.add(AtomicMutationFactory.newAlteration(cell, this.getSide(), this.getPieceType()));
        }
        return sequence;
    }
}
