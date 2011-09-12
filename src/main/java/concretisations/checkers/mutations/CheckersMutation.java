
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.direction.DirectionInterface;
import abstractions.old.cell.ManagedCellInterface;
import abstractions.old.mutation.AbstractCompositeMutation;
import abstractions.old.mutation.MutationInterface;
import abstractions.old.mutation.MutationTypeInterface;

public abstract class CheckersMutation extends AbstractCompositeMutation implements CheckersMutationInterface {

    private final DirectionInterface direction;

    public CheckersMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
        super(cell, mutationType);
        this.direction = direction;
    }

    public final DirectionInterface getDirection() {
        return this.direction;
    }

    @Override
    protected abstract List<MutationInterface> sequence();

    @Override
    public final int hashCode() {

        int hashCode = 17;

        hashCode *= 31;
        hashCode += this.getType().hashCode();

        hashCode *= 31;
        hashCode += this.getPosition().hashCode();

        hashCode *= 31;
        hashCode += this.getDirection().hashCode();

        return hashCode;
    }

    @Override
    public final boolean equals(final Object object) {
        final boolean isEqual; // NOPMD
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof CheckersMutation)) { // NOPMD
            isEqual = false;
        }
        else {
            // TODO ! revoir la méthode equals() d'une cellule
            final CheckersMutation that = (CheckersMutation) object;
            isEqual =
                    that.getType().equals(this.getType())
                            &&
                            that.getDirection().getRowDelta() == this.getDirection().getRowDelta()
                            &&
                            that.getDirection().getColumnDelta() == this.getDirection().getColumnDelta()
                            &&
                            that.getPosition().getPosition().equals(this.getPosition().getPosition());
        }
        return isEqual;
    }
}
