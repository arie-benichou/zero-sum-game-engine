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

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class NegaMax implements EvaluatorInterface {

    // TODO passer le contexte à la méthode applyEvaluation d'un évaluateur
    private ContextInterface context;

    @Override
    public void injectContext(final ContextInterface context) {
        this.context = context;
    }

    @Override
    public final ContextInterface getContext() {
        return this.context;
    }

    private final int maximalDepth;

    public NegaMax(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    @Override
    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    private final int getSideSign(final SideInterface side) {
        return side.equals(this.getContext().getCurrentSide()) ? 1 : -1;
    }

    private final Double evaluate(final MutationInterface move, final SideInterface side, final int depthLeft) {
        this.getContext().applyMove(move, side);
        Double bestScore;
        if (this.getContext().isGameOver())
            bestScore = this.getSideSign(side) * this.getContext().getTerminalEvaluation(this.getContext().getCurrentSide());
        else if (depthLeft == 1)
            bestScore = this.getSideSign(side) * this.getContext().getHeuristicEvaluation(this.getContext().getCurrentSide());
        else {
            bestScore = 1.0; // TODO ! avoid magic numbers
            final List<MutationInterface> opponentMoves = this.getContext().getLegalMoves(side.getNextSide());
            Collections.sort(opponentMoves);
            for (final MutationInterface opponentMove : opponentMoves)
                bestScore = Math.min(bestScore, -this.evaluate(opponentMove, side.getNextSide(), depthLeft - 1));
        }
        this.getContext().unapplyLastPlayedMove(side);
        return bestScore;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations, final int maximalDepth) {
        final TreeMap<Double, List<MutationInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MutationInterface mutation : mutations) {
            final Double score = this.evaluate(mutation, this.getContext().getCurrentSide(), maximalDepth);
            final List<MutationInterface> value = map.get(score);
            if (value == null)
                map.put(score, Lists.newArrayList(mutation));
            else
                value.add(mutation);
        }
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