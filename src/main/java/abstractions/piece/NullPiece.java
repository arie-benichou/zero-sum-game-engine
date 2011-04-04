
package abstractions.piece;

import java.util.Set;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.MutationInterface;
import abstractions.side.API.SideInterface;

import com.google.common.collect.ImmutableSet;

public class NullPiece extends PotentialPiece {
    
    private final static Set<MutationInterface> AVAILABLE_MUTATIONS = ImmutableSet.of();

    public NullPiece() {
        super(abstractions.side.API.NULL_SIDE);
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