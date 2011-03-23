package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies2.evaluators;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public interface EvaluatorInterface {
	
	List<MoveInterface> applyEvaluation(List<MoveInterface> legalMoves);

}