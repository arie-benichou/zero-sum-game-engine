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

package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.EvaluationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.selection.SelectionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

/**
 * This is the interface for a player's strategy.
 */
public interface StrategyInterface extends ImmutableInterface<StrategyInterface>{

	StrategyInterface apply(EvaluationInterface evaluationType);
	EvaluationInterface evaluation();

	StrategyInterface apply(SelectionInterface selectionType);
	SelectionInterface selection();

	List<MoveTypeInterface> process(final ContextInterface gamePlay);

}