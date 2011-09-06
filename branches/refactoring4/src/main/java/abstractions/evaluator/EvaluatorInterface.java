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

package abstractions.evaluator;

import java.util.List;
import java.util.TreeMap;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;

/**
 * This is the interface for an evaluator used by a player's strategy.
 * 
 * @TODO envisager de passer le contexte via la méthode applyEvaluation
 */
public interface EvaluatorInterface {

    final static Double WORST_EVALUATION = -1.0;
    final static Double BEST_EVALUATION = -WORST_EVALUATION;
    final static Double NULL_EVALUATION = WORST_EVALUATION + BEST_EVALUATION;

    TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations);

    TreeMap<Double, List<MutationInterface>> applyEvaluation(final ContextInterface context, final List<MutationInterface> mutations, final int maximalDepth);

    int getMaximalDepth();

}