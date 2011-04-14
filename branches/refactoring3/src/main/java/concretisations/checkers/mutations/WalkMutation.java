
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;

import com.google.common.collect.ImmutableList;

public class WalkMutation extends CheckersMutation {

    public WalkMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
        super(cell, mutationType, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(
                AtomicMutationFactory.newBirth(this.getCell().getNeihgbour(this.getDirection()), this.getCell().getPiece().getSide(), this.getCell().getPiece()
                        .getType()),
                AtomicMutationFactory.newDeath(this.getCell())
                );
    }
}