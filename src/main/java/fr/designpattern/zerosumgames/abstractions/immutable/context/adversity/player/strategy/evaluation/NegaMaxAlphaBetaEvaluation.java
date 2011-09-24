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
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.NegaMaxAlphaBetaExploration;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public class NegaMaxAlphaBetaEvaluation implements EvaluationInterface<MoveTypeInterface> {

    final ExplorationInterface<MoveTypeInterface> explorationType;

    @Override
    public EvaluationInterface<MoveTypeInterface> apply() {
        return this;
    }

    public NegaMaxAlphaBetaEvaluation() {
        this.explorationType = new NegaMaxAlphaBetaExploration(10);
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context) {

        /*-------------------------------------8<-------------------------------------*/
        //final ActorRef actorRef = Actors.actorOf(myActor2.class);
        /*-------------------------------------8<-------------------------------------*/

        final TreeMap<Double, List<MoveTypeInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final MoveTypeInterface option : context.playableMoves()) {
            final Double score = this.explorationType.evaluate(context, option, 10);

            /*
            final mySignal2 signal = new mySignal2(context, this.explorationType, option, 11, -1.0, 1.0);
            final Future future = actorRef.sendRequestReplyFuture(signal);
            future.await();
            if (future.isCompleted()) {
                final Option resultOption = future.result();
                if (resultOption.isDefined()) {
                    final Object result = resultOption.get();
                }
            }
            */

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