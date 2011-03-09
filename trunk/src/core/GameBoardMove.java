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

import core.interfaces.IGameBoardMove;
import core.interfaces.IGameBoardPosition;
import core.types.GamePlayersEnumeration;

public class GameBoardMove implements IGameBoardMove {
		
	private GamePlayersEnumeration side;
	private final void setSide(final GamePlayersEnumeration side) {
		this.side = side;
	}
	@Override	
	public final GamePlayersEnumeration getSide() {
		return this.side;
	}
	
	private IGameBoardPosition position;
	private final void setNewPosition(final IGameBoardPosition newPosition) {
		this.position = newPosition;
	}
	@Override
	public final IGameBoardPosition getPosition() {
		return this.position;
	}	
	
	@Override
	public boolean isNull() {
		return this.getPosition().isNull();
	}

	public GameBoardMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		this.setSide(side);
		this.setNewPosition(position);
	}
	
	@Override
	public String toString() {
		return this.getSide() + " " + this.getPosition();
	}

}