package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
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
	public Double evaluate(final ContextInterface context, final MoveTypeInterface option, final int maximalDepth) {
		return 0.0;
	}

}