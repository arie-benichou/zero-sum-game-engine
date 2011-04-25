
package abstractions.selection;

import java.util.List;

import abstractions.mutation.MutationInterface;

import com.google.common.collect.Lists;

// TODO utiliser une classe abstraite ?
public class FirstItemSelector extends NullSelector {

    private final List<MutationInterface> selection = Lists.newArrayListWithCapacity(1);

    @Override
    public List<MutationInterface> applySelection(final List<MutationInterface> mutations) {
        this.selection.set(0, mutations.get(0));
        return this.selection;
    }

}
