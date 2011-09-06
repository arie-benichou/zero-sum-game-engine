
package concretisations.othello.mutations;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.mutation.MutationTypeInterface;
import abstractions.piece.PieceInterface;
import abstractions.position.PositionInterface;

public class NullMutation implements OthelloMutationInterface {

    private static MutationInterface INSTANCE = new NullMutation();

    public static MutationInterface getInstance() {
        return NullMutation.INSTANCE;
    }

    private NullMutation() {}

    @Override
    public PositionInterface getPosition() {
        return null;
    }

    @Override
    public MutationTypeInterface getType() {
        return OthelloMutations.NUll;
    }

    @Override
    public PieceInterface getSavedSate() {
        return null;
    }

    @Override
    public MutationInterface process(final ContextInterface context) {
        return this;
    }

    @Override
    public MutationInterface cancel(final ContextInterface context) {
        return this;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public int getFirstSideDelta() {
        return 0;
    }

    @Override
    public int getSecondSideDelta() {
        return 0;
    }

    @Override
    public int getNumberOfPawnsToRevert() {
        return 0;
    }

    @Override
    public int compareTo(final MutationInterface o) {
        return 1; // for descending order
    }

    @Override
    public String toString() {
        return "NULL MOVE";
    }

    @Override
    public void computeSequence(final ContextInterface context) {}

}