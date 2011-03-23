package fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;

public interface OpponentBuilderInterface {
	
	OpponentBuilderInterface player(PlayerInterface player);
	OpponentBuilderInterface evaluator(EvaluatorInterface evaluator);
	OpponentBuilderInterface selector(SelectorInterface selector);
	
	OpponentInterface build();
		
}