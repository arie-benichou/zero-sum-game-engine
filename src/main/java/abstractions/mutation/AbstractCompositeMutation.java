
package abstractions.mutation;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.side.SideInterface;

public abstract class AbstractCompositeMutation extends AbstractMutation {

    public AbstractCompositeMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType) {
        super(cell, side, pieceType);
    }

    private transient List<MutationInterface> sequence;

    private List<MutationInterface> getSequence() {
        if (this.sequence == null) {
            this.sequence = this.sequence();
        }
        return this.sequence;
    }

    protected abstract List<MutationInterface> sequence();

    @Override
    public void process() {
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.process();
        }
    }

    @Override
    public void cancel() {
        for (final MutationInterface mutation : this.getSequence()) {
            mutation.cancel();
        }
    }

}
