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

package abstractions.immutable.context.gameplay.adversity.player.strategy.evaluation;

import java.util.List;

import abstractions.immutable.context.ContextInterface;

/**
 * This is the interface for an evaluator used by a player's strategy.
 * 
 * @TODO envisager de passer le contexte via la méthode applyEvaluation
 */
public interface EvaluationInterface<T> {

    List<List<T>> evaluate(final ContextInterface context, final List<T> options);

    //List<List<T>> evaluate(final ContextInterface context, final List<T> options, final int maximalDepth);

    //int getMaximalDepth();

}