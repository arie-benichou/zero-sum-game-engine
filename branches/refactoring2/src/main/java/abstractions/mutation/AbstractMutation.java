
package abstractions.mutation;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import abstractions.cell.CellInterface;

public abstract class AbstractMutation implements MutationInterface {

    private final int priority;
    private final CellInterface concernedCell;
    
    private transient List<AtomicMutationInterface> sequence = null;

    public AbstractMutation(int priority, CellInterface concernedCell) {
        this.priority = priority;
        this.concernedCell = concernedCell;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final CellInterface getConcernedCell() {
        return concernedCell;
    }

    protected abstract List<AtomicMutationInterface> generateSequence();    
    
    //TODO avoir une seule interface
    public List<AtomicMutationInterface> getSequence() {
        if(this.sequence == null) {
            this.sequence = this.generateSequence();
        }
        return this.sequence;
    }
    
    
    public void process() {
        // TODO lazy init de la séquence dans une variable d'instance
        for(AtomicMutationInterface atomicMutation: this.getSequence()) {
            atomicMutation.process();
        }
    }

    public void cancel() {
        // TODO lazy init de la séquence dans une variable d'instance
        for(AtomicMutationInterface atomicMutation: this.getSequence()) {
            atomicMutation.cancel();
        }
    }    
    

    public final int compareTo(MutationInterface mutation) {
        checkNotNull(mutation, "Argument 'mutation' is not intended to be null.");
        return this.getPriority() - mutation.getPriority();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.concernedCell.getPiece().getSide() + " | "
                + this.concernedCell.getPiece().getClass().getSimpleName() + " | [" + this.concernedCell.getRow() + "][" + this.concernedCell.getColumn() + "]";
    }

}
