
package concretisations.connect4.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.position.relative.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

import concretisations.tictactoe.mutations.NewPawnMutation;

public class Null extends Connect4Piece {

    public Null(SideInterface side) {
        super(side);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return cell.isEmpty() && !cell.getRelative(RelativePositions.BOTTOM).isEmpty() ? ImmutableSet.of(new NewPawnMutation(cell, side)) : NULL_MUTATIONS;
    }

}
