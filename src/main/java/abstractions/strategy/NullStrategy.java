
package abstractions.strategy;

import java.util.List;

import abstractions.evaluation.EvaluationInterface;
import abstractions.evaluation.NullEvaluator;
import abstractions.mutation.MutationInterface;
import abstractions.selection.NullSelector;
import abstractions.selection.SelectionInterface;

public class NullStrategy implements StrategyInterface {

    private final EvaluationInterface evaluator;
    private final SelectionInterface selector;

    protected NullStrategy(final EvaluationInterface evaluator, final SelectionInterface selector) {
        this.evaluator = evaluator;
        this.selector = selector;
    }

    public NullStrategy() {
        this(new NullEvaluator(), new NullSelector());
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

    protected final List<MutationInterface> applySelection(final List<MutationInterface> mutations) {
        return this.selector.applySelection(mutations);
    }

    @Override
    public String toString() {
        return "Strategy(" + this.evaluator.toString() + ", " + this.selector.toString() + ")";
    }

    public final List<MutationInterface> applyStrategy(final List<MutationInterface> mutations) {
        return this.applySelection(this.applyEvaluation(mutations));
    }

}
