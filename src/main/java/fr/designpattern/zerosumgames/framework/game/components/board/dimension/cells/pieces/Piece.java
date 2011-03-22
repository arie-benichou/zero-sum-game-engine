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

package fr.designpattern.zerosumgames.framework.game.components.board.dimension.cells.pieces;

import fr.designpattern.zerosumgames.framework.game.components.opponents.OpponentsEnumeration;

public abstract class Piece implements PieceInterface {

	private final transient OpponentsEnumeration side;

	public OpponentsEnumeration getSide() {
		return side;
	}

	private final transient PieceTypeInterface type;

	public PieceTypeInterface getType() {
		return type;
	}

	public Piece(final PieceTypeInterface type, final OpponentsEnumeration side) {
		this.type = type;
		this.side = side;
	}

	@Override
	public String toString() {
		return "[type=" + type + ", " + "side=" + side + "]";
	}

}