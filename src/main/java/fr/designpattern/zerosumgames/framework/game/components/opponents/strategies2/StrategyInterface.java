package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.evaluators.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.selectors.SelectorInterface;

public interface StrategyInterface {
	
	EvaluatorInterface getEvaluator();
	
	SelectorInterface getSelector();
	
	List<MoveInterface> applyEvaluator(List<MoveInterface> legalMoves);
	
	MoveInterface applySelector(List<MoveInterface> legalMoves);
	
	MoveInterface getSelectedMoveFrom(List<MoveInterface> legalMoves);

}