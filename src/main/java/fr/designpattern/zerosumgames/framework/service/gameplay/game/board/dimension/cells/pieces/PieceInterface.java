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

package fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces;

import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

/**
 * This is the interface for a game piece.
 */
public interface PieceInterface {

	/**
	 * Returns the side related to this piece.
	 * 
	 * @return the side related to this piece
	 */
	OpponentsEnumeration getSide();

	/**
	 * Returns the type of this piece.
	 * 
	 * @return the type of this piece
	 */
	PieceTypeInterface getType();

}