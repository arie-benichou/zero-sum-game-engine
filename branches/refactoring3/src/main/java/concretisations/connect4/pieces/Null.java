
package concretisations.connect4.pieces;

import java.util.Set;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.position.relative.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

import concretisations.connect4.mutations.NewPawnMutation;

public class Null extends Connect4Piece {

    public Null(SideInterface side) {
        super(side);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(ManagedCellInterface cell, SideInterface side) {
        return cell.isEmpty() && !cell.getRelative(RelativePositions.BOTTOM).isEmpty() ? ImmutableSet.of(new NewPawnMutation(cell, side)) : NULL_MUTATIONS;
    }

}
