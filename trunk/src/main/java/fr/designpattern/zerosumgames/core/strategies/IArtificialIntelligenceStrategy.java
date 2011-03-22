package fr.designpattern.zerosumgames.core.strategies;

import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.strategies.selectors.IAlgorithm;

public interface IArtificialIntelligenceStrategy extends IGamePlayerStrategy {

	public IAlgorithm getEngine();

}
