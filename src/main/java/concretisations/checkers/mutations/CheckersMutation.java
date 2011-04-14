
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AbstractCompositeMutation;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;

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
    public final String toString() {
        return super.toString() + " | " + this.getDirection();
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode *= 31;
        hashCode += this.getType().hashCode();

        hashCode *= 31;
        hashCode += this.getCell().hashCode();

        hashCode *= 31;
        hashCode += this.getDirection().hashCode();

        return hashCode;
    }

    @Override
    public boolean equals(final Object object) {
        final boolean isEqual;
        if (object == this) {
            isEqual = true;
        }
        else if (object == null) {
            isEqual = false;
        }
        else if (!(object instanceof CheckersMutation)) {
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
                            that.getCell().getPosition().equals(this.getCell().getPosition());
        }
        return isEqual;
    }
}
