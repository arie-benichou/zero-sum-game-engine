package fr.designpattern.zerosumgames.framework.gameplay.opponents.strategies;

import java.util.List;

import fr.designpattern.zerosumgames.framework.gameplay.opponents.strategies.evaluators.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.strategies.selectors.SelectorInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface StrategyInterface {
	
	EvaluatorInterface getEvaluator();
	
	SelectorInterface getSelector();
	
	List<MoveInterface> applyEvaluator(List<MoveInterface> legalMoves);
	
	MoveInterface applySelector(List<MoveInterface> legalMoves);
	
	MoveInterface getSelectedMoveFrom(List<MoveInterface> legalMoves);

}