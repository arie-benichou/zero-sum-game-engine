package fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.evaluators;

import fr.designpattern.zerosumgames.framework.game.GameInterface;
import fr.designpattern.zerosumgames.framework.game.components.moves.MoveInterface;

public interface EvaluatorInterface {

	double evaluate(final GameInterface context, final MoveInterface moveToEvaluate);
	//double evaluate(final GameInterface context, final MoveInterface moveToEvaluate, final int maximalDepth);

}
