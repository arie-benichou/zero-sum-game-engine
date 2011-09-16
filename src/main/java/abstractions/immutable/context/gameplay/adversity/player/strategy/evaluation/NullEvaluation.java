
package abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation;

import java.util.List;

import abstractions.immutable.context.ContextInterface;

import com.google.common.collect.Lists;

public class NullEvaluation<T> implements EvaluationInterface<T> {

    @Override
    public List<List<T>> evaluate(final ContextInterface context, final List<T> options, final int maximalDepth) {
        final List<List<T>> evaluation = Lists.newArrayList();
        evaluation.add(options);
        return evaluation;
    }

    @Override
    public List<List<T>> evaluate(final ContextInterface context, final List<T> options) {
        return this.evaluate(context, options, this.getMaximalDepth());
    }

    @Override
    public int getMaximalDepth() {
        return 0;
    }

}