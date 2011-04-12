
package concretisations.checkers.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.position.PositionManager.DirectionInterface;

public class CheckersMutationFactory {

    private CheckersMutationFactory() {}

    public static CheckersMutation newJumpMutation(final ManagedCellInterface cell, final DirectionInterface direction) {
        return new JumpMutation(cell, CheckersMutations.JUMP, direction);

    }

    public static CheckersMutation newWalkMutation(final ManagedCellInterface cell, final DirectionInterface direction) {
        return new WalkMutation(cell, CheckersMutations.WALK, direction);
    }

}
