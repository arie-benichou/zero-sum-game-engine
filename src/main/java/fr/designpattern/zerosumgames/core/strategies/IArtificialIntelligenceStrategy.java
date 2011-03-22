package fr.designpattern.zerosumgames.core.strategies;

import fr.designpattern.zerosumgames.core.ai.engine.standard.algorithms.IAlgorithm;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;

public interface IArtificialIntelligenceStrategy extends IGamePlayerStrategy {

	public IAlgorithm getEngine();

}
