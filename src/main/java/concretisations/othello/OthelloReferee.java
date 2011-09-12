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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import abstractions.immutable.context.ContextInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.referee.RefereeInterface;
import abstractions.old.evaluator.EvaluatorInterface;
import abstractions.old.mutation.MutationInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import concretisations.othello.mutations.NullMutation;

class OthelloReferee implements RefereeInterface {

    @Override
    public boolean isGameOver(final ContextInterface context) {
        //les deux joueurs ont succesivement passé leur tour ou les deux joueurs ne peuvent plus jouer
        return context.getLastMove(context.side()).isNull()
                && context.getLastMove(context.side().getNextSide()).isNull()
                ||
                this.getLegalMoves(context, context.side().getNextSide()).get(0).isNull()
                && this.getLegalMoves(context, context.side()).get(0).isNull();
    }

    @Override
    public List<MutationInterface> getLegalMoves(final ContextInterface context, final SideInterface side) {
        final List<MutationInterface> legalMoves = Lists.newArrayList(Iterables.concat(context.getCellManager().getPotentialMutations(side).values()));
        legalMoves.add(NullMutation.getInstance());
        return legalMoves;
    }

    @Override
    public List<MutationInterface> getSortedLegalMoves(final ContextInterface context, final SideInterface side) {
        final List<MutationInterface> legalMoves = Lists.newArrayList();
        for (final Set<MutationInterface> collection : context.getCellManager().getPotentialMutations(side).values()) {
            for (final MutationInterface mutation : collection) {
                mutation.computeSequence(context);
                legalMoves.add(mutation);
            }
        }
        legalMoves.add(NullMutation.getInstance());
        Collections.sort(legalMoves);
        return legalMoves;
    }

    @Override
    public final Double getHeuristicEvaluation(final ContextInterface context, final SideInterface sidePointOfView) {
        final OthelloContext othelloContext = (OthelloContext) context;
        return (0.0 + othelloContext.getNumberOfPawns(sidePointOfView) - othelloContext.getNumberOfPawns(sidePointOfView.getNextSide()))
                / othelloContext.getNumberOfCells();
    }

    @Override
    public final Double getTerminalEvaluation(final ContextInterface context, final SideInterface sidePointOfView) {
        Double evaluation = this.getHeuristicEvaluation(context, sidePointOfView);
        if (evaluation == EvaluatorInterface.NULL_EVALUATION) {
            final OthelloContext othelloContext = (OthelloContext) context;
            //evaluation = 0 - othelloContext.getNumberOfEmptyCells() / othelloContext.getNumberOfCells() / 10;
            evaluation = othelloContext.getNumberOfEmptyCells() / (-10.0 * othelloContext.getNumberOfCells());
        }
        return evaluation;
    }
}