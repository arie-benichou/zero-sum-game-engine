package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.selector.SelectorInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface StrategyInterface {
	
	EvaluatorInterface getEvaluator();
	
	SelectorInterface getSelector();
	
	List<LegalMoveInterface> applyEvaluator(List<LegalMoveInterface> legalMoves);
	
	LegalMoveInterface applySelector(List<LegalMoveInterface> legalMoves);
	
	LegalMoveInterface getSelectedMoveFrom(List<LegalMoveInterface> legalMoves);

}