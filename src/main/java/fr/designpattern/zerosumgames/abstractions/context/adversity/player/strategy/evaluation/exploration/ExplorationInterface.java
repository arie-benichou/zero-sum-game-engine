
package fr.designpattern.zerosumgames.abstractions.context.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;

public interface ExplorationInterface<OPTION> extends ImmutableInterface<ExplorationInterface<OPTION>> {

    /*-------------------------------------8<-------------------------------------*/

    ExplorationInterface<OPTION> apply(int maximalOrdinal);

    int maximalOrdinal();

    /*-------------------------------------8<-------------------------------------*/

    Double evaluate(final ContextInterface context, final OPTION option);

    Double evaluate(final ContextInterface context, final OPTION option, final int maximalOrdinal);

    /*-------------------------------------8<-------------------------------------*/

}