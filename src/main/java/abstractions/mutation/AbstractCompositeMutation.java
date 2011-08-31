
package abstractions.mutation;

import java.util.List;

import abstractions.cell.ManagedCellInterface;

public abstract class AbstractCompositeMutation extends AbstractMutation {

    public AbstractCompositeMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType) {
        super(cell, mutationType);
    }

    private transient List<MutationInterface> sequence; // NOPMD 

    private List<MutationInterface> getSequence() {
        if (this.sequence == null) {
            this.sequence = this.sequence();
        }
        return this.sequence;
    }

    protected abstract List<MutationInterface> sequence();

    @Override
    public final MutationInterface process() {
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.process();
        }
        return this;
    }

    @Override
    public final void cancel() {
        // TODO Cut-off si la mutation n'a pas été processée.
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.cancel();
        }
    }

}
