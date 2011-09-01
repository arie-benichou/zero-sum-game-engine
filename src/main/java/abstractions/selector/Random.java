
package abstractions.selector;

import java.util.List;

import abstractions.mutation.MutationInterface;

public class Random implements SelectorInterface {

    private final java.util.Random random = new java.util.Random();

    @Override
    public MutationInterface applySelection(final List<MutationInterface> mutations) {
        return mutations.get(this.random.nextInt(mutations.size()));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
