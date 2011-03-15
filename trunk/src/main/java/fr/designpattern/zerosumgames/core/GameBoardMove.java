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

package fr.designpattern.zerosumgames.core;

import fr.designpattern.zerosumgames.core.interfaces.IGameBoardMove;
import fr.designpattern.zerosumgames.core.interfaces.IGameBoardPosition;
import fr.designpattern.zerosumgames.core.types.GamePlayersEnumeration;

public class GameBoardMove implements IGameBoardMove {
		
	private GamePlayersEnumeration side;
	private final void setSide(final GamePlayersEnumeration side) {
		this.side = side;
	}
	public final GamePlayersEnumeration getSide() {
		return this.side;
	}
	
	private transient IGameBoardPosition position;
	private final void setNewPosition(final IGameBoardPosition newPosition) {
		this.position = newPosition;
	}
	public final IGameBoardPosition getPosition() {
		return this.position;
	}	
	
	private Double evaluation;
	public final void setEvaluation(final Double evaluation) {
		this.evaluation = evaluation;
	}	
	public final Double getEvaluation() {
		return this.evaluation;
	}
	
	private int depth;
	public final void setDepth(final int depth) {
		this.depth = depth;
	}
	
	public final int getDepth() {
		return this.depth;
	}	
	
	public boolean isNull() {
		return this.getPosition().isNull();
	}

	public GameBoardMove(final GamePlayersEnumeration side, final IGameBoardPosition position) {
		this.setSide(side);
		this.setNewPosition(position);
	}
	
	@Override
	public String toString() {
		return this.getSide() + " " + this.getPosition() + " " + (this.getEvaluation() != null ? this.getEvaluation() : "");// + " @depth = " + this.getDepth();
	}
	
	@Override
	public int hashCode() {
		return this.getEvaluation().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO tester la classe
		IGameBoardMove o = (IGameBoardMove)obj;
		return this.getEvaluation().equals(o.getEvaluation());
	}
	
	public int compareTo(IGameBoardMove o) {
		return o.getEvaluation().compareTo(this.getEvaluation());
		//return this.getEvaluation().compareTo(o.getEvaluation());
	}


}