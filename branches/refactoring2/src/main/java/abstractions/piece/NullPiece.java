
package abstractions.piece;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.ImmutableSet;

public class NullPiece extends PotentialPiece {
    
    private final static Set<MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();

    public NullPiece() {
        super(Sides.NULL_SIDE);
    }

    @Override
    public final boolean isNull() {
        return true;
    }

    @Override
    public final String toString() {
        return " ";
    }

    public Set<MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return AVAILABLE_MUTATIONS;
    }

}