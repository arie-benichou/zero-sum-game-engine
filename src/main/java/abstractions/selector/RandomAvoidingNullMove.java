
package abstractions.selector;

import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class RandomAvoidingNullMove implements SelectorInterface {

    private final Random random = new Random();

    @Override
    public MutationInterface applySelection(final TreeMap<Integer, List<MutationInterface>> evaluatedMutations) {

        final List<MutationInterface> mutations = Lists.newArrayList(Iterables.concat(evaluatedMutations.values()));
        final int size = mutations.size();

        if (size == 1)
            return mutations.get(0);

        MutationInterface mutation;
        do
            mutation = mutations.get(this.random.nextInt(size));
        while (mutation.isNull());

        return mutation;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}