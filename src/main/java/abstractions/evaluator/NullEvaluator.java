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

package abstractions.evaluator;

import java.util.List;
import java.util.TreeMap;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;

import com.google.common.collect.Maps;

public class NullEvaluator implements EvaluatorInterface {

    private final static int NULL_EVALUATION = 0;

    @Override
    public TreeMap<Integer, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations) {
        final TreeMap<Integer, List<MutationInterface>> evaluatedMutations = Maps.newTreeMap();
        evaluatedMutations.put(NullEvaluator.NULL_EVALUATION, mutations);
        return evaluatedMutations;
    }

    @Override
    public void injectContext(final ContextInterface context) {}

    @Override
    public ContextInterface getContext() {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}