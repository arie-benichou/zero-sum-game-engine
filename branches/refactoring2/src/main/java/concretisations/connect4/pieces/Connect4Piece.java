
package concretisations.connect4.pieces;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.side.SideInterface;

public abstract class Connect4Piece extends AbstractPiece {
    
    protected final static Set<? extends MutationInterface> NULL_MUTATIONS = ImmutableSet.of();    

    public Connect4Piece(SideInterface side) {
        super(side);
    }
    
    public final String toString() {
        String consoleView;
        if (this.getSide().isFirstSide()) {
            consoleView = "x";
        }
        else if (this.getSide().isSecondSide()) {
            consoleView = "o";
        }
        else {
            consoleView = " ";
        }
        return consoleView;
    }
    
    public abstract Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side);    

}
