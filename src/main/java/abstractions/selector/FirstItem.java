
package abstractions.selector;

import java.util.List;

import abstractions.mutation.MutationInterface;

public class FirstItem implements SelectorInterface {

    @Override
    public MutationInterface applySelection(final List<MutationInterface> mutations) {
        return mutations.get(0);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
