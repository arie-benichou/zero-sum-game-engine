package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.legalMoves.legalMove.LegalMoveInterface;

public interface EvaluatorInterface {
	
	void setContext(GameInterface context);
	
	GameInterface getContext();
	
	List<LegalMoveInterface> applyEvaluation(final List<LegalMoveInterface> legalMoves);
	
}