/*
 * @(#)IGamePlayer.java	0.99
 * 
 * Copyright 2011 Arie Benichou 
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

package fr.designpattern.zerosumgames.framework.game.components.opponents.player;

import fr.designpattern.zerosumgames.framework.game.components.opponents.strategy.IGameStrategy;

/**
 * This is the interface for a game player.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public interface IGamePlayer {

	/**
	 * Returns the player's name.
	 * 
	 * @return the player's name
	 */
	String getName();

	/**
	 * Returns the player's nature.
	 * 
	 * @return the player's nature
	 */
	GamePlayerNature getNature();

	/**
	 * Returns the player strategy.
	 * 
	 * @return the player strategy
	 */
	IGameStrategy getStrategy();

}