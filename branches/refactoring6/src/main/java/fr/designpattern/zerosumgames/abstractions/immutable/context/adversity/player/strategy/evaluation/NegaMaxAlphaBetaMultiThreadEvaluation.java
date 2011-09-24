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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.ExplorationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.NegaMaxAlphaBetaExploration;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public class NegaMaxAlphaBetaMultiThreadEvaluation implements EvaluationInterface<MoveTypeInterface> {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static final int MAX = 7;

    final ExplorationInterface<MoveTypeInterface> explorationType;

    @Override
    public EvaluationInterface<MoveTypeInterface> apply() {
        return this;
    }

    public NegaMaxAlphaBetaMultiThreadEvaluation() {
        this.explorationType = new NegaMaxAlphaBetaExploration(MAX);
    }

    @Override
    public List<List<MoveTypeInterface>> process(final ContextInterface context) {
        /*-------------------------------------8<-------------------------------------*/
        final List<MoveTypeInterface> playableMoves = context.playableMoves();
        /*-------------------------------------8<-------------------------------------*/
        //final ExecutorService executor = Executors.newFixedThreadPool((int) (2.0 * NUMBER_OF_CORES));
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final List<Future<Double>> list = Lists.newArrayList();
        for (final MoveTypeInterface option : playableMoves)
            list.add(executor.submit(new ExplorationThread(context, this.explorationType, option, MAX, -1.0, 1.0)));
        /*-------------------------------------8<-------------------------------------*/
        executor.shutdown();
        while (!executor.isTerminated()) {}
        /*-------------------------------------8<-------------------------------------*/
        int index = 0;
        final TreeMap<Double, List<MoveTypeInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final Future<Double> future : list) {
            try {
                final Double score = future.get();
                final List<MoveTypeInterface> value = map.get(score);
                if (value == null)
                    map.put(score, Lists.newArrayList(playableMoves.get(index)));
                else
                    value.add(playableMoves.get(index));
                ++index;
            }
            catch (final InterruptedException e) {
                e.printStackTrace();
            }
            catch (final ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*-------------------------------------8<-------------------------------------*/
        if (list.size() != index) {
            throw new RuntimeException("Double-entries!!!");
        }
        /*-------------------------------------8<-------------------------------------*/
        final List<List<MoveTypeInterface>> evaluation = Lists.newArrayList();
        for (final List<MoveTypeInterface> items : map.values())
            evaluation.add(items);
        /*-------------------------------------8<-------------------------------------*/
        return evaluation;
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}
