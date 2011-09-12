
package concretisations.reversi.mutations;

import abstractions.old.mutation.MutationInterface;

public interface OthelloMutationInterface extends MutationInterface {

    int getFirstSideDelta();

    int getSecondSideDelta();

    int getNumberOfPawnsToRevert();

}
