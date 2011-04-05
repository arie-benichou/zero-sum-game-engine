
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;
import abstractions.position.RelativePositionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

public class WalkMutation extends CheckersMutation {

    private final static int PRIORITY = 2;

    public WalkMutation(CellInterface cell, SideInterface side, RelativePositionInterface direction) {
        super(PRIORITY, cell, side, direction);
    }

    protected List<AtomicMutationInterface> generateSequence() {
        
        return ImmutableList.of(
                
            MutationFactory.death(
                this.getConcernedCell()
            ).setProtagonist(
                this.getConcernedCell().getPieceFactory().getNullPiece()
            ),
            
            MutationFactory.birth(
                this.getConcernedCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getConcernedCell().getPiece()
            )
            
        );
        
    }
    
}
