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

package fr.designpattern.zerosumgames.framework.gameplay.opponents.opponent.player;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategies.selectors.MoveSelectorInterface;


public class Player implements PlayerInterface {
	// ------------------------------------------------------------	
	private final transient String name;
	public final String getName() {
		return this.name;
	}
	// ------------------------------------------------------------
	// TODO à mettre du côté de opponent
	private final transient MoveSelectorInterface strategy;
	public final MoveSelectorInterface getStrategy() {
		return this.strategy;
	}	
	// ------------------------------------------------------------
	// TODO ? utiliser la stratégie de l'adversaire si elle est automatisée
	public Player(final String name, final MoveSelectorInterface strategy) {
		this.name = name;
		// TODO strategy = (selection, moteur d'evaluation)
		this.strategy = strategy;
	}
	// ------------------------------------------------------------	
}