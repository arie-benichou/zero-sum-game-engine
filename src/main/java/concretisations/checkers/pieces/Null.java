
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public class Null extends CheckerPiece {

    private final static Set<DirectionInterface> NULL_DIRECTIONS = ImmutableSet.of();

    public Null(final SideInterface side) {
        super(side, Null.NULL_DIRECTIONS);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(final ManagedCellInterface cell, final SideInterface side) {
        return PieceInterface.NULL_POTENTIAL_MUTATION_SET
    }
}
