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

package abstractions.player;

import java.util.List;

import abstractions.context.ContextInterface;
import abstractions.mutation.MutationInterface;
import abstractions.strategy.StrategyInterface;

public class Player implements PlayerInterface {

    private final String name;
    private final StrategyInterface strategy;

    public Player(final String name, final StrategyInterface strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final StrategyInterface getStrategy() {
        return this.strategy;
    }

    @Override
    public final MutationInterface applyStrategy(final ContextInterface context, final List<MutationInterface> mutations) {
        return this.getStrategy().applyStrategy(context, mutations);
    }

    @Override
    public String toString() {
        return "Player(" + "\"" + this.name + "\"" + ", " + this.strategy + ")";
    }
}
