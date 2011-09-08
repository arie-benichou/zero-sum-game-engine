
package concretisations.checkers.mutations;

import java.util.List;

import abstractions.cell.ManagedCellInterface;
import abstractions.direction.DirectionInterface;
import abstractions.mutation.AtomicMutationFactory;
import abstractions.mutation.MutationInterface;

import com.google.common.collect.ImmutableList;

import concretisations.checkers.pieces.CheckersPieceSet;

// TODO ? implémenter l'interface CheckersMutationInterface et hériter de AlterationMutation ou bien supprimer Alteration
public final class KingMutation extends CheckersMutation { 

    //public KingMutation(final ManagedCellInterface cell, final MutationTypeInterface mutationType, final DirectionInterface direction) {
    public KingMutation(final ManagedCellInterface cell, final DirectionInterface direction) {
        super(cell, CheckersMutations.KING, direction);
    }

    @Override
    protected List<MutationInterface> sequence() {
        return ImmutableList.of(
                AtomicMutationFactory.newAlteration(this.getCell(), this.getCell().getPiece().getSide(), CheckersPieceSet.KING)
                //AtomicMutationFactory.newBirth(this.getCell().getNeighbour(this.getDirection()), this.getCell().getPiece().getSide(), this.getCell().getPiece().getType()),
                //AtomicMutationFactory.newDeath(this.getCell())
                );
    }
}