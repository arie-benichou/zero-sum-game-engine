
package context.entity.adversity.player.strategy.evaluation.exploration;

import context.ContextInterface;

public final class NullExploration<OPTION> implements ExplorationInterface<OPTION> {

    private final int maximalOrdinal = 0;

    @Override
    public ExplorationInterface<OPTION> apply() {
        return this;
    }

    @Override
    public ExplorationInterface<OPTION> apply(final int maximalOrdinal) {
        return this; // spécifique à l'exploration nulle
    }

    @Override
    public int maximalOrdinal() {
        return this.maximalOrdinal;
    }

    @Override
    public Double evaluate(final ContextInterface context, final OPTION option, final int maximalOrdinal) {
        return 0.0;
    }

    @Override
    public Double evaluate(final ContextInterface context, final OPTION option) {
        return 0.0;
    }

}