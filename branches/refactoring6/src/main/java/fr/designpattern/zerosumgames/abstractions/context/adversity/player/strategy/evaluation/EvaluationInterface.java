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

package fr.designpattern.zerosumgames.abstractions.context.adversity.player.strategy.evaluation;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;

/**
 * This is the interface for evaluation.
 * 
 * @param <OPTION>
 */
public interface EvaluationInterface<OPTION> extends ImmutableInterface<EvaluationInterface<OPTION>> {

    int maximalOdinal();

    List<List<OPTION>> process(final ContextInterface context);

    List<List<OPTION>> process(final ContextInterface context, int maximalOdinal);

    //List<List<OPTION>> process(final ContextInterface context, final int maximalOdinal, List<List<OPTION>> sortedOptions);

    List<List<OPTION>> process(final ContextInterface context, final int maximalOdinal, List<OPTION> sortedOptions);

}