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

package fr.designpattern.zerosumgames.framework.game.components.opponents.players;

import fr.designpattern.zerosumgames.framework.game.components.opponents.players.strategies.StrategyInterface;



public class Player implements PlayerInterface {

	// ------------------------------------------------------------	
	private final transient String name;
	public final String getName() {
		return this.name;
	}
	// ------------------------------------------------------------	
	private final transient StrategyInterface strategy;
	public final StrategyInterface getStrategy() {
		return this.strategy;
	}	
	// ------------------------------------------------------------		
	// TODO ? supprimmer
	private final transient PlayerNature type;
	public final PlayerNature getNature() {
		return this.type;
	}	
	// ------------------------------------------------------------	
	public Player(final String name, final PlayerNature type, final StrategyInterface strategy) {
		this.name = name;
		this.type = type;
		this.strategy = strategy;
	}
	// ------------------------------------------------------------	
}