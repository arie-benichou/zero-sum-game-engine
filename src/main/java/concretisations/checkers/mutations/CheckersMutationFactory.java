
package concretisations.checkers.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

public class CheckersMutationFactory {

    private CheckersMutationFactory() {}

    public static CheckersMutation jump(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction) {
        return new JumpMutation(cell, side, direction);

    }

    public static CheckersMutation walk(final ManagedCellInterface cell, final SideInterface side, final DirectionInterface direction) {
        return new WalkMutation(cell, side, direction);
    }

}
