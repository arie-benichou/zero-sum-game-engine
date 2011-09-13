
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.direction.DirectionInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.AtomicMutationFactory;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.mutation.MutationTypeInterface;

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
                new CheckersBirthMutation(this.getPosition().getNeighbour(this.getDirection()).getNeighbour(this.getDirection()), this.getPosition().getPiece()
                        .type(), this.getPosition().getPiece()
                        .side()),
                AtomicMutationFactory.newDeath(this.getPosition().getNeighbour(this.getDirection())),
                AtomicMutationFactory.newDeath(this.getPosition())

                );
    }
}