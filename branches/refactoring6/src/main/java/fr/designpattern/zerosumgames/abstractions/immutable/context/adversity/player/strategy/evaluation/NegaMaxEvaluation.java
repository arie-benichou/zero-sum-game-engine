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

package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation;

import java.util.List;
import java.util.TreeMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.ExplorationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.NegaMaxExploration;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public class NegaMaxEvaluation implements EvaluationInterface<MoveTypeInterface> {

    final ExplorationInterface<MoveTypeInterface> explorationType;

    @Override
    public EvaluationInterface<MoveTypeInterface> apply() {
        return this;
    }

    public NegaMaxEvaluation() {
        this.explorationType = new NegaMaxExploration(1);
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context) {
        final TreeMap<Double, List<MoveTypeInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MoveTypeInterface option : context.playableMoves()) {
            final Double score = this.explorationType.evaluate(context, option, 1);
            final List<MoveTypeInterface> value = map.get(score);
            if (value == null)
                map.put(score, Lists.newArrayList(option));
            else
                value.add(option);
        }
        final List<List<MoveTypeInterface>> evaluation = Lists.newArrayList();
        for (final List<MoveTypeInterface> items : map.values())
            evaluation.add(items);
        return evaluation;
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}