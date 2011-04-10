package concretisations.checkers.mutations;

import abstractions.cell.old.CellInterface;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

public class CheckersMutationFactory {
    
    private CheckersMutationFactory() {}
    
    public static CheckersMutation jump(final CellInterface cell, final SideInterface side, final RelativePositionInterface direction) {
        return new JumpMutation(cell, side, direction);
        
    }
    public static CheckersMutation walk(final CellInterface cell, final SideInterface side, final RelativePositionInterface direction) {
        return new WalkMutation(cell, side, direction);
    }

}
