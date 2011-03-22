package fr.designpattern.zerosumgames.framework.build.game.components.move;

import fr.designpattern.zerosumgames.framework.build.game.IGame;

public interface IGameMoveEvaluator {

	double evaluate(final IGame context, final IGameMove moveToEvaluate);
	double evaluate(final IGame context, final IGameMove moveToEvaluate, final int maximalDepth);

}
