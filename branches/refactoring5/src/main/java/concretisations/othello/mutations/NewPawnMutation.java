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

import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.Side;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.AbstractCompositeMutation;
import abstractions.old.mutation.AtomicMutationFactory;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.mutation.MutationTypeInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import concretisations.othello.pieces.OthelloPiece;

public final class NewPawnMutation extends AbstractCompositeMutation implements OthelloMutationInterface {

    private final SideInterface side;
    private final OldPieceTypeInterface pieceType;

    private int firstSideDelta;
    private int secondSideDelta;
    private int numberOfPawnsToRevert;

    @Override
    public final int getFirstSideDelta() {
        return this.firstSideDelta;
    }

    @Override
    public final int getSecondSideDelta() {
        return this.secondSideDelta;
    }

    @Override
    public final int getNumberOfPawnsToRevert() {
        return this.numberOfPawnsToRevert;
    }

    public NewPawnMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final SideInterface side,
            final OldPieceTypeInterface pieceType) {
        super(cell, mutationType);
        this.side = side;
        this.pieceType = pieceType;
    }

    private SideInterface getSide() {
        return this.side;
    }

    private OldPieceTypeInterface getPieceType() {
        return this.pieceType;
    }

    @Override
    protected List<MutationInterface> sequence(final ContextInterface context) {

        final List<MutationInterface> sequence = Lists.newArrayList(AtomicMutationFactory.newBirth(context.getCellManager().getCell(this.getPosition()),
                this.getSide(), this.getPieceType()));
        final Set<ManagedCellInterface> cellsToRevert = Sets.newHashSet();
        final Set<ManagedCellInterface> cellsToRevertInOneDirection = Sets.newHashSet();
        for (final NamedDirection direction : context.getCellManager().getCell(this.getPosition()).getNamedDirections()) {
            cellsToRevert.addAll(
                    ((OthelloPiece) context.getCellManager().getCell(this.getPosition()).getPiece()).getConnected(
                            context.getCellManager().getCell(this.getPosition()), this.getSide(),
                            direction, cellsToRevertInOneDirection)
                    );
            cellsToRevertInOneDirection.clear();
        }

        int numberOfPawnsToRevert = 0;

        for (final ManagedCellInterface cell : cellsToRevert) {
            sequence.add(AtomicMutationFactory.newAlteration(cell, this.getSide(), this.getPieceType()));
            ++numberOfPawnsToRevert;
        }

        if (this.getSide().equals(Side.FIRST)) {
            this.firstSideDelta = numberOfPawnsToRevert + 1;
            this.secondSideDelta = -numberOfPawnsToRevert;
        }
        else {
            this.secondSideDelta = numberOfPawnsToRevert + 1;
            this.firstSideDelta = -numberOfPawnsToRevert;
        }

        this.numberOfPawnsToRevert = numberOfPawnsToRevert;

        return sequence;
    }

    @Override
    public int compareTo(final MutationInterface that) {
        return ((OthelloMutationInterface) that).getNumberOfPawnsToRevert() - this.getNumberOfPawnsToRevert(); // for descending order
    }

}