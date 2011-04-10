
package concretisations.checkers.mutations;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.old._AbstractMutation;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

public abstract class CheckersMutation extends _AbstractMutation implements CheckersMutationInterface {

    private final RelativePositionInterface direction;

    public CheckersMutation(final int priority, final ManagedCellInterface cell, SideInterface side, final RelativePositionInterface direction) {
        super(priority, cell, side);
        this.direction = direction;
    }

    public final RelativePositionInterface getDirection() {
        return this.direction;
    }

    @Override
    public final String toString() {
        return super.toString() + " | " + this.direction;
    }

}
