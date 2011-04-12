
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.position.PositionManager.DirectionInterface;

import com.google.common.collect.ImmutableList;

// TODO ? SeveralJumpsMutation
public class JumpMutation extends CheckersMutation {

    public JumpMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
        super(cell, mutationType, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(
                AtomicMutationFactory.newBirth(this.getCell().getRelative(this.getDirection()).getRelative(this.getDirection()), this.getCell().getPiece()
                        .getSide(), this.getCell().getPiece().getType()),
                AtomicMutationFactory.newDeath(this.getCell().getRelative(this.getDirection())),
                AtomicMutationFactory.newDeath(this.getCell())

        );
    }
}