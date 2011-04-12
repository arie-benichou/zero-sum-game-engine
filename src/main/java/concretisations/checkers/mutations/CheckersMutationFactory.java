
package concretisations.checkers.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.NamedDirection;

public class CheckersMutationFactory {

    private CheckersMutationFactory() {}

    public static CheckersMutation newJumpMutation(final ManagedCellInterface cell, final NamedDirection direction) {
        return new JumpMutation(cell, CheckersMutations.JUMP, direction);

    }

    public static CheckersMutation newWalkMutation(final ManagedCellInterface cell, final NamedDirection direction) {
        return new WalkMutation(cell, CheckersMutations.WALK, direction);
    }

}
