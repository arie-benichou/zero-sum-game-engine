package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.evaluator;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;

public interface EvaluatorInterface {
	
	void setContext(GameInterface context);
	
	GameInterface getContext();
	
	List<LegalMoveInterface> applyEvaluation(List<LegalMoveInterface> legalMoves);
	
}