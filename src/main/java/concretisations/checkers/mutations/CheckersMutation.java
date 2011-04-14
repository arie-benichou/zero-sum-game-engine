
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AbstractCompositeMutation;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;

public abstract class CheckersMutation extends AbstractCompositeMutation implements CheckersMutationInterface {

    private final DirectionInterface direction;

    public CheckersMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
        super(cell, mutationType);
        this.direction = direction;
    }

    public final DirectionInterface getDirection() {
        return this.direction;
    }

    @Override
    protected abstract List<MutationInterface> sequence();

    @Override
    public final String toString() {
        return super.toString() + " | " + this.getDirection();
    }

}
