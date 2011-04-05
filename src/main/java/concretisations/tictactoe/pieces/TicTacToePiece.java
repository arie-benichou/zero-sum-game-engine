
package concretisations.tictactoe.pieces;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.side.SideInterface;

public abstract class TicTacToePiece extends AbstractPiece {

    public TicTacToePiece(SideInterface side) {
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
