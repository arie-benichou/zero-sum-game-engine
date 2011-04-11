
package abstractions.mutation;

import abstractions.cell.ManagedCellInterface;

public abstract class AbstractAtomicMutation extends AbstractMutation {

    // TODO coder les mutations de bases dans la cellule
    public AbstractAtomicMutation(final ManagedCellInterface cell) {
        super(cell);
    }

    @Override
    public abstract void process();

    @Override
    public void cancel() {
        this.getCell().setPiece(this.getSavedSate());
    }

}
