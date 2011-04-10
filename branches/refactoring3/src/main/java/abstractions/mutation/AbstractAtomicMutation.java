
package abstractions.mutation;

import abstractions.cell.ManagedCellInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public class AbstractAtomicMutation extends AbstractMutation {

    public AbstractAtomicMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        super(cell, side, pieceType);
    }

    @Override
    public void process() {
        this.getCell().setPiece(this.getSide(), this.getPieceType());
    }

    @Override
    public void cancel() {
        this.getCell().setPiece(this.getSavedSate());
    }

}
