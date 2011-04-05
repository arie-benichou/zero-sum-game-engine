
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
        super(Sides.NULL_SIDE);
    }

    @Override
    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        System.out.println("------------------------------------------");
        System.out.println(cell.getRow());
        System.out.println(cell.getColumn());
        
        if(this.checkNeighbourhood(cell, side)) {
            System.out.println("mutation available");
            return ImmutableSet.of(new NewPawnMutation(cell, side));
        }
        else {
            System.out.println("no mutation available");
        }
        return EMPTY_MUTATION_SET;
    }
}
