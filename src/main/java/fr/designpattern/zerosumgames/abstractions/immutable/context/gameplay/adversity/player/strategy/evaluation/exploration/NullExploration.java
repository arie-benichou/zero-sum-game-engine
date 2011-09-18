package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class NullExploration implements ExplorationInterface{

	private final int maximalDepth = 0;

	@Override
	public ExplorationInterface apply() {
		return this;
	}

	@Override
	public ExplorationInterface apply(final int maximalDepth) {
		return this;
	}

	@Override
	public int maximalDepth() {
		return this.maximalDepth;
	}

	@Override
	public Double evaluate(final GamePlayInterface context, final MoveTypeInterface option, final int maximalDepth) {
		return 0.0;
	}

}