package fr.designpattern.zerosumgames.framework.service.gameplay.opponents;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.Opponent;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentBuilderInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.Strategy;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.evaluator.EvaluatorInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.strategy.selector.SelectorInterface;

public class OpponentBuilder implements OpponentBuilderInterface {

	private transient PlayerInterface player;
	private transient SelectorInterface selector;
	private transient EvaluatorInterface evaluator;
	
	public OpponentBuilderInterface player(PlayerInterface player) {
		this.player = player;
		return this;
	}

	public OpponentBuilderInterface evaluator(EvaluatorInterface evaluator) {
		this.evaluator = evaluator;
		return this;
	}

	public OpponentBuilderInterface selector(SelectorInterface selector) {
		this.selector = selector;
		return this;
	}

	public OpponentInterface build() {
		return new Opponent(this.player, new Strategy(this.evaluator, this.selector));		
	}

}