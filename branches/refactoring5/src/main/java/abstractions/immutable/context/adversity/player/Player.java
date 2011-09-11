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

package abstractions.immutable.context.adversity.player;

import abstractions.immutable.context.adversity.player.strategy2.StrategyInterface;

public final class Player implements PlayerInterface {

    /*-------------------------------------8<-------------------------------------*/

    private final String name;

    @Override
    public final String name() {
        return this.name;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final StrategyInterface strategy;

    @Override
    public final StrategyInterface strategy() {
        return this.strategy;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PlayerInterface from(final String name, final StrategyInterface strategy) {
        return new Player(name, strategy);
    }

    private Player(final String name, final StrategyInterface strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PlayerInterface apply() {
        return this;
    }

    @Override
    public PlayerInterface apply(final String name) {
        return name == null || name.equals(this.name()) ? this.apply() : from(name, this.strategy());
    }

    @Override
    public PlayerInterface apply(final StrategyInterface strategy) { // TODO equals pour strategy
        return strategy == null || strategy.equals(this.strategy()) ? this.apply() : from(this.name(), strategy);
    }

    @Override
    public PlayerInterface apply(final String name, final StrategyInterface strategy) {
        return this.apply(name).apply(strategy);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "( " + this.name() + " , " + this.strategy() + " ) ";
    }

    /*-------------------------------------8<-------------------------------------*/

    /*
    @Override
    public final MutationInterface applyStrategy(final ContextInterface context, final List<MutationInterface> mutations) {
        return this.getStrategy().applyStrategy(context, mutations);
    }

    @Override
    public String toString() {
        return "Player(" + "\"" + this.name + "\"" + ", " + this.strategy + ")";
    }
    */
}
