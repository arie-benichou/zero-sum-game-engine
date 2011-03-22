/*
 * @(#)GameBoardPlane.java	0.99
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

package fr.designpattern.zerosumgames.game.components.board;

/**
 * This is the enumeration of board planes. 
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public enum GameBoardPlane {
	
	RIGHT_AND_LEFT(GameBoardCardinalPosition.RIGHT, GameBoardCardinalPosition.LEFT),
	
	BOTTOM_AND_TOP(GameBoardCardinalPosition.BOTTOM, GameBoardCardinalPosition.TOP),
	
	BOTTOM_LEFT_AND_TOP_RIGHT(GameBoardCardinalPosition.BOTTOM_LEFT, GameBoardCardinalPosition.TOP_RIGHT),
	
	TOP_LEFT_AND_BOTTOM_RIGHT(GameBoardCardinalPosition.TOP_LEFT, GameBoardCardinalPosition.BOTTOM_RIGHT);

	private GameBoardCardinalPosition oneWay;
	private GameBoardCardinalPosition oppositeWay;

	private GameBoardPlane(final GameBoardCardinalPosition oneWay, final GameBoardCardinalPosition oppositeWay) {
		this.oneWay = oneWay;
		this.oppositeWay = oppositeWay;
	}
	
	/**
	 * Returns one way.
	 * 
	 * @return one way
	 */
	public final GameBoardCardinalPosition getOneWay() {
		return this.oneWay; 
	}
	
	/**
	 * Returns the opposite way.
	 *  
	 * @return the opposite way
	 */
	public final GameBoardCardinalPosition getOppositeWay() {
		return this.oppositeWay; 
	}	
	
}