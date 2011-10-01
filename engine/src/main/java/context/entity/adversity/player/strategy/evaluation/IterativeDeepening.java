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

package context.entity.adversity.player.strategy.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import context.ContextInterface;
import context.event.MoveInterface;

public class IterativeDeepening implements EvaluationInterface<MoveInterface> {

    private final EvaluationInterface<MoveInterface> evaluation;

    public IterativeDeepening(final EvaluationInterface<MoveInterface> evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public EvaluationInterface<MoveInterface> apply() {
        return this;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal, final List<MoveInterface> givenOptions) {
        int localMaximalOrdinal = 0;
        List<List<MoveInterface>> evaluation = new ArrayList<List<MoveInterface>>();
        evaluation.add(givenOptions);
        while (localMaximalOrdinal < maximalOdinal) {
            System.out.println("Iteration " + (localMaximalOrdinal + 1) + " / " + this.maximalOdinal());
            evaluation = this.evaluation.process(context, ++localMaximalOrdinal, Lists.newArrayList(Iterables.concat(evaluation)));
        }
        return evaluation;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal) {
        final List<MoveInterface> givenOptions = context.options();
        return this.process(context, this.maximalOdinal(), givenOptions);
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context) {
        return this.process(context, this.maximalOdinal());
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int maximalOdinal() {
        return this.evaluation.maximalOdinal();
    }

}