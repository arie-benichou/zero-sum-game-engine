
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

// TODO ? utiliser une ManyJumpMutation
public class JumpMutation extends CheckersMutation {

    // TODO virer le pieceType
    public JumpMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType, final DirectionInterface direction) {
        super(cell, side, pieceType, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(

        AtomicMutationFactory.newDeath(this.getCell()),

        AtomicMutationFactory.newBirth(this.getCell().getRelative(this.getDirection()).getRelative(this.getDirection()), this.getCell().getPiece()),

        AtomicMutationFactory.newDeath(this.getCell().getRelative(this.getDirection()))

        );
    }
}