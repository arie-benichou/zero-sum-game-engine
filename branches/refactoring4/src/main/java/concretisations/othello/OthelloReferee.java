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

package concretisations.othello;

import java.util.List;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.referee.RefereeInterface;
import abstractions.side.SideInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import concretisations.othello.mutations.NullMutation;

public class OthelloReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(final ContextInterface context) {
        //les deux joueurs ont succesivement passé leur tour ou les deux joueurs ne peuvent plus jouer
        return context.getLastMove(context.getCurrentSide()).isNull()
                && context.getLastMove(context.getCurrentSide().getNextSide()).isNull()
                ||
                this.getLegalMoves(context, context.getCurrentSide().getNextSide()).get(0).isNull()
                && this.getLegalMoves(context, context.getCurrentSide()).get(0).isNull();
    }

    @Override
    public List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side) {
        final List<MutationInterface> legalMoves = Lists.newArrayList(Iterables.concat(context.getCellManager().getPotentialMutations(side).values()));
        legalMoves.add(NullMutation.getInstance());
        return legalMoves;
    }

    @Override
    public final Double getHeuristicEvaluation(final ContextInterface context, final SideInterface sidePointOfView) {
        final OthelloContext othelloContext = (OthelloContext) context;
        return (othelloContext.getNumberOfPawns(sidePointOfView) - othelloContext.getNumberOfPawns(sidePointOfView.getNextSide()))
                / othelloContext.getNumberOfCells();
    }

    @Override
    public final Double getTerminalEvaluation(final ContextInterface context, final SideInterface sidePointOfView) {
        Double evaluation = this.getHeuristicEvaluation(context, sidePointOfView);
        if (evaluation == 0) {
            final OthelloContext othelloContext = (OthelloContext) context;
            //evaluation = 0 - othelloContext.getNumberOfEmptyCells() / othelloContext.getNumberOfCells() / 10;
            evaluation = othelloContext.getNumberOfEmptyCells() / (-10 * othelloContext.getNumberOfCells());
        }
        return evaluation;
    }
}