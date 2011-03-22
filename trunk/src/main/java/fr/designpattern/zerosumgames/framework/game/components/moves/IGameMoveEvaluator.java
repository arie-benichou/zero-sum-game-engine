package fr.designpattern.zerosumgames.framework.game.components.moves;

import fr.designpattern.zerosumgames.framework.game.GameInterface;

public interface IGameMoveEvaluator {

	double evaluate(final GameInterface context, final IGameMove moveToEvaluate);
	double evaluate(final GameInterface context, final IGameMove moveToEvaluate, final int maximalDepth);

}
