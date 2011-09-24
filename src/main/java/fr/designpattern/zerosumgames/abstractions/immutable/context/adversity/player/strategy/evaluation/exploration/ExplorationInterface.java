
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface ExplorationInterface<OPTION> extends ImmutableInterface<ExplorationInterface<OPTION>> {

    /*-------------------------------------8<-------------------------------------*/

    public ExplorationInterface<OPTION> apply(int maximalOrdinal);

    int maximalOrdinal();

    /*-------------------------------------8<-------------------------------------*/

    Double evaluate(final ContextInterface context, final OPTION option, final int maximalOrdinal);

    Double evaluate(ContextInterface context, MoveTypeInterface option, int maximalOrdinal, Double worstScore, Double bestScore);

    /*-------------------------------------8<-------------------------------------*/

}