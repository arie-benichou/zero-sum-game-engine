/*
 * Copyright (C) 2011 Arié Bénichou
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.    
 */

package core;

import core.interfaces.IGamePlayer;
import core.interfaces.IGamePlayerStrategy;
import core.types.GamePlayerNature;
import core.types.GamePlayersEnumeration;

public class GamePlayer implements IGamePlayer {

	private String name;
	private GamePlayersEnumeration ordinal;
	// TODO ? supprimmer
	private GamePlayerNature type;
	private IGamePlayerStrategy strategy;

	public GamePlayer(String name, GamePlayersEnumeration ordinal,
			GamePlayerNature type, IGamePlayerStrategy strategy) {
		this.name = name;
		this.ordinal = ordinal;
		this.type = type;
		this.strategy = strategy;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public GamePlayersEnumeration getOrder() {
		return this.ordinal;
	}

	@Override
	public GamePlayerNature getNature() {
		return this.type;
	}

	@Override
	public IGamePlayerStrategy getStrategy() {
		return this.strategy;
	}

}