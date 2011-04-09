
package abstractions.piece.mocks;

import java.util.Set;

import abstractions.cell.CellInterface;
import abstractions.piece.AbstractPiece;
import abstractions.piece.PieceActionTypeInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public class Null extends AbstractPiece {

    public Null(final SideInterface side, final PieceTypeInterface type) {
        super(side, type);
    }

    @Override
    public Set<? extends PieceActionTypeInterface> computePotentialActionTypes(final CellInterface cell, final SideInterface side) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return this.getType() + " " + this.getSide();
    }

}
