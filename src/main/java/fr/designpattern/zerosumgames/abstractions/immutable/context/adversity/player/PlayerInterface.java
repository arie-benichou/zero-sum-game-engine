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

import fr.designpattern.zerosumgames.abstractions.immutable.ImmutableInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Application;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Computation;
import fr.designpattern.zerosumgames.abstractions.immutable.annotations.Value;
import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.adversity.player.strategy.StrategyInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.type.MoveTypeInterface;

/**
 * This is the interface for a player.
 */
public interface PlayerInterface<OPTION> extends ImmutableInterface<PlayerInterface<OPTION>> {

    /*-------------------------------------8<-------------------------------------*/

    @Value
    String name();

    @Value
    StrategyInterface<MoveTypeInterface> strategy();

    /*-------------------------------------8<-------------------------------------*/

    @Application
    PlayerInterface<OPTION> apply(String name);

    @Application
    PlayerInterface<OPTION> apply(StrategyInterface<MoveTypeInterface> strategy);

    @Application
    PlayerInterface<OPTION> apply(String name, StrategyInterface<MoveTypeInterface> strategy);

    /*-------------------------------------8<-------------------------------------*/

    @Computation
    List<MoveTypeInterface> playFrom(final ContextInterface context);

    /*-------------------------------------8<-------------------------------------*/

}