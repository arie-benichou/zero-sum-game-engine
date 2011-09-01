
package abstractions.selector;

import java.util.List;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Random implements SelectorInterface {

    private final java.util.Random random = new java.util.Random();

    @Override
    public MutationInterface applySelection(final TreeMap<Integer, List<MutationInterface>> evaluatedMutations) {
        final List<MutationInterface> mutations = Lists.newArrayList(Iterables.concat(evaluatedMutations.values()));
        return mutations.get(this.random.nextInt(mutations.size()));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
