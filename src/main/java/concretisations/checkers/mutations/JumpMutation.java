
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;

import com.google.common.collect.ImmutableList;

// TODO ? SeveralJumpsMutation
public final class JumpMutation extends CheckersMutation {

    public JumpMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
        super(cell, mutationType, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(

                /*
                AtomicMutationFactory.newBirth(this.getCell().getNeighbour(this.getDirection()).getNeighbour(this.getDirection()), this.getCell().getPiece()
                        .getSide(), this.getCell().getPiece().getType()),
                */
                new CheckersBirthMutation(this.getCell().getNeighbour(this.getDirection()).getNeighbour(this.getDirection()), this.getCell().getPiece()
                        .getType(), this.getCell().getPiece()
                        .getSide()),
                AtomicMutationFactory.newDeath(this.getCell().getNeighbour(this.getDirection())),
                AtomicMutationFactory.newDeath(this.getCell())

                );
    }
}