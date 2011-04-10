
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.AbstractCompositeMutation;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

public abstract class CheckersMutation extends AbstractCompositeMutation implements CheckersMutationInterface {

    private final DirectionInterface direction;

    public CheckersMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType, final DirectionInterface direction) {
        super(cell, side, pieceType);
        this.direction = direction;
    }

    @Override
    public final DirectionInterface getDirection() {
        return this.direction;
    }

    @Override
    protected abstract List<MutationInterface> sequence();

    @Override
    public final String toString() {
        return super.toString() + " | " + this.direction;
    }

}
