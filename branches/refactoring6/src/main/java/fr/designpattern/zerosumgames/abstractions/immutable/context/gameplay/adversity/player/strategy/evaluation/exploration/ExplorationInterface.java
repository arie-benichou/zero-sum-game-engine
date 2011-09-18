
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public interface ExplorationInterface extends ImmutableInterface<ExplorationInterface> {

	public ExplorationInterface apply(int maximalDepth);
	int maximalDepth();

	Double evaluate(final GamePlayInterface context, final MoveTypeInterface option, final int maximalDepth);

}