
package abstractions.mutation;

import abstractions.cell.ManagedCellInterface;

public abstract class AbstractAtomicMutation extends AbstractMutation {

    // TODO ! coder les mutations de bases dans la cellule
    public AbstractAtomicMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        super(cell, mutationType);
    }

    @Override
    public abstract MutationInterface process();

    @Override
    public final void cancel() {
        this.getCell().setPiece(this.getSavedSate());
    }

}
