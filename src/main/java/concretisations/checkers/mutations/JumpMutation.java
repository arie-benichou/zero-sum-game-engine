
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.BasicMutationFactory;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.position.relative.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

// TODO ? utiliser une ManyJumpMutation
public class JumpMutation extends CheckersMutation {

    private final static int PRIORITY = 1;

    public JumpMutation(ManagedCellInterface cell, SideInterface side, RelativePositionInterface direction) {
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
                this.getCell().getRelative(RelativePositions.reduce(this.getDirection(), this.getDirection()))
            ).setProtagonist(
                this.getCell().getPiece()
            ),
                    
            BasicMutationFactory.death(
                this.getCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getCell().getPieceFactory().NullPiece()
            )                    
            
        );
    }
    
    
}