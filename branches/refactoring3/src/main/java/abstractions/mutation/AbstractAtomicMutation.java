
package abstractions.mutation;

import abstractions.cell.old.CellInterface;
import abstractions.piece.PieceInterface;

public abstract class AbstractAtomicMutation implements AtomicMutationInterface {

    private final CellInterface concernedCell;
    private final PieceInterface savedSate;
    
    private transient PieceInterface protagonist;

    public AbstractAtomicMutation(final CellInterface concernedCell) {
        this.concernedCell = concernedCell;
        this.savedSate = concernedCell.getPiece();
    }

    public final CellInterface getConcernedCell() {
        return this.concernedCell;
    }
    
    public final PieceInterface getProtagonist() {
        return this.protagonist;
    }

    public final AtomicMutationInterface setProtagonist(final PieceInterface concernedPiece) {
        this.protagonist = concernedPiece;
        return this;
    }

    public void process() {
        this.getConcernedCell().setPiece(this.getProtagonist());
    }
    
    
    public void cancel() {
        this.getConcernedCell().setPiece(this.savedSate);
    }    

}
