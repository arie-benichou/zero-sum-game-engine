
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AbstractMutation;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.position.RelativePositionInterface;

public abstract class CheckersMutation extends AbstractMutation implements CheckersMutationInterface {

    private final RelativePositionInterface direction;

    public CheckersMutation(final int priority, final CellInterface cell, final RelativePositionInterface direction) {
        super(priority, cell);
        this.direction = direction;
    }

    public final RelativePositionInterface getDirection() {
        return this.direction;
    }

    //TODO avoir une seule interface
    public abstract List<AtomicMutationInterface> getSequence();

    @Override
    public final String toString() {
        return super.toString() + " | " + this.direction;
    }


}
