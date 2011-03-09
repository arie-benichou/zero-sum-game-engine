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

package main.java.core;

import main.java.core.interfaces.IGamePlayer;
import main.java.core.interfaces.IGamePlayerStrategy;
import main.java.core.types.GamePlayerNature;
import main.java.core.types.GamePlayersEnumeration;

public class GamePlayer implements IGamePlayer {

	private final transient String name;
	private final transient GamePlayersEnumeration ordinal;
	// TODO ? supprimmer
	private final transient GamePlayerNature type;
	private final transient IGamePlayerStrategy strategy;

	public GamePlayer(final String name, final GamePlayersEnumeration ordinal, final GamePlayerNature type, final IGamePlayerStrategy strategy) {
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