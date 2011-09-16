
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.direction.DirectionInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.MutationInterface;

import com.google.common.collect.ImmutableSet;

public final class Null extends CheckerPiece {

    private static final Set<DirectionInterface> NULL_DIRECTIONS = ImmutableSet.of();

    public Null(final SideInterface side, final OldPieceTypeInterface type) {
        super(side, type, Null.NULL_DIRECTIONS);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(final ManagedCellInterface cell, final SideInterface side) {
        return MutationInterface.NULL_POTENTIAL_MUTATION_SET;
    }

    @Override
    public String toString() {
        return " ";
    }

}
