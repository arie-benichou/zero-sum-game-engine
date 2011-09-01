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

public class MiniMaxAlphaBeta implements EvaluatorInterface {

    private ContextInterface context;

    private final Map<Integer, SideInterface> sides = Maps.newHashMapWithExpectedSize(2);

    @Override
    public void injectContext(final ContextInterface context) {
        this.context = context;
    }

    private final int maximalDepth;

    public MiniMaxAlphaBeta(final int maximalDepth) {
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

    protected int evaluateMutation(final MutationInterface mutation, final int profondeur, final Integer side, int alpha, int beta) {

        ///System.out.println();
        ///System.out.println("side " + this.sides.get(side) + " plays " + mutation);

        this.getContext().applyMove(mutation, this.sides.get(side));

        ///System.out.println(this.getContext());

        int bestScore;

        if (this.getContext().isGameOver()) {
            bestScore = side * this.getContext().getTerminalEvaluation(this.sides.get(side));
        }
        else if (profondeur == 0) {
            bestScore = side * this.getContext().getHeuristicEvaluation(this.sides.get(side));
        }
        else if (side == -1) {
            bestScore = -Integer.MAX_VALUE;
            final List<MutationInterface> opponentMutations = this.getContext().getLegalMoves(this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations) {
                bestScore = Math.max(bestScore, this.evaluateMutation(opponentMutation, profondeur - 1, -side, alpha, beta));
                if (bestScore >= beta) {
                    //System.out.println("coupure beta");
                    break;
                }
                else {
                    alpha = Math.max(alpha, bestScore);
                }
            }
        }
        else {
            bestScore = Integer.MAX_VALUE;
            final List<MutationInterface> opponentMutations = this.getContext().getLegalMoves(this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations) {
                bestScore = Math.min(bestScore, this.evaluateMutation(opponentMutation, profondeur - 1, -side, alpha, beta));
                if (alpha >= bestScore) {
                    //System.out.println("coupure alpha");
                    break;
                }
                else {
                    beta = Math.min(beta, bestScore);
                }
            }
        }

        this.getContext().unapplyLastPlayedMove(this.sides.get(side));

        ///System.out.println(bestScore);

        return bestScore;
    }

    @Override
    public TreeMap<Integer, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations) {
        System.out.println(this.getContext().getCurrentSide());
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
        return map;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}