
package concretisations.checkers.mutations;

import abstractions.cell.CellInterface;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;


// TODO ? utiliser une manyJumpMutation
public class JumpMutation extends CheckersMutation {
    
    private final static int PRIORITY = 1;

    public JumpMutation(CellInterface cell) {
        super(cell, PRIORITY);
    }

    // TODO interface
    public void process() {

        //TODO à mettre dans une liste pour l'historisation.

        AtomicMutationInterface death1 = MutationFactory.death(this.getCell());
        death1.setProtagonist(this.getCell().getPiece());
        death1.process();

        AtomicMutationInterface birth = MutationFactory.birth(this.getCell().getRelative(this.getDirection()).getRelative(this.getDirection()));
        
        birth.setProtagonist(this.getCell().getPiece());
        birth.process();

        AtomicMutationInterface death2 = MutationFactory.death(this.getCell().getRelative(this.getDirection()));
        death2.setProtagonist(this.getCell().getRelative(this.getDirection()).getPiece());
        death2.process();

    }

}
