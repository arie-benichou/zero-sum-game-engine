
package concretisations.checkers.mutations;

import abstractions.direction.DirectionInterface;
import abstractions.old.cell.ManagedCellInterface;

public final class CheckersMutationFactory {

    private CheckersMutationFactory() {}

    public static CheckersMutation newJumpMutation(final ManagedCellInterface cell, final DirectionInterface direction) {
        return new JumpMutation(cell, CheckersMutations.JUMP, direction);

    }

    public static CheckersMutation newWalkMutation(final ManagedCellInterface cell, final DirectionInterface direction) {
        return new WalkMutation(cell, CheckersMutations.WALK, direction);
    }

}
