
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.BasicMutationFactory;
import abstractions.mutation.MutationInterface;
import abstractions.piece.PieceTypeInterface;
import abstractions.position.PositionManager.DirectionInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.ImmutableList;

// TODO ? utiliser une ManyJumpMutation
public class WalkMutation extends CheckersMutation {

    public WalkMutation(final ManagedCellInterface cell, final SideInterface side, final PieceTypeInterface pieceType, final DirectionInterface direction) {
        super(cell, side, pieceType, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(
                BasicMutationFactory.newDeath(this.getCell()),
                BasicMutationFactory.newBirth(this.getCell().getRelative(this.getDirection()), this.getCell().getPiece())
                );
    }
}