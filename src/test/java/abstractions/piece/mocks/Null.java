
package abstractions.piece.mocks;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceActionTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public class Null extends AbstractPiece {
    
    public Null(SideInterface side, PieceTypeInterface type) {
        super(side, type);
    }

    public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(CellInterface cell, SideInterface side) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return this.getType() + " " + this.getSide();
    }

}
