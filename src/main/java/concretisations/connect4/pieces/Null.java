
package concretisations.connect4.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.NullPiece;
import abstractions.position.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

import concretisations.tictactoe.mutations.NewPawnMutation;

public class Null extends NullPiece {

    private final static Set<? extends MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();

    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return cell.isEmpty() && !cell.getRelative(RelativePositions.BOTTOM).isEmpty() ? ImmutableSet.of(new NewPawnMutation(cell, side)) : AVAILABLE_MUTATIONS;
    }

}
