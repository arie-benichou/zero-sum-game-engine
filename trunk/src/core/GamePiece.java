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

package core;

import core.interfaces.IGamePiece;
import core.interfaces.IGamePieceType;
import core.types.GamePlayersEnumeration;

public abstract class GamePiece implements IGamePiece {

	private GamePlayersEnumeration side;

	public GamePlayersEnumeration getSide() {
		return side;
	}

	private IGamePieceType type;

	public IGamePieceType getType() {
		return type;
	}

	public GamePiece(IGamePieceType type, GamePlayersEnumeration side) {
		this.type = type;
		this.side = side;
	}

	@Override
	public String toString() {
		return "[type=" + type + ", " + "side=" + side + "]";
	}

}