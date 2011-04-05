
package concretisations.connect4.mutations;

import java.util.List;

import abstractions.cell.CellInterface;
import abstractions.mutation.AbstractMutation;
import abstractions.mutation.AtomicMutationInterface;
import abstractions.mutation.MutationFactory;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

import concretisations.tictactoe.pieces.Pieces;

public class NewPawnMutation extends AbstractMutation {

    public NewPawnMutation(final CellInterface cell, SideInterface side) {
        super(cell, side);
    }
    
    protected List<AtomicMutationInterface> generateSequence() {
        
        return ImmutableList.of(
            MutationFactory.birth(
                this.getConcernedCell()
            ).setProtagonist(
                this.getConcernedCell().getPieceFactory().getPiece(this.getSide(), Pieces.PAWN)
            )
            
        );
    }

}
