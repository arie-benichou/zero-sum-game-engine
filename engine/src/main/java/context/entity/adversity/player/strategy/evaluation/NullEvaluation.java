
package context.entity.adversity.player.strategy.evaluation;

import java.util.List;

import com.google.common.collect.Lists;

import context.ContextInterface;
import context.event.MoveInterface;

public final class NullEvaluation implements EvaluationInterface<MoveInterface> {

    @Override
    public int maximalOdinal() {
        return 0;
    }

    @Override
    public EvaluationInterface<MoveInterface> apply() {
        return this;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal, final List<MoveInterface> sortedOptions) {
        final List<List<MoveInterface>> evaluatedOptions = Lists.newArrayList();
        evaluatedOptions.add(sortedOptions);
        return evaluatedOptions;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal) {
        return this.process(context, this.maximalOdinal(), context.options());
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context) {
        return this.process(context, this.maximalOdinal());
    }

}