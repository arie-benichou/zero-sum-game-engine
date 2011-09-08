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

package abstractions.adversity;

import java.util.Map;

import abstractions.player.PlayerInterface;
import abstractions.side.SideInterface;
import abstractions.side.Sides;
import annotations.Immutable;

import com.google.common.collect.Maps;

@Immutable
public class Adversity implements AdversityInterface {

    private final Map<SideInterface, PlayerInterface> map = Maps.newHashMapWithExpectedSize(2);

    public Adversity(final PlayerInterface firstSidePlayer, final PlayerInterface secondSidePlayer) {
        this.map.put(Sides.FIRST, firstSidePlayer);
        this.map.put(Sides.SECOND, secondSidePlayer);
    }

    @Override
    public PlayerInterface getPlayer(final SideInterface side) {
        return this.map.get(side); // TODO ? gérer le null opponent ici
    }

    @Override
    public String toString() {
        return this.map.get(Sides.FIRST) + " Vs " + this.map.get(Sides.SECOND);
    }

}
