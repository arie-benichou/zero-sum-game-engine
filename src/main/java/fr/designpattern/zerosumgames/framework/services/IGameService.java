/*
 * @(#)IGame.java	0.99
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
package fr.designpattern.zerosumgames.framework.services;

public interface IGameService {

	/**
	 * Starts a new game play.
	 */
	void start();
	//void start(IGameBoard board);
	
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