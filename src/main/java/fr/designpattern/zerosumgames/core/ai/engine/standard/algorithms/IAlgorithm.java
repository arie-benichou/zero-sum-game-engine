package fr.designpattern.zerosumgames.core.ai.engine.standard.algorithms;

import fr.designpattern.zerosumgames.core.interfaces.IGame;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;

public interface IAlgorithm {

	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToPlay);

	public double evaluateDeeply(final IGame context, final IGameBoardMove moveToPlay, int maximalDepth);

}