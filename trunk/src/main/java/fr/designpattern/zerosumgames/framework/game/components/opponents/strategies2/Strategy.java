package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.evaluators.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.evaluators.NullEvaluator;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.selectors.NullSelector;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.selectors.SelectorInterface;

public class Strategy implements StrategyInterface {
	
	private EvaluatorInterface evaluator;
	private SelectorInterface selector; // TODO Selector peut être un composite

	public Strategy(EvaluatorInterface evaluator, SelectorInterface selector) {
		this.evaluator = evaluator;
		this.selector = selector;
	}

	public EvaluatorInterface getEvaluator() {
		return this.evaluator;
	}

	public SelectorInterface getSelector() {
		return this.selector;
	}

	public List<MoveInterface> applyEvaluator(List<MoveInterface> legalMoves) {
		return this.getEvaluator().applyEvaluation(legalMoves);
	}

	public MoveInterface applySelector(List<MoveInterface> legalMoves) {
		return this.getSelector().applySelection(legalMoves);
	}

	public MoveInterface getSelectedMoveFrom(List<MoveInterface> legalMoves) {
		return this.applySelector(this.applyEvaluator(legalMoves));
	}

	@Override
	public String toString() {
		return this.getEvaluator().toString() + " | " + getSelector().toString();
	}
	
	public static void main(String[] args) {
		
		//TODO implémenter la stratégie Humaine (InputStrategy) en utilisant le nullEvaluator et le selector dans human
		StrategyInterface s = new Strategy(new NullEvaluator(), new NullSelector());
		
		System.out.println(s);
		
	}

}
