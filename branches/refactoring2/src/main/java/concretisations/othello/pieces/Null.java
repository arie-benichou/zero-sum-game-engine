
package concretisations.othello.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.ImmutableSet;

import concretisations.othello.mutations.NewPawnMutation;

public class Null extends OthelloPiece implements OthelloPieceInterface {

    public Null() {
        super(Sides.NULL);
    }

    @Override
    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return this.isMutable(cell, side) ? ImmutableSet.of(new NewPawnMutation(cell, side)) : EMPTY_MUTATION_SET;
    }
}
