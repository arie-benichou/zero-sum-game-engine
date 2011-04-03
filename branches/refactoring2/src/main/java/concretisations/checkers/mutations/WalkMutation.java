
package concretisations.checkers.mutations;

import abstractions.cell.API.CellInterface;
import abstractions.cell.mutation.AtomicMutationInterface;
import abstractions.cell.mutation.MutationFactory;

public class WalkMutation extends CheckersMutation {
    
    private final static int PRIORITY = 2;

    public WalkMutation(CellInterface cell) {
        super(cell, PRIORITY);
    }

    // TODO interface
    public void process() {

        AtomicMutationInterface death = MutationFactory.death(this.getCell());
        death.setProtagonist(this.getCell().getPiece());
        death.process();

        AtomicMutationInterface birth = MutationFactory.birth(this.getCell().getRelative(this.getDirection()));
        birth.setProtagonist(this.getCell().getPiece());
        birth.process();

    }

}
