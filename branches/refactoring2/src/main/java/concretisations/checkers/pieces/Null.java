
package concretisations.checkers.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.NullPiece;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public class Null extends NullPiece {

    private final static Set<MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();

    @Override
    public Set<MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return AVAILABLE_MUTATIONS;
    }

}
