package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;

public class NullEvaluator implements EvaluatorInterface {
	// ------------------------------------------------------------
	private GameInterface context;
	public final void setContext(GameInterface context) {
		this.context = context;
	}
	public final GameInterface getContext() {
		return this.context;
	}
	// ------------------------------------------------------------
	public List<LegalMoveInterface> applyEvaluation(final List<LegalMoveInterface> legalMoves) {
		return legalMoves;
	}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	// ------------------------------------------------------------	
}