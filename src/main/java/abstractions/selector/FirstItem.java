
package abstractions.selector;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

public class FirstItem implements SelectorInterface {

    private final java.util.Random random = new Random();

    @Override
    public MutationInterface applySelection(final TreeMap<Integer, List<MutationInterface>> evaluatedMutations) {
        final List<MutationInterface> item = evaluatedMutations.firstEntry().getValue();
        final int size = item.size();
        return size == 0 ? item.get(0) : item.get(this.random.nextInt(size));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
