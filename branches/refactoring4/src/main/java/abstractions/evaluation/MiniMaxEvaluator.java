
package abstractions.evaluation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MiniMaxEvaluator implements EvaluationInterface {

    private ContextInterface context;

    private final Map<Integer, SideInterface> sides = Maps.newHashMapWithExpectedSize(2);

    @Override
    public void injectContext(final ContextInterface context) {
        this.context = context;
    }

    private final int maximalDepth;

    public MiniMaxEvaluator(final int maximalDepth) {
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
        return this.evaluateMutation(mutation, this.getMaximalDepth(), 1);
    }

    protected int evaluateMutation(final MutationInterface mutation, final int profondeur, final Integer side) {

        ///System.out.println();
        ///System.out.println("side " + this.sides.get(side) + " plays " + mutation);

        this.getContext().applyMove(mutation, this.sides.get(side));

        ///System.out.println(this.getContext());

        int bestScore;

        if (this.getContext().isGameOver()) {
            ///System.out.println("Game Over!");
            //bestScore = this.getContext().getTerminalEvaluation(this.context.getCurrentSide());
            //System.out.println(side);
            bestScore = side * this.getContext().getTerminalEvaluation(this.sides.get(side));
            //System.out.println(bestScore);
        }
        else if (profondeur == 1) {
            ///System.out.println("Maximal depth");
            //bestScore = this.getContext().getHeuristicEvaluation(this.context.getCurrentSide());
            bestScore = side * this.getContext().getHeuristicEvaluation(this.sides.get(side));
            //System.out.println(bestScore);
        }
        else if (side == -1) {
            bestScore = -1001;
            final List<MutationInterface> opponentMutations = this.getContext().getLegalMoves(this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations) {
                final int currentScore = this.evaluateMutation(opponentMutation, profondeur - 1, -side);
                ///System.out.println(currentScore);
                if (currentScore > bestScore) {
                    ///System.out.println("currentScore > bestScore : " + currentScore + " > " + bestScore);
                    bestScore = currentScore;
                }
                ///System.out.println(bestScore);
            }
        }
        else {
            bestScore = 1001;
            final List<MutationInterface> opponentMutations = this.getContext().getLegalMoves(this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations) {
                final int currentScore = this.evaluateMutation(opponentMutation, profondeur - 1, -side);
                ///System.out.println(currentScore);
                if (currentScore < bestScore) {
                    ///System.out.println("currentScore < bestScore : " + currentScore + " < " + bestScore);
                    bestScore = currentScore;
                }
                ///System.out.println(bestScore);
            }
        }

        this.getContext().unapplyLastPlayedMove(this.sides.get(side));

        ///System.out.println(bestScore);

        return bestScore;
    }

    @Override
    public List<MutationInterface> applyEvaluation(final List<MutationInterface> mutations) {

        System.out.println(this.getContext().getCurrentSide());

        this.sides.put(1, this.getContext().getCurrentSide());
        this.sides.put(-1, this.getContext().getCurrentSide().getNextSide());

        final Map<Integer, List<MutationInterface>> map = Maps.newTreeMap();

        for (final MutationInterface mutation : mutations) {

            System.out.println();
            System.out.println(mutation + "= ?");

            final Integer score = this.evaluateMutation(mutation);
            System.out.println(mutation + "= " + score);

            if (!map.containsKey(score))
                map.put(score, Lists.newArrayList(mutation));
            else
                map.get(score).add(mutation);

        }

        //System.out.println();
        //System.out.println(map);

        // TODO à améliorer
        final List<MutationInterface> evaluatedMutations = Lists.newArrayList();
        for (final List<MutationInterface> e : map.values())
            if (e.size() > 1)
                for (final MutationInterface m : e) {
                    if (!m.isNull())
                        evaluatedMutations.add(m);
                }
            else
                evaluatedMutations.addAll(e);

        Collections.reverse(evaluatedMutations);

        System.out.println();
        //System.out.println(evaluatedMutations);

        return evaluatedMutations;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}