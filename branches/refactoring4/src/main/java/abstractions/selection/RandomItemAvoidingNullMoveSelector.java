
package abstractions.selection;

import java.util.List;
import java.util.Random;

import abstractions.mutation.MutationInterface;

public class RandomItemAvoidingNullMoveSelector implements SelectionInterface {

    private final Random random = new Random();

    @Override
    public MutationInterface applySelection(final List<MutationInterface> mutations) {
        if (mutations.size() == 1)
            return mutations.get(0);
        MutationInterface mutation;
        do
            mutation = mutations.get(this.random.nextInt(mutations.size()));
        while (mutation.isNull());
        return mutation;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
