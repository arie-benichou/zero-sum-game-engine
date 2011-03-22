package fr.designpattern.zerosumgames.core.strategies;

import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.strategies.engines.IAlgorithm;

public interface IArtificialIntelligenceStrategy extends IGamePlayerStrategy {

	public IAlgorithm getEngine();

}
