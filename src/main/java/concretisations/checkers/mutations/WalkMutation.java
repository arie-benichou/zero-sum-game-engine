
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.BasicMutationFactory;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

public class WalkMutation extends CheckersMutation {

    private final static int PRIORITY = 2;

    public WalkMutation(ManagedCellInterface cell, SideInterface side, RelativePositionInterface direction) {
        super(PRIORITY, cell, side, direction);
    }

    protected List<MutationInterface> generateSequence() {
        
        return ImmutableList.of(
                
            BasicMutationFactory.death(
                this.getCell()
            ).setProtagonist(
                this.getCell().getPieceFactory().NullPiece()
            ),
            
            BasicMutationFactory.birth(
                this.getCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getCell().getPiece()
            )
            
        );
        
    }
    
}
