package fr.designpattern.zerosumgames.game.components.move;

import fr.designpattern.zerosumgames.game.IGame;

public interface IGameMoveEvaluator {

	double evaluate(final IGame context, final IGameMove moveToEvaluate);
	double evaluate(final IGame context, final IGameMove moveToEvaluate, final int maximalDepth);

}
