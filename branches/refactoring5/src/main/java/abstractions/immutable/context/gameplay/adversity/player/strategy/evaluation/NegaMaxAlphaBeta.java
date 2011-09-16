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

import java.util.List;
import java.util.TreeMap;

import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.side.SideInterface;
import abstractions.old.mutation.MutationInterface;
import annotations.Immutable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Immutable
public class NegaMaxAlphaBeta<T> implements EvaluationInterface<T> {

    private final int maximalDepth;

    public NegaMaxAlphaBeta(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    @Override
    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    private final Double evaluate(final ContextInterface context, final MutationInterface move, final SideInterface side, final int depthLeft,
            final double worstScore, final double bestScore) {
        context.applyOption(move, side);

        //System.out.println(context);

        Double localBestScore;
        if (context.isOver())
            localBestScore = (side.equals(context.side()) ? 1 : -1) * context.getTerminalEvaluation(context.side());
        else if (depthLeft == 1)
            localBestScore = (side.equals(context.side()) ? 1 : -1) * context.getHeuristicEvaluation(context.side());
        else {
            localBestScore = bestScore;
            for (final MutationInterface opponentMove : context.getOptions(side.getNextSide())) {
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
    public List<List<T>> evaluate(final ContextInterface context, final List<T> options, final int maximalDepth) {
        final TreeMap<Double, List<T>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final T option : options) {
            final Double score = this.evaluate(context, option, context.side(), maximalDepth, -1.0, 1.0);
            final List<T> value = map.get(score);
            if (value == null)
                map.put(score, Lists.newArrayList(option));
            else
                value.add(option);
        }
        final List<List<T>> evaluation = Lists.newArrayList();
        for (final List<T> items : map.values())
            evaluation.add(items);
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