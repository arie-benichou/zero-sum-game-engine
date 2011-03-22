package fr.designpattern.zerosumgames.framework.game.components.moves;

import fr.designpattern.zerosumgames.framework.game.GameInterface;

public interface IGameMoveEvaluator {

	double evaluate(final GameInterface context, final MoveInterface moveToEvaluate);
	double evaluate(final GameInterface context, final MoveInterface moveToEvaluate, final int maximalDepth);

}
