/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package abstractions.selector;

import java.util.List;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

// TODO vérifier le randomize => unit test
public abstract class AbstractSelector implements SelectorInterface {

    private final java.util.Random random = new java.util.Random();

    /**
     * If set to True, use random selection on a selection of mutations with the
     * same evaluation.
     */
    private final boolean randomOnSameEvaluation;

    /**
     * If set to True, try to avoid selection of a null mutation.
     */
    private final boolean avoidNullMutation;

    public AbstractSelector(final boolean randomOnSameEvaluation, final boolean avoidNullMutation) {
        this.randomOnSameEvaluation = randomOnSameEvaluation;
        this.avoidNullMutation = avoidNullMutation;
    }

    public abstract List<MutationInterface> select(final TreeMap<Double, List<MutationInterface>> evaluatedMutations);

    private MutationInterface doSelection(final List<MutationInterface> selection, final int numberOfOptions) {
        int index = 0;
        MutationInterface selectedMutation = selection.get(index);
        if (numberOfOptions > 1) {
            if (this.randomOnSameEvaluation) {
                index = this.random.nextInt(numberOfOptions);
                selectedMutation = selection.get(index);
            }
            if (this.avoidNullMutation && selectedMutation.isNull())
                selectedMutation = selection.get((index + 1) % numberOfOptions);
        }
        return selectedMutation;
    }

    @Override
    public MutationInterface applySelection(final TreeMap<Double, List<MutationInterface>> evaluatedMutations) {
        final List<MutationInterface> selection = this.select(evaluatedMutations);
        return this.doSelection(selection, selection.size());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}