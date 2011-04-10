
package concretisations.othello.pieces;

import java.util.Set;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableSet;

import concretisations.othello.mutations.NewPawnMutation;

public class Null extends OthelloPiece implements OthelloPieceInterface {

    public Null(SideInterface side) {
        super(side);
    }

    @Override
    public Set<? extends MutationInterface> computeAvailableMutations(ManagedCellInterface cell, SideInterface side) {
        return this.isMutable(cell, side) ? ImmutableSet.of(new NewPawnMutation(cell, side)) : NULL_MUTATIONS;
    }
}
