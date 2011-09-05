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

public class MiniMaxAlphaBeta implements EvaluatorInterface {

    public int cutOffs = 0;

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

    public MiniMaxAlphaBeta(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    @Override
    public final int getMaximalDepth() {
        return this.maximalDepth;
    }

    private final Double evaluate(final MutationInterface move, final SideInterface side, final int depthLeft, Double worstScore, Double bestScore) {
        this.getContext().applyMove(move, side);
        Double localBestScore;
        if (this.getContext().isGameOver())
            localBestScore = this.getContext().getTerminalEvaluation(this.getContext().getCurrentSide());
        else if (depthLeft == 1)
            localBestScore = this.getContext().getHeuristicEvaluation(this.getContext().getCurrentSide());
        else {
            final List<MutationInterface> opponentMoves = this.getContext().getLegalMoves(side.getNextSide());
            Collections.sort(opponentMoves);
            if (side.equals(this.getContext().getCurrentSide())) {
                localBestScore = bestScore;
                for (final MutationInterface opponentMutation : opponentMoves) {
                    localBestScore = Math.min(localBestScore, this.evaluate(opponentMutation, side.getNextSide(), depthLeft - 1, worstScore, bestScore));
                    if (localBestScore <= worstScore) {
                        ++this.cutOffs;
                        break;
                    }
                    else
                        bestScore = Math.min(bestScore, localBestScore);
                }
            }
            else {
                localBestScore = worstScore;
                for (final MutationInterface opponentMutation : opponentMoves) {
                    localBestScore = Math.max(localBestScore, this.evaluate(opponentMutation, side.getNextSide(), depthLeft - 1, worstScore, bestScore));
                    if (localBestScore >= bestScore) {
                        ++this.cutOffs;
                        break;
                    }
                    else
                        worstScore = Math.max(worstScore, localBestScore);
                }
            }
        }
        this.getContext().unapplyLastPlayedMove(side);
        return localBestScore;
    }

    @Override
    public TreeMap<Double, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations, final int maximalDepth) {
        final TreeMap<Double, List<MutationInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MutationInterface mutation : mutations) {
            final Double score = this.evaluate(mutation, this.getContext().getCurrentSide(), maximalDepth, -1.0, 1.0);
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
    public String toString() {
        return this.getClass().getSimpleName();
    }

}