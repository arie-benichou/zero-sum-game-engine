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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class IterativeDeepening implements EvaluatorInterface {

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

    public IterativeDeepening(final int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    //TODO ? rajouter à l'interface d'un évaluateur
    private final int getMaximalDepth() {
        return this.maximalDepth;
    }

    private final int getSideSign(final SideInterface side) {
        return side.equals(this.getContext().getCurrentSide()) ? 1 : -1;
    }

    private final Double evaluate(final MutationInterface move, final SideInterface side, final int depthLeft, final Double worstScore, final Double bestScore) {
        this.getContext().applyMove(move, side);
        Double localBestScore;
        if (this.getContext().isGameOver())
            localBestScore = this.getSideSign(side) * this.getContext().getTerminalEvaluation(this.getContext().getCurrentSide());
        else if (depthLeft == 1)
            localBestScore = this.getSideSign(side) * this.getContext().getHeuristicEvaluation(this.getContext().getCurrentSide());
        else {
            localBestScore = bestScore;

            final List<MutationInterface> opponentMoves = this.getContext().getLegalMoves(side.getNextSide());
            ///Collections.sort(opponentMoves);

            for (final MutationInterface opponentMove : opponentMoves) {
                localBestScore = Math.min(localBestScore, -this.evaluate(opponentMove, side.getNextSide(), depthLeft - 1, -localBestScore, -worstScore));
                if (localBestScore <= worstScore) {
                    ++this.cutOffs;
                    break;
                }

            }
        }
        this.getContext().unapplyLastPlayedMove(side);
        return localBestScore;
    }

    public final TreeMap<Double, List<MutationInterface>> iterativeDeepening(final List<MutationInterface> mutations, final int depthLeft) {
        ///System.out.print("Evaluation des coups légaux du point de vue de: ");
        ///System.out.println(this.getContext().getCurrentSide() + " à une profondeur de " + depthLeft + " ...");
        final TreeMap<Double, List<MutationInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MutationInterface mutation : mutations) {
            final Double score = this.evaluate(mutation, this.getContext().getCurrentSide(), depthLeft, -1.0, 1.0);
            ///System.out.println(mutation + " = " + score);
            final List<MutationInterface> value = map.get(score);
            if (value == null)
                map.put(score, Lists.newArrayList(mutation));
            else
                value.add(mutation);
        }
        ///System.out.println();
        return map;
    }

    @Override
    public final TreeMap<Double, List<MutationInterface>> applyEvaluation(final List<MutationInterface> mutations) {

        int maximalLocalDepth = 1;

        TreeMap<Double, List<MutationInterface>> map;

        List<MutationInterface> orderedMutations = mutations; // TODO utiliser Ordering.sortedCopy...

        ///Collections.sort(orderedMutations);

        do {
            map = this.iterativeDeepening(orderedMutations, maximalLocalDepth);
            orderedMutations = Lists.newArrayList(Iterables.concat(map.values()));
        }
        while (++maximalLocalDepth <= this.getMaximalDepth());
        ///System.out.println();
        return map;
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}