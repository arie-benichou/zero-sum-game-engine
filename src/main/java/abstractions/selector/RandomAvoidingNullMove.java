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
import java.util.Random;
import java.util.TreeMap;

import abstractions.mutation.MutationInterface;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class RandomAvoidingNullMove implements SelectorInterface {

    private final Random random = new Random();

    @Override
    public MutationInterface applySelection(final TreeMap<Integer, List<MutationInterface>> evaluatedMutations) {

        final List<MutationInterface> mutations = Lists.newArrayList(Iterables.concat(evaluatedMutations.values()));
        final int size = mutations.size();

        if (size == 1)
            return mutations.get(0);

        MutationInterface mutation;
        do
            mutation = mutations.get(this.random.nextInt(size));
        while (mutation.isNull());

        return mutation;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}