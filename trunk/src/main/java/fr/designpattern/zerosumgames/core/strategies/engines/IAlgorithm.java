package fr.designpattern.zerosumgames.core.strategies.engines;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public interface IAlgorithm {

	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToPlay);

	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToPlay, int maximalDepth);

}