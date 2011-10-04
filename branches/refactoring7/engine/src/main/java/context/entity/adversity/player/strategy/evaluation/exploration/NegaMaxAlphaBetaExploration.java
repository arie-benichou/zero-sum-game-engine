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

package context.entity.adversity.player.strategy.evaluation.exploration;

import java.util.Collections;
import java.util.List;

import context.ContextInterface;
import context.event.MoveInterface;

public final class NegaMaxAlphaBetaExploration implements ExplorationInterface<MoveInterface> {

    /*-------------------------------------8<-------------------------------------*/

    private final int maximalOrdinal;

    @Override
    public int maximalOrdinal() {
        return this.maximalOrdinal;
    }

    /*-------------------------------------8<-------------------------------------*/

    public NegaMaxAlphaBetaExploration(final int maximalOrdinal) {
        this.maximalOrdinal = maximalOrdinal;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ExplorationInterface<MoveInterface> apply() {
        return this;
    }

    @Override
    public ExplorationInterface<MoveInterface> apply(final int maximalOrdinal) {
        return new NegaMaxAlphaBetaExploration(maximalOrdinal);
    }

    /*-------------------------------------8<-------------------------------------*/

    private Double evaluateMovesForOppositeSide(final int i, final List<MoveInterface> options, final int maximalOrdinal, final Double worstScore,
            final Double bestScore) {
        if (bestScore <= worstScore || options.size() == i) return bestScore;
        final MoveInterface option = options.get(i);
        return this.evaluateMovesForOppositeSide(i + 1, options, maximalOrdinal, worstScore,
                Math.min(bestScore, -this.evaluate(option.context(), option, maximalOrdinal - 1, -bestScore, -worstScore)));
    }

    private Double evaluate(final ContextInterface context, final MoveInterface option, final int maximalOrdinal, final Double worstScore,
            final Double bestScore) {

        final ContextInterface newContext = context.apply(option);

        if (newContext.isOver()) return newContext.evaluation();
        if (maximalOrdinal <= 1) return newContext.estimation();

        final ContextInterface newContextForOppositeSide = newContext.apply(context.side().opposite());

        final List<MoveInterface> movesForOppositeSide = newContextForOppositeSide.options();

        /*-------------------------------------8<-------------------------------------*
        System.out.println();
        System.out.println(movesForOppositeSide);
        /*-------------------------------------8<-------------------------------------*/

        Collections.sort(movesForOppositeSide);

        /*-------------------------------------8<-------------------------------------*
        System.out.println();
        System.out.println(movesForOppositeSide);
        /*-------------------------------------8<-------------------------------------*/

        return this.evaluateMovesForOppositeSide(0, movesForOppositeSide, maximalOrdinal, worstScore, bestScore);

    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double evaluate(final ContextInterface context, final MoveInterface option, final int maximalOrdinal) {
        //System.out.println(option + " = ?");
        final Double evaluation = this.evaluate(context, option, maximalOrdinal, -1.0, 1.0); // TODO extract constants
        //System.out.println(option + " = " + evaluation);
        return evaluation;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double evaluate(final ContextInterface context, final MoveInterface option) {
        System.out.println(option + " = ?");
        final Double evaluation = this.evaluate(context, option, this.maximalOrdinal());
        System.out.println(option + " = " + evaluation);
        return evaluation;

    }

    /*-------------------------------------8<-------------------------------------*/

}