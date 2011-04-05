
package concretisations.tictactoe.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.ImmutableSet;

import concretisations.tictactoe.mutations.NewPawnMutation;

public class Null extends TicTacToePiece {

    public Null() {
        super(Sides.NULL_SIDE);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return ImmutableSet.of(new NewPawnMutation(cell, side));
    }

}
