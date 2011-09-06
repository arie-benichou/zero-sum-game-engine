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
import java.util.Map;
import java.util.TreeMap;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import annotations.Immutable;

import com.google.common.collect.Maps;

@Immutable
public class CachingEvaluator implements EvaluatorInterface {

    private EvaluatorInterface evaluator;
    private final Map<String, TreeMap<Double, List<MutationInterface>>> cache = Maps.newHashMap();

    @Override
    public int getMaximalDepth() {
        return this.evaluator.getMaximalDepth();
    }

    public CachingEvaluator(final EvaluatorInterface evaluator) {
        this.evaluator = evaluator;
    }

    public void setEvaluator(final EvaluatorInterface evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations,
            final int maximalDepth) {
        // TODO amélioration possible si découpage de la zone utile de la grille entre autre...
        final String hash = maximalDepth + context.getCellManager().asString() + mutations.toString();
        TreeMap<Double, List<MutationInterface>> result = this.cache.get(hash);
        if (result == null) {
            result = this.evaluator.applyEvaluation(context, mutations, maximalDepth);
            this.cache.put(hash, result);
        }
        return result;
    }

    @Override
    public final TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations) {
        return this.applyEvaluation(context, mutations, this.getMaximalDepth());
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}