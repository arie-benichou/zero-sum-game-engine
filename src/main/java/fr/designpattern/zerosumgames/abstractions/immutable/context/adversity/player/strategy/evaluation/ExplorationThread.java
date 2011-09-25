
package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation;

import java.util.concurrent.Callable;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.ExplorationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public class ExplorationThread implements Callable<Double> {

    /*-------------------------------------8<-------------------------------------*/

    private final ContextInterface context;
    private final ExplorationInterface<MoveTypeInterface> exploration;
    private final MoveTypeInterface option;
    private final int maximalOrdinal;
    private final Double worstScore;
    private final Double bestScore;

    /*-------------------------------------8<-------------------------------------*/

    public ExplorationThread(
            final ContextInterface context,
            final ExplorationInterface<MoveTypeInterface> exploration,
            final MoveTypeInterface option,
            final int maximalOrdinal,
            final Double worstScore,
            final Double bestScore) {
        this.context = context;
        this.exploration = exploration;
        this.option = option;
        this.maximalOrdinal = maximalOrdinal;
        this.worstScore = worstScore;
        this.bestScore = bestScore;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double call() throws Exception {
        System.out.println(this.option + " = ?");
        final Double result = this.exploration.evaluate(this.context, this.option, this.maximalOrdinal, this.worstScore, this.bestScore);
        System.out.println(this.option + " = " + result);
        return result;
    }

    /*-------------------------------------8<-------------------------------------*/

}