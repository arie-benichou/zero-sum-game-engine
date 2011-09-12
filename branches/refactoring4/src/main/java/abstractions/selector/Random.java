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

package abstractions.selector;

import java.util.List;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;
import annotations.Immutable;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Immutable
public class Random extends AbstractSelector {

    public Random() {
        super(RANDOM_ON_SAME_EVALUATION, !AVOID_NULL_MOVE);
    }

    public Random(final boolean avoidNullMove) {
        super(RANDOM_ON_SAME_EVALUATION, avoidNullMove);
    }

    @Override
    public List<MutationInterface> select(final TreeMap<Double, List<MutationInterface>> evaluatedMutations) {
        return Lists.newArrayList(Iterables.concat(evaluatedMutations.values()));
    }

}