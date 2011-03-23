package fr.designpattern.zerosumgames.framework.opponents.strategies.evaluators;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.moves.MoveInterface;

public interface EvaluatorInterface {
	
	void setContext(GameInterface context);
	
	GameInterface getContext();
	
	List<MoveInterface> applyEvaluation(List<MoveInterface> legalMoves);
	
}