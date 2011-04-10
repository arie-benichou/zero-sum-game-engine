
package concretisations.connect4.mutations;

import java.util.List;

import abstractions.cell.old.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.BasicMutationFactory;
import abstractions.mutation.old._AbstractMutation;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

import concretisations.tictactoe.pieces.Pieces;

public class NewPawnMutation extends _AbstractMutation {

    public NewPawnMutation(final ManagedCellInterface cell, SideInterface side) {
        super(cell, side);
    }
    
    protected List<MutationInterface> generateSequence() {
        
        return ImmutableList.of(
            BasicMutationFactory.birth(
                this.getCell()
            ).setProtagonist(
                this.getCell().getPieceFactory().Piece(this.getSide(), Pieces.PAWN)
            )
            
        );
    }

}
