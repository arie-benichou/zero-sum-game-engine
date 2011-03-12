/*
 * @(#)GameBoardCellNeighbour.java	0.99
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

package fr.designpattern.zerosumgames.core.types;

/**
 * This is the enumeration of cell's neighbours in a board game.
 * 
 * @author Arie Benichou
 * @version 0.99, 01/03/2011
 */
public enum GameBoardCardinalPosition {

	TOP(-1, 0),
	RIGHT(0, 1),
	BOTTOM(1, 0),
	LEFT(0, -1),

	//CENTER(0, 0),
	
	TOP_RIGHT(-1, 1),
	TOP_LEFT(-1, -1),
	BOTTOM_RIGHT(1, 1),
	BOTTOM_LEFT(1, -1);

	private final int deltaRowIndex;
	private final int deltaColumnIndex;

	private GameBoardCardinalPosition(final int deltaRowIndex, final int deltaColumnIndex) {
		this.deltaRowIndex = deltaRowIndex;
		this.deltaColumnIndex = deltaColumnIndex;
	}

	/**
	 * Return the row index delta for a cell's neighbour.
	 * 
	 * @return the row index delta for a cell's neighbour
	 */
	public final int getDeltaRowIndex() {
		return this.deltaRowIndex;
	}

	/**
	 * Return the column index delta for a cell's neighbour.
	 * 
	 * @return the column index delta for a cell's neighbour
	 */
	public final int getDeltaColumnIndex() {
		return this.deltaColumnIndex;
	}

}