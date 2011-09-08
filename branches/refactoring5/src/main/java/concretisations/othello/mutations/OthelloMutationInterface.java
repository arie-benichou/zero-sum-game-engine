
package concretisations.othello.mutations;

import abstractions.mutation.MutationInterface;

public interface OthelloMutationInterface extends MutationInterface {

    int getFirstSideDelta();

    int getSecondSideDelta();

    int getNumberOfPawnsToRevert();

}
