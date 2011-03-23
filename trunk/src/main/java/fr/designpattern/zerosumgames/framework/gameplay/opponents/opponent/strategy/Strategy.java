package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector.SelectorInterface;

public class Strategy implements StrategyInterface {
	
	private EvaluatorInterface evaluator;
	private SelectorInterface selector; // TODO Selector peut être un composite

	public Strategy(EvaluatorInterface evaluator, SelectorInterface selector) {
		this.evaluator = evaluator;
		this.selector = selector;
	}
	
	public void injectContext(GameInterface context) {
		this.getEvaluator().setContext(context);
	}

	public EvaluatorInterface getEvaluator() {
		return this.evaluator;
	}

	public SelectorInterface getSelector() {
		return this.selector;
	}

	public List<LegalMoveInterface> applyEvaluator(List<LegalMoveInterface> legalMoves) {
		return this.getEvaluator().applyEvaluation(legalMoves);
	}

	public LegalMoveInterface applySelector(List<LegalMoveInterface> legalMoves) {
		return this.getSelector().applySelection(legalMoves);
	}

	public LegalMoveInterface computeStrategicMoveFrom(List<LegalMoveInterface> legalMoves) {
		return this.applySelector(this.applyEvaluator(legalMoves));
	}

	@Override
	public String toString() {
		return this.getEvaluator().toString() + " | " + getSelector().toString();
	}

}
