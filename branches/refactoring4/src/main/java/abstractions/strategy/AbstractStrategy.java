
package abstractions.strategy;

import java.util.List;

import abstractions.evaluation.EvaluationInterface;
import abstractions.mutation.MutationInterface;
import abstractions.selection.SelectionInterface;

public abstract class AbstractStrategy implements StrategyInterface {

    private final EvaluationInterface evaluator;
    private final SelectionInterface selector;

    public AbstractStrategy(final EvaluationInterface evaluator, final SelectionInterface selector) {
        this.evaluator = evaluator;
        this.selector = selector;
    }

    public final EvaluationInterface getEvaluator() {
        return this.evaluator;
    }

    public final SelectionInterface getSelector() {
        return this.selector;
    }

    protected final List<MutationInterface> applyEvaluation(final List<MutationInterface> mutations) {
        return this.evaluator.applyEvaluation(mutations);
    }

    protected final MutationInterface applySelection(final List<MutationInterface> mutations) {
        return this.getSelector().applySelection(mutations);
    }

    @Override
    public String toString() {
        return "Strategy(" + this.evaluator.toString() + ", " + this.selector.toString() + ")";
    }

    public final MutationInterface applyStrategy(final List<MutationInterface> mutations) {
        return this.applySelection(this.applyEvaluation(mutations));
    }

}
