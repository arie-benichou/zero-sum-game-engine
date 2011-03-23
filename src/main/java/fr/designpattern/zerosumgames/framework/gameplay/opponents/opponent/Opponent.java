package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent;

import java.util.List;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;
import fr.designpattern.zerosumgames.framework.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.gameplay.legalMoves.legalMove.LegalMoveInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player.PlayerInterface;
import fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.strategy.StrategyInterface;

public class Opponent implements OpponentInterface {

	private final PlayerInterface player;
	private final StrategyInterface strategy;
	private transient GameInterface context;
	
	public Opponent(final PlayerInterface player, final StrategyInterface strategy) {
		this.player = player;
		this.strategy = strategy;
	}

	public PlayerInterface getPlayer() {
		return this.player;
	}

	public StrategyInterface getStrategy() {
		return this.strategy;
	}

	public MoveSelectorInterface selectMove(List<LegalMoveInterface> legalMoves) {
		return null;
	}

	public void setContext(GameInterface context) {
		this.context = context;
	}
	
	public GameInterface getContext() {
		return this.context;
	}
	
}