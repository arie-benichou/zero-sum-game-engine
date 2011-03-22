package fr.designpattern.zerosumgames.core.interfaces;

public interface IMoveEvaluator {

	double evaluate(final IGame context, final IGameBoardMove moveToEvaluate);
	double evaluate(final IGame context, final IGameBoardMove moveToEvaluate, final int maximalDepth);

}
