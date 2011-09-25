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

package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.Move;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class NegaMaxExploration implements ExplorationInterface<MoveTypeInterface> {

    /*-------------------------------------8<-------------------------------------*/

    private final int maximalOrdinal;

    @Override
    public int maximalOrdinal() {
        return this.maximalOrdinal;
    }

    /*-------------------------------------8<-------------------------------------*/

    public NegaMaxExploration(final int maximalOrdinal) {
        this.maximalOrdinal = maximalOrdinal;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ExplorationInterface<MoveTypeInterface> apply() {
        return this;
    }

    @Override
    public ExplorationInterface<MoveTypeInterface> apply(final int maximalOrdinal) {
        return new NegaMaxExploration(maximalOrdinal);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double evaluate(final ContextInterface context, final MoveTypeInterface option, final int maximalOrdinal) {

        final MoveInterface move = Move.from(option, option.value().boardMutation()); // TODO ?? MoveType -> Move
        final ContextInterface newContext = context.play(move);

        if (newContext.isOver()) return newContext.getTerminalEvaluation();
        if (maximalOrdinal == 1) return newContext.getHeuristicEvaluation();

        // TODO playableMoves(SideInterface side) et passer side en argument à evaluate(...)    
        final ContextInterface newContextForOppositeSide = newContext.apply(context.side().opposite());

        Double bestEvaluation = 1.0; // TODO tenter une version immutable        
        for (final MoveTypeInterface oppositeSideOption : newContextForOppositeSide.playableMoves())
            bestEvaluation = Math.min(bestEvaluation, -this.evaluate(newContextForOppositeSide, oppositeSideOption, maximalOrdinal - 1));
        return bestEvaluation;

    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Double evaluate(final ContextInterface context, final MoveTypeInterface option) {
        return this.evaluate(context, option, this.maximalOrdinal());
    }

    /*-------------------------------------8<-------------------------------------*/

}