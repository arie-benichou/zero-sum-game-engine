
package context.entity.adversity.player.strategy.evaluation.exploration;

import util.interfaces.ImmutableInterface;
import context.ContextInterface;

public interface ExplorationInterface<OPTION> extends ImmutableInterface<ExplorationInterface<OPTION>> {

    /*-------------------------------------8<-------------------------------------*/

    ExplorationInterface<OPTION> apply(int maximalOrdinal);

    int maximalOrdinal();

    /*-------------------------------------8<-------------------------------------*/

    Double evaluate(final ContextInterface context, final OPTION option);

    Double evaluate(final ContextInterface context, final OPTION option, final int maximalOrdinal);

    /*-------------------------------------8<-------------------------------------*/

}