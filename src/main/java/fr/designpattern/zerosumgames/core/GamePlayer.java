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

package fr.designpattern.zerosumgames.core;

import fr.designpattern.zerosumgames.core.interfaces.IGamePlayer;
import fr.designpattern.zerosumgames.core.interfaces.IGamePlayerStrategy;
import fr.designpattern.zerosumgames.core.types.GamePlayerNature;

public class GamePlayer implements IGamePlayer {

	// ------------------------------------------------------------	
	private final transient String name;
	public final String getName() {
		return this.name;
	}
	// ------------------------------------------------------------	
	private final transient IGamePlayerStrategy strategy;
	public final IGamePlayerStrategy getStrategy() {
		return this.strategy;
	}	
	// ------------------------------------------------------------		
	// TODO ? supprimmer
	private final transient GamePlayerNature type;
	public final GamePlayerNature getNature() {
		return this.type;
	}	
	// ------------------------------------------------------------	
	public GamePlayer(final String name, final GamePlayerNature type, final IGamePlayerStrategy strategy) {
		this.name = name;
		this.type = type;
		this.strategy = strategy;
	}
	// ------------------------------------------------------------	
}