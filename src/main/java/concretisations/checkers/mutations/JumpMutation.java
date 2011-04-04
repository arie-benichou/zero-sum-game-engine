
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;
import abstractions.position.RelativePositionInterface;
import abstractions.position.RelativePositions;

import com.google.common.collect.ImmutableList;

// TODO ? utiliser une ManyJumpMutation
public class JumpMutation extends CheckersMutation {

    private final static int PRIORITY = 1;

    public JumpMutation(CellInterface cell, RelativePositionInterface direction) {
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
                this.getConcernedCell().getRelative(RelativePositions.reduce(this.getDirection(), this.getDirection()))
            ).setProtagonist(
                this.getConcernedCell().getPiece()
            ),
                    
            MutationFactory.death(
                this.getConcernedCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getConcernedCell().getRelative(this.getDirection()).getPiece()
            )                    
            
        );
        
    }
    
}