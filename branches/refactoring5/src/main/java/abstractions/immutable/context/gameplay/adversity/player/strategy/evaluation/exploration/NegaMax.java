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

package abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation.exploration;

import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.side.SideInterface;
import annotations.Immutable;

@Immutable
public class NegaMax<T> implements ExplorationInterface<T> {

    private final int maximalDepth;

    public NegaMax(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    @Override
    public int getMaximalDepth() {
        return this.maximalDepth;
    }

    private Double evaluate(final ContextInterface context, final T option, final int maximalDepth, final SideInterface side) {
        context.applyOption(option, side);
        Double bestEvaluation;
        if (context.isOver())
            //localBestScore = (side.equals(context.getCurrentSide()) ? 1 : -1) * context.getTerminalEvaluation(context.getCurrentSide());
            bestEvaluation = context.getTerminalEvaluation(side);
        else if (maximalDepth == 1)
            //localBestScore = (side.equals(context.getCurrentSide()) ? 1 : -1) * context.getHeuristicEvaluation(context.getCurrentSide());
            bestEvaluation = context.getHeuristicEvaluation(side);
        else {
            bestEvaluation = 1.0;
            for (final T nextSideOption : context.getOptions(side.getNextSide()))
                bestEvaluation = Math.min(bestEvaluation, -this.evaluate(context, nextSideOption, side.getNextSide(), maximalDepth - 1));
        }
        context.unapplyLastPlayedMove();
        return bestEvaluation;
    }

    @Override
    public Double evaluate(final ContextInterface context, final T option, final int maximalDepth) {
        return this.evaluate(context, option, maximalDepth, context.side());
    }

}