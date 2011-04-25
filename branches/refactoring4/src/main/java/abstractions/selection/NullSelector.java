
package abstractions.selection;

import java.util.List;

import abstractions.mutation.MutationInterface;

public class NullSelector implements SelectionInterface {

    public List<MutationInterface> applySelection(final List<MutationInterface> mutations) {
        return mutations;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}
