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

package abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation;

import java.util.ArrayList;
import java.util.List;

import abstractions.immutable.context.ContextInterface;
import annotations.Immutable;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Immutable
public class IterativeDeepening<T> implements EvaluationInterface<T> {

    private final EvaluationInterface<T> evaluator;

    @Override
    public int getMaximalDepth() {
        return this.evaluator.getMaximalDepth();
    }

    public IterativeDeepening(final EvaluationInterface<T> evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public List<List<T>> evaluate(final ContextInterface context, final List<T> options, final int maximalDepth) {
        int maximalLocalDepth = 0;
        List<List<T>> evaluation = new ArrayList<List<T>>();
        evaluation.add(options);
        while (maximalLocalDepth < maximalDepth)
            evaluation = this.evaluator.evaluate(context, Lists.newArrayList(Iterables.concat(evaluation)), ++maximalLocalDepth);
        return evaluation;
    }

    @Override
    public List<List<T>> evaluate(final ContextInterface context, final List<T> options) {
        return this.evaluate(context, options, this.getMaximalDepth());
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}