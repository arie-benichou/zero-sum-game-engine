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

package fr.designpattern.zerosumgames.abstractions.context.adversity.player.strategy;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.context.adversity.player.strategy.evaluation.EvaluationInterface;
import fr.designpattern.zerosumgames.abstractions.context.adversity.player.strategy.selection.SelectionInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;

public final class Strategy implements StrategyInterface<MoveInterface> {

    /*-------------------------------------8<-------------------------------------*/

    private final EvaluationInterface<MoveInterface> evaluation;

    @Override
    public EvaluationInterface<MoveInterface> evaluation() {
        return this.evaluation;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final SelectionInterface<MoveInterface> selection;

    @Override
    public SelectionInterface<MoveInterface> selection() {
        return this.selection;
    }

    /*-------------------------------------8<-------------------------------------*/

    public Strategy(final EvaluationInterface<MoveInterface> evaluation, final SelectionInterface<MoveInterface> selection) {
        this.evaluation = evaluation;
        this.selection = selection;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Strategy apply(final EvaluationInterface<MoveInterface> evaluation) {
        return new Strategy(evaluation, this.selection());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Strategy apply(final SelectionInterface<MoveInterface> selection) {
        return new Strategy(this.evaluation(), selection);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Strategy apply() {
        return this;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return "Strategy(" + this.evaluation.toString() + ", " + this.selection.toString() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public List<MoveInterface> process(final ContextInterface context) {
        final List<MoveInterface> options = context.options();
        //return options.size() == 1 ? options : this.selection().process(this.evaluation().process(context));
        if (options.size() == 1)
            return options;
        final List<List<MoveInterface>> _evaluation = this.evaluation().process(context);
        final List<MoveInterface> _selection = this.selection().process(_evaluation);
        return _selection;
    }
    /*-------------------------------------8<-------------------------------------*/

}