
package abstractions.cell.mutation;

import abstractions.cell.API.CellInterface;
import abstractions.piece.API.PieceInterface;

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

    public final PieceInterface getProtagonist() {
        return this.protagonist;
    }

    public final void setProtagonist(final PieceInterface concernedPiece) {
        this.protagonist = concernedPiece;
    }

    // TODO ? méthode abstraite de l'interface de cell, plutôt
    public abstract void process();

}
