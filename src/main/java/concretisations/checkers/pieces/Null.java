
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.NamedDirection;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public class Null extends CheckerPiece {

    private final static Set<NamedDirection> NULL_DIRECTIONS = ImmutableSet.of();

    public Null(final SideInterface side, final PieceTypeInterface type, final Set<NamedDirection> directions) {
        super(side, type, Null.NULL_DIRECTIONS);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(final ManagedCellInterface cell, final SideInterface side) {
        return PieceInterface.NULL_POTENTIAL_MUTATION_SET;
    }
}
