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

package fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player;

import java.util.List;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.StrategyInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

public final class Player implements PlayerInterface<MoveTypeInterface> {

    /*-------------------------------------8<-------------------------------------*/

    private final String name;

    @Override
    public final String name() {
        return this.name;
    }

    /*-------------------------------------8<-------------------------------------*/

    private final StrategyInterface<MoveTypeInterface> strategy;

    @Override
    public final StrategyInterface<MoveTypeInterface> strategy() {
        return this.strategy;
    }

    /*-------------------------------------8<-------------------------------------*/

    public static PlayerInterface<MoveTypeInterface> from(final String name, final StrategyInterface<MoveTypeInterface> strategy) {
        return new Player(name, strategy);
    }

    private Player(final String name, final StrategyInterface<MoveTypeInterface> strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public PlayerInterface<MoveTypeInterface> apply() {
        return this;
    }

    @Override
    public PlayerInterface<MoveTypeInterface> apply(final String name) {
        return name == null || name.equals(this.name()) ? this.apply() : from(name, this.strategy());
    }

    @Override
    public PlayerInterface<MoveTypeInterface> apply(final StrategyInterface<MoveTypeInterface> strategy) {
        return strategy == null || strategy.equals(this.strategy()) ? this.apply() : from(this.name(), strategy);
    }

    @Override
    public PlayerInterface<MoveTypeInterface> apply(final String name, final StrategyInterface<MoveTypeInterface> strategy) {
        return this.apply(name).apply(strategy); // TODO optimisable
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public List<MoveTypeInterface> playFrom(final ContextInterface context) {
        return this.strategy().process(context);
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.name() + ", " + this.strategy() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

}