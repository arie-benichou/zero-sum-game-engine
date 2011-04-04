package concretisations.checkers.mutations;

import abstractions.cell.CellInterface;
import abstractions.position.RelativePositionInterface;

public class CheckersMutationFactory {
    
    private CheckersMutationFactory() {}
    
    public static CheckersMutation jump(final CellInterface cell, final RelativePositionInterface direction) {
        return new JumpMutation(cell, direction);
        
    }
    public static CheckersMutation walk(final CellInterface cell, final RelativePositionInterface direction) {
        return new WalkMutation(cell, direction);
    }

}
