
package abstractions.strategy;

import java.util.List;

import abstractions.evaluator.EvaluatorInterface;
import abstractions.mutation.MutationInterface;
import abstractions.selector.SelectorInterface;

public class Strategy implements StrategyInterface {

    private final EvaluatorInterface evaluator;
    private final SelectorInterface selector;

    public Strategy(final EvaluatorInterface evaluator, final SelectorInterface selector) {
        this.evaluator = evaluator;
        this.selector = selector;
    }

    @Override
    public final EvaluatorInterface getEvaluator() {
        return this.evaluator;
    }

    @Override
    public final SelectorInterface getSelector() {
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

    @Override
    public final MutationInterface applyStrategy(final List<MutationInterface> mutations) {
        return this.applySelection(this.applyEvaluation(mutations));
    }

}
