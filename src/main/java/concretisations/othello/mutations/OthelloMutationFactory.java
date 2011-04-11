
package concretisations.othello.mutations;

import abstractions.cell.ManagedCellInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import concretisations.othello.pieces.OthelloPieceSet;

public class OthelloMutationFactory {

    public static MutationInterface newPawnMutation(final ManagedCellInterface cell, final SideInterface side) {
        return new NewPawnMutation(cell, OthelloMutations.NEW_PAWN, side, OthelloPieceSet.PAWN);
    }
}
