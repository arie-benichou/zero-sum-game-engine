package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.evaluators;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public class NullEvaluator implements EvaluatorInterface {

	public List<MoveInterface> applyEvaluation(List<MoveInterface> legalMoves) {
		return legalMoves;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}