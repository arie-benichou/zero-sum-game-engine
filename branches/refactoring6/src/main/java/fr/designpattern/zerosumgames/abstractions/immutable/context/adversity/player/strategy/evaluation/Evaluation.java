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

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.ExplorationInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.evaluation.exploration.ExplorationThread;
import fr.designpattern.zerosumgames.abstractions.immutable.move.MoveInterface;

public final class Evaluation implements EvaluationInterface<MoveInterface> {

    private final ExplorationInterface<MoveInterface> explorationType;

    @Override
    public int maximalOdinal() {
        return this.explorationType.maximalOrdinal();
    }

    @Override
    public EvaluationInterface<MoveInterface> apply() {
        return this;
    }

    public Evaluation(final ExplorationInterface<MoveInterface> explorationType) {
        this.explorationType = explorationType;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal, final List<MoveInterface> givenOptions) {

        /*-------------------------------------8<-------------------------------------*/
        //final ExecutorService executor = Executors.newSingleThreadExecutor();
        //final ExecutorService executor = Executors.newFixedThreadPool(givenOptions.size());
        final ExecutorService executor = Executors.newCachedThreadPool();
        /*-------------------------------------8<-------------------------------------*/
        final Builder<Future<Entry<MoveInterface, Double>>> listBuilder = new ImmutableList.Builder<Future<Entry<MoveInterface, Double>>>();
        for (final MoveInterface option : givenOptions) {
            //listBuilder.add(executor.submit(new ExplorationThread(context, exploration, option, maximalOrdinal)));
            listBuilder.add(executor.submit(new ExplorationThread(context, this.explorationType, option, maximalOdinal)));
        }
        /*-------------------------------------8<-------------------------------------*/
        executor.shutdown();
        final ImmutableList<Future<Entry<MoveInterface, Double>>> list = listBuilder.build();
        /*-------------------------------------8<-------------------------------------*/
        while (!executor.isTerminated()) {}
        /*-------------------------------------8<-------------------------------------*/
        final TreeMap<Double, List<MoveInterface>> map = Maps.newTreeMap(java.util.Collections.reverseOrder());
        for (final Future<Map.Entry<MoveInterface, Double>> future : list) {
            try {
                final Map.Entry<MoveInterface, Double> evaluatedOption = future.get();
                final List<MoveInterface> value = map.get(evaluatedOption.getValue());
                final List<MoveInterface> newValue = value == null ? ImmutableList.of(evaluatedOption.getKey()) :
                        new ImmutableList.Builder<MoveInterface>().addAll(value).add(evaluatedOption.getKey()).build();
                map.put(evaluatedOption.getValue(), newValue);
            }
            catch (final InterruptedException e) {
                e.printStackTrace();
            }
            catch (final ExecutionException e) {
                e.printStackTrace();
            }
        }
        /*-------------------------------------8<-------------------------------------*/
        if (list.size() != givenOptions.size()) { throw new RuntimeException("Double-entries !!!"); }
        /*-------------------------------------8<-------------------------------------*/

        /*-------------------------------------8<-------------------------------------*/
        final List<List<MoveInterface>> evaluation = Lists.newArrayList();
        for (final List<MoveInterface> items : map.values())
            evaluation.add(items);
        /*-------------------------------------8<-------------------------------------*/
        return evaluation;
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context, final int maximalOdinal) {

        final List<MoveInterface> givenOptions = context.options();

        System.out.println();
        System.out.println(givenOptions);

        Collections.sort(givenOptions);

        System.out.println(givenOptions);
        System.out.println();

        return this.process(context, maximalOdinal, givenOptions);
    }

    @Override
    public List<List<MoveInterface>> process(final ContextInterface context) {
        return this.process(context, this.maximalOdinal());
    }

    @Override
    public final String toString() {
        return this.getClass().getSimpleName();
    }

}