
package abstractions.selection;

import java.util.List;
import java.util.Random;

import abstractions.mutation.MutationInterface;

public class RandomItemSelector implements SelectionInterface {

    private Random random = new Random();

    @Override
    public MutationInterface applySelection(final List<MutationInterface> mutations) {
        return mutations.get(random.nextInt(mutations.size()));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
