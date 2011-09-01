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

import abstractions.mutation.MutationInterface;
import abstractions.strategy.StrategyInterface;

/**
 * This is the interface for a player.
 */
public interface PlayerInterface {

    /**
     * Returns the player's name.
     * 
     * @return the player's name
     */
    String getName();

    /**
     * Returns the player's strategy.
     * 
     * @return the player's strategy
     */
    StrategyInterface getStrategy();

    /**
     * Returns the application of the player's strategy.
     * 
     * @param mutations
     *            a list of legal moves for this player
     * 
     * @return the application of the player's strategy
     * 
     * @todo passer également le contexte
     */
    MutationInterface applyStrategy(List<MutationInterface> mutations);

}
