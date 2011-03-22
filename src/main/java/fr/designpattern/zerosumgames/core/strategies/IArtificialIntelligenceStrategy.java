package fr.designpattern.zerosumgames.core.strategies;

import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.strategies.moveSelectors.IAlgorithm;

public interface IArtificialIntelligenceStrategy extends IGamePlayerStrategy {

	public IAlgorithm getEngine();

}
