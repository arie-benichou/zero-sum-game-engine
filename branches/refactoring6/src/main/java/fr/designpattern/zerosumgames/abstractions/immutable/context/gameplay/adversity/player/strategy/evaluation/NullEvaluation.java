
package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation;

import java.util.List;

import com.google.common.collect.Lists;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;


public final class NullEvaluation implements EvaluationInterface {

	@Override
	public List<List<MoveTypeInterface>> process(final GamePlayInterface context, final List<MoveTypeInterface> options) {
		final List<List<MoveTypeInterface>> evaluation = Lists.newArrayList();
		evaluation.add(options);
		return evaluation;
	}

}