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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class IterativeDeepening implements EvaluatorInterface {

    public int cutOffs = 0;

    // TODO passer le contexte à la méthode applyEvaluation d'un évaluateur
    private ContextInterface context;

    @Override
    public void injectContext(final ContextInterface context) {
        this.evaluator.injectContext(context);
    }

    @Override
    public final ContextInterface getContext() {
        return this.evaluator.getContext();
    }

    @Override
    public int getMaximalDepth() {
        return this.evaluator.getMaximalDepth();
    }

    private final EvaluatorInterface evaluator;

    public IterativeDeepening(final EvaluatorInterface evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(List<MutationInterface> mutations, final int maximalDepth) {
        int maximalLocalDepth = 1;
        TreeMap<Double, List<MutationInterface>> map;
        do {
            map = this.evaluator.applyEvaluation(mutations, maximalLocalDepth);
            mutations = Lists.newArrayList(Iterables.concat(map.values()));
        }
        while (++maximalLocalDepth <= maximalDepth);
        return map;
    }

    @Override
    public final TreeMap<Double, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations) {
        return this.applyEvaluation(mutations, this.getMaximalDepth());
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}