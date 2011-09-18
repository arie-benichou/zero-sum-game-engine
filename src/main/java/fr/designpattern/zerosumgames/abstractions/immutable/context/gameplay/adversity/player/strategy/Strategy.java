/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.GamePlayInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation.EvaluationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.adversity.player.strategy.selection.SelectionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class Strategy implements StrategyInterface {

	/*-------------------------------------8<-------------------------------------*/

	private final EvaluationInterface evaluation;

	@Override
	public EvaluationInterface evaluation() {
		return this.evaluation;
	}

	/*-------------------------------------8<-------------------------------------*/

	private final SelectionInterface selection;

	@Override
	public SelectionInterface selection() {
		return this.selection;
	}

	/*-------------------------------------8<-------------------------------------*/

	public Strategy(final EvaluationInterface evaluation, final SelectionInterface selection) {
		this.evaluation = evaluation;
		this.selection = selection;
	}


	/*-------------------------------------8<-------------------------------------*/

	@Override
	public StrategyInterface apply(final EvaluationInterface evaluation) {
		return new Strategy(evaluation, this.selection());
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public StrategyInterface apply(final SelectionInterface selection) {
		return new Strategy(this.evaluation(), selection);
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public StrategyInterface apply() {
		return this;
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public String toString() {
		return "Strategy(" + this.evaluation.toString() + ", " + this.selection.toString() + ")";
	}

	/*-------------------------------------8<-------------------------------------*/

	@Override
	public List<MoveTypeInterface> process(final GamePlayInterface gamePlay) {
		final List<MoveTypeInterface> options = gamePlay.computePlayableMoves();
		//return options.size() == 1 ? options : this.selection().process(this.evaluation().applyEvaluation(gamePlay, options));
		return null;
	}

	/*-------------------------------------8<-------------------------------------*/

}