
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

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
    public Double evaluate(final ContextInterface context, final MoveTypeInterface option, final int maximalOrdinal, final Double worstScore,
            final Double bestScore) {
        return 0.0;
    }

}