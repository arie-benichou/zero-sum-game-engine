
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation;

import java.util.List;

import com.google.common.collect.Lists;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class NullEvaluation implements EvaluationInterface<MoveTypeInterface> {

    @Override
    public int maximalOdinal() {
        return 0;
    }

    @Override
    public EvaluationInterface<MoveTypeInterface> apply() {
        return this;
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context, final int maximalOdinal, final List<MoveTypeInterface> sortedOptions) {
        final List<List<MoveTypeInterface>> evaluatedOptions = Lists.newArrayList();
        evaluatedOptions.add(sortedOptions);
        return evaluatedOptions;
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context, final int maximalOdinal) {
        return this.process(context, this.maximalOdinal(), context.playableMoves());
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context) {
        return this.process(context, this.maximalOdinal());
    }

}