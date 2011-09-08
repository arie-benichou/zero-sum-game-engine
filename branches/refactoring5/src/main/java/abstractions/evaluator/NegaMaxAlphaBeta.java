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
import abstractions.side.SideInterface;
import annotations.Immutable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Immutable
public class NegaMaxAlphaBeta implements EvaluatorInterface {

    private final int maximalDepth;

    public NegaMaxAlphaBeta(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    @Override
    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    private final Double evaluate(final ContextInterface context, final MutationInterface move, final SideInterface side, final int depthLeft,
            final Double worstScore, final Double bestScore) {
        context.applyMove(move, side);
        Double localBestScore;
        if (context.isGameOver())
            localBestScore = (side.equals(context.getCurrentSide()) ? 1 : -1) * context.getTerminalEvaluation(context.getCurrentSide());
        else if (depthLeft == 1)
            localBestScore = (side.equals(context.getCurrentSide()) ? 1 : -1) * context.getHeuristicEvaluation(context.getCurrentSide());
        else {
            localBestScore = bestScore;
            for (final MutationInterface opponentMove : context.getSortedLegalMoves(side.getNextSide())) {
                localBestScore = Math.min(localBestScore,
                        -this.evaluate(context, opponentMove, side.getNextSide(), depthLeft - 1, -localBestScore, -worstScore));
                if (localBestScore <= worstScore)
                    break;
            }
        }
        context.unapplyLastPlayedMove(side);
        return localBestScore;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations,
            final int maximalDepth) {
        final TreeMap<Double, List<MutationInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MutationInterface mutation : mutations) {
            final Double score = this.evaluate(context, mutation, context.getCurrentSide(), maximalDepth, WORST_EVALUATION, BEST_EVALUATION);
            final List<MutationInterface> value = map.get(score);
            if (value == null)
                map.put(score, Lists.newArrayList(mutation));
            else
                value.add(mutation);
        }
        return map;
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