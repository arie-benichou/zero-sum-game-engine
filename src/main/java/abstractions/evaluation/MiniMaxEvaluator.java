
package abstractions.evaluation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MiniMaxEvaluator implements EvaluationInterface {

    private ContextInterface context;

    private Map<Integer, SideInterface> sides = Maps.newHashMapWithExpectedSize(2);

    @Override
    public void injectContext(ContextInterface context) {
        this.context = context;
    }

    private final int maximalDepth;

    public MiniMaxEvaluator(int maximalDepth) {
        this.maximalDepth = maximalDepth;
    }

    private int getMaximalDepth() {
        return maximalDepth;
    }

    @Override
    public ContextInterface getContext() {
        return this.context;
    }

    protected int evaluateMutation(final MutationInterface mutation) {
        return this.evaluateMutation(mutation, this.getMaximalDepth(), 1);
    }

    protected int evaluateMutation(final MutationInterface mutation, final int profondeur, Integer side) {

        mutation.process(); // TODO passer par le contexte (doMove())
        MutationInterface previousLastPlayerMove = this.getContext().setLastPlayerMove(this.sides.get(side), mutation);

        //System.out.println(this.getContext());

        int bestScore;

        if (this.getContext().getReferee().isGameOver(this.getContext(), mutation)) {
            SideInterface winner = this.getContext().getReferee().getWinner(getContext());
            //System.out.println("Game Over!" + "the winner would be: " + winner);
            bestScore = winner.equals(Sides.NULL) ? 0 : (winner.equals(this.sides.get(side)) ? 1001 : -1001);
        }
        else if (profondeur == 1)
            bestScore = this.getContext().getReferee().evaluate(this.getContext(), this.sides.get(side));
        else {
            bestScore = -1001;
            final List<MutationInterface> opponentMutations = this.getContext().getReferee()
                    .getLegalMoves(this.getContext(), this.sides.get(side).getNextSide());
            for (final MutationInterface opponentMutation : opponentMutations)
                bestScore = Math.max(bestScore, -this.evaluateMutation(opponentMutation, profondeur - 1, -side));
        }

        mutation.cancel(); // TODO passer par le contexte (undoMove())
        this.getContext().setLastPlayerMove(this.sides.get(side), previousLastPlayerMove);

        //System.out.println(bestScore);

        return bestScore;
    }

    public List<MutationInterface> applyEvaluation(final List<MutationInterface> mutations) {
        
        System.out.println(this.getContext().getCurrentSide());

        sides.put(1, this.getContext().getCurrentSide());
        sides.put(-1, this.getContext().getCurrentSide().getNextSide());        

        Map<Integer, List<MutationInterface>> map = Maps.newTreeMap();
        
        for (final MutationInterface mutation : mutations) {
            final Integer score = this.evaluateMutation(mutation);
            System.out.println(score);
            if (!map.containsKey(score))
                map.put(score, Lists.newArrayList(mutation));
            else {
                map.get(score).add(mutation);
            }
        }

        System.out.println(map);

        // TODO à améliorer
        List<MutationInterface> evaluatedMutations = Lists.newArrayList();
        for (final List<MutationInterface> e : map.values()) {
            evaluatedMutations.addAll(e);
        }
        
        Collections.reverse(evaluatedMutations);
        System.out.println(evaluatedMutations);

        return evaluatedMutations;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}