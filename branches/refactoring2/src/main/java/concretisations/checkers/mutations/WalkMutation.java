
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;
import abstractions.position.RelativePositionInterface;

import com.google.common.collect.ImmutableList;

public class WalkMutation extends CheckersMutation {

    private final static int PRIORITY = 2;

    public WalkMutation(CellInterface cell, RelativePositionInterface direction) {
        super(PRIORITY, cell, direction);
    }

    public List<AtomicMutationInterface> getSequence() {
        
        return ImmutableList.of(
                
            MutationFactory.death(
                this.getConcernedCell()
            ).setProtagonist(
                this.getConcernedCell().getPiece()
            ),
            
            MutationFactory.birth(
                this.getConcernedCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getConcernedCell().getPiece()
            )
            
        );
        
    }
    
}
