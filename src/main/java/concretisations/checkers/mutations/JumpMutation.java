
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;
import abstractions.position.relative.RelativePositionInterface;
import abstractions.position.relative.RelativePositions;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

// TODO ? utiliser une ManyJumpMutation
public class JumpMutation extends CheckersMutation {

    private final static int PRIORITY = 1;

    public JumpMutation(CellInterface cell, SideInterface side, RelativePositionInterface direction) {
        super(PRIORITY, cell, side, direction);
    }

    protected List<AtomicMutationInterface> generateSequence() {
        return ImmutableList.of(
                
            MutationFactory.death(
                this.getConcernedCell()
            ).setProtagonist(
                this.getConcernedCell().getPieceFactory().NullPiece()
            ),
            
            MutationFactory.birth(
                this.getConcernedCell().getRelative(RelativePositions.reduce(this.getDirection(), this.getDirection()))
            ).setProtagonist(
                this.getConcernedCell().getPiece()
            ),
                    
            MutationFactory.death(
                this.getConcernedCell().getRelative(this.getDirection())
            ).setProtagonist(
                this.getConcernedCell().getPieceFactory().NullPiece()
            )                    
            
        );
    }
    
    
}