
package abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation.exploration;

import abstractions.immutable.context.ContextInterface;

public interface ExplorationInterface<T> {

    Double evaluate(final ContextInterface context, final T option, final int maximalDepth);

    int getMaximalDepth();

}
