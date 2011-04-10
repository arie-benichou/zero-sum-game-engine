
package abstractions.cell.old;

import java.util.Set;

import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

abstract class AbstractCell implements ManagedCellInterface {

    protected boolean willGenerateMutations = false;

    // TODO ? injecter un contexte
    @Override
    public final Set<? extends MutationInterface> fetchAvailableMutations(final SideInterface side) {
        return this.getPiece().computeAvailableMutations(this, side);
    }

    @Override
    public void willGenerateMutations(final boolean willItGenerateMutations) {
        this.willGenerateMutations = willItGenerateMutations;
    }

    @Override
    public boolean willGenerateMutations() {
        return this.willGenerateMutations;
    }

}