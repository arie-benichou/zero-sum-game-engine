
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public final class Null extends CheckerPiece {

    private static final Set<? extends DirectionInterface> NULL_DIRECTIONS = ImmutableSet.of();

    public Null(final SideInterface side, final PieceTypeInterface type) {
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
