
package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;

public class Strategy implements StrategyInterface {

    private final transient EvaluatorInterface evaluator;
    private final transient SelectorInterface selector; // TODO Selector peut être un composite

    public Strategy(final EvaluatorInterface evaluator,
            final SelectorInterface selector) {
        this.evaluator = evaluator;
        this.selector = selector;
    }

    public void injectContext(final GameInterface context) {
        this.getEvaluator().setContext(context);
    }

    public EvaluatorInterface getEvaluator() {
        return this.evaluator;
    }

    public SelectorInterface getSelector() {
        return this.selector;
    }

    public List<LegalMoveInterface> applyEvaluator(
            final List<LegalMoveInterface> legalMoves) {
        return this.getEvaluator().applyEvaluation(legalMoves);
    }

    public LegalMoveInterface applySelector(
            final List<LegalMoveInterface> legalMoves) {
        return this.getSelector().applySelection(legalMoves);
    }

    public LegalMoveInterface computeStrategicMoveFrom(
            final List<LegalMoveInterface> legalMoves) {
        return this.applySelector(this.applyEvaluator(legalMoves));
    }

    @Override
    public String toString() {
        return this.getEvaluator().toString() + " | "
                + this.getSelector().toString();
    }

}
