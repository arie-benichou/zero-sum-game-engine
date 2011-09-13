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

package abstractions.referee;

import java.util.List;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.side.SideInterface;

/**
 * This is the interface for a game referee.
 * 
 * @todo commentaires
 */
public interface RefereeInterface {

    boolean isGameOver(final ContextInterface context);

    Double getHeuristicEvaluation(final ContextInterface context, final SideInterface side);

    Double getTerminalEvaluation(final ContextInterface context, final SideInterface side);

    List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side);

    List<MutationInterface> getSortedLegalMoves(ContextInterface context, SideInterface side);

}