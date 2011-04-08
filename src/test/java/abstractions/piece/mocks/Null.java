
package abstractions.piece.mocks;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public class Null extends AbstractPiece {
    
    public Null(SideInterface side, PieceTypeInterface type) {
        super(side, type);
    }

    public Set<? extends MutationInterface> computeAvailableMutations(CellInterface cell, SideInterface side) {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

}
