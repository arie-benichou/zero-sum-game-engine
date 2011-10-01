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

package context.entity.adversity.player;

import java.util.List;

import util.annotations.Application;
import util.annotations.Computation;
import util.annotations.Value;
import util.interfaces.ImmutableInterface;
import context.ContextInterface;
import context.entity.adversity.player.strategy.StrategyInterface;
import context.event.MoveInterface;

/**
 * This is the interface for a player.
 */
public interface PlayerInterface<OPTION> extends ImmutableInterface<PlayerInterface<OPTION>> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    String name();

    @Value
    StrategyInterface<MoveInterface> strategy();

    /*-------------------------------------8<-------------------------------------*/

    @Application
    PlayerInterface<OPTION> apply(String name);

    @Application
    PlayerInterface<OPTION> apply(StrategyInterface<MoveInterface> strategy);

    @Application
    PlayerInterface<OPTION> apply(String name, StrategyInterface<MoveInterface> strategy);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    List<MoveInterface> playFrom(final ContextInterface context);

    /*-------------------------------------8<-------------------------------------*/

}