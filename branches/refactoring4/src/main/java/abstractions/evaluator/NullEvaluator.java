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

// TODO ? classe AbstractEvaluator
public class NullEvaluator implements EvaluatorInterface {

    private final static Double NULL_EVALUATION = 0.0;

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations,
            final int maximalDepth) {
        final TreeMap<Double, List<MutationInterface>> evaluatedMutations = Maps.newTreeMap();
        evaluatedMutations.put(NullEvaluator.NULL_EVALUATION, mutations);
        return evaluatedMutations;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations) {
        return this.applyEvaluation(context, mutations, 0);
    }

    @Override
    public int getMaximalDepth() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}