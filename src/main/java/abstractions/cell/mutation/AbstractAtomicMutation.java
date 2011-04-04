
package abstractions.cell.mutation;

import abstractions.cell.CellInterface;
import abstractions.piece.PieceInterface;

public abstract class AbstractAtomicMutation implements AtomicMutationInterface {

    private final CellInterface concernedCell;
    private final PieceInterface previousState;

    private transient PieceInterface protagonist;

    public AbstractAtomicMutation(CellInterface concernedCell) {
        this.concernedCell = concernedCell;
        this.previousState = concernedCell.getPiece(); // TODO ? différé au process
    }

    public final CellInterface getConcernedCell() {
        return this.concernedCell;
    }

    public final PieceInterface getPreviousState() {
        return previousState;
    }

    public final PieceInterface getProtagonist() { // TODO ? simplification possible pour Death.
        return this.protagonist;
    }

    public final void setProtagonist(final PieceInterface concernedPiece) {
        this.protagonist = concernedPiece;
    }

    public abstract void process();

}
