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

package fr.designpattern.zerosumgames.framework.service.gameplay.opponents;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.GameInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.opponent.OpponentInterface;

/**
 * This is the interface for the opponents.
 */
public interface OpponentsInterface {

    /**
     * Set the game context for the opponents.
     * 
     * @param context
     *            the game context for the opponents
     */
    void injectContext(final GameInterface context);

    /**
     * Returns the game context for the opponents.
     * 
     * @return the game context for the opponents
     */
    GameInterface getContext();

    /**
     * Returns the opponent correspondig to the given order.
     * 
     * @param side
     *            the given side
     * 
     * @return the opponent correspondig to the given order
     */
    OpponentInterface getOpponentByOrder(
            final OpponentsEnumeration side);

}