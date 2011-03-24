/*
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

package fr.designpattern.zerosumgames.framework.service;

import fr.designpattern.zerosumgames.framework.service.gameplay.GamePlayInterface;

/**
 * This is the interface for a game play service.
 */
public interface GamePlayServiceInterface {
	
	/**
	 * Returns the gameplay.
	 *  
	 * @return the gameplay
	 */
	GamePlayInterface getGamePlay();

	/**
	 * Starts a new game play.
	 */
	void start();
	
	/**
	 * Pauses this game play.
	 */
	void pause();

	/**
	 * Resumes from pause.
	 */
	void resume();

	/**
	 * Stops this game play.
	 */
	void stop();

	/**
	 * Stops this game play and starts a new one.
	 */
	void reset();

}