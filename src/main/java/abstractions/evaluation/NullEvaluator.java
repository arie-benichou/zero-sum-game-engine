
package abstractions.evaluation;

import java.util.List;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;

public class NullEvaluator implements EvaluationInterface {

    public List<MutationInterface> applyEvaluation(final List<MutationInterface> mutations) {
        return mutations;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void injectContext(final ContextInterface context) {}

    @Override
    public ContextInterface getContext() {
        return null;
    }

}
