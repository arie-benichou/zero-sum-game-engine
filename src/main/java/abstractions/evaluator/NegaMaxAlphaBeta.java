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
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class NegaMaxAlphaBeta implements EvaluatorInterface {

    private int cutoffs = 0;

    private ContextInterface context;

    private final Map<Integer, SideInterface> sides = Maps.newHashMapWithExpectedSize(2);

    @Override
    public void injectContext(final ContextInterface context) {
        this.context = context;
    }

    private final int maximalDepth;

    public NegaMaxAlphaBeta(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    private int getMaximalDepth() {
        return this.maximalDepth;
    }

    @Override
    public ContextInterface getContext() {
        return this.context;
    }

    protected int evaluateMutation(final MutationInterface mutation) {
        return this.evaluateMutation(mutation, this.getMaximalDepth(), 1, -Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    protected int evaluateMutation(final MutationInterface mutation, final int profondeur, final Integer side, final int alpha, int bestScore) {
        ///System.out.println();
        ///System.out.println("side " + this.sides.get(side) + " plays " + mutation);
        this.getContext().applyMove(mutation, this.sides.get(side));
        ///System.out.println(this.getContext());
        if (this.getContext().isGameOver())
            bestScore = side * this.getContext().getTerminalEvaluation(this.sides.get(1));
        else if (profondeur == 0)
            bestScore = side * this.getContext().getHeuristicEvaluation(this.sides.get(1));
        else {
            final List<MutationInterface> opponentMutations = this.getContext().getLegalMoves(this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations) {
                bestScore = Math.min(bestScore, -this.evaluateMutation(opponentMutation, profondeur - 1, -side, -bestScore, -alpha));
                if (bestScore <= alpha) {
                    ++this.cutoffs;
                    break;
                }
            }
        }
        this.getContext().unapplyLastPlayedMove(this.sides.get(side));
        ///System.out.println(bestScore);
        return bestScore;
    }

    @Override
    public TreeMap<Integer, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations) {

        this.cutoffs = 0;

        System.out.println("Evaluation des coups légaux du point de vue de: " + this.getContext().getCurrentSide() + " ...");

        //TODO à simplifier
        this.sides.put(1, this.getContext().getCurrentSide());
        this.sides.put(-1, this.getContext().getCurrentSide().getNextSide());

        final TreeMap<Integer, List<MutationInterface>> map = Maps.newTreeMap();
        for (final MutationInterface mutation : mutations) {
            final Integer score = this.evaluateMutation(mutation);
            System.out.println(mutation + "= " + score);
            if (map.containsKey(score))
                map.get(score).add(mutation);
            else
                map.put(score, Lists.newArrayList(mutation));
        }

        System.out.println("nombre de coupures alpha/beta: " + this.cutoffs);

        return map;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}