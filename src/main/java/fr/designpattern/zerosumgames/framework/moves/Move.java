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

package fr.designpattern.zerosumgames.framework.moves;

import fr.designpattern.zerosumgames.framework.game.board.dimension.cells.positions.PositionInterface;
import fr.designpattern.zerosumgames.framework.opponents.OpponentsEnumeration;

public class Move implements MoveInterface {
		
	private OpponentsEnumeration side;
	private final void setSide(final OpponentsEnumeration side) {
		this.side = side;
	}
	public final OpponentsEnumeration getSide() {
		return this.side;
	}
	
	private transient PositionInterface position;
	private final void setNewPosition(final PositionInterface newPosition) {
		this.position = newPosition;
	}
	public final PositionInterface getPosition() {
		return this.position;
	}	
	
	private Double evaluation = Double.NEGATIVE_INFINITY;
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

	public Move(final OpponentsEnumeration side, final PositionInterface position) {
		this.setSide(side);
		this.setNewPosition(position);
	}
	
	@Override
	public String toString() {
		return this.getSide() + " " + this.getPosition();
	}
	
	public String debug() {
		return this.getSide() + " " + this.getPosition() + " " + this.getEvaluation() + " @depth = " + this.getDepth();
	}
	
	@Override
	public int hashCode() {
		return this.getEvaluation().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO tester la classe
		MoveInterface o = (MoveInterface)obj;
		return this.getEvaluation().equals(o.getEvaluation());
	}
	
	public int compareTo(MoveInterface o) {
		//return o.getEvaluation().compareTo(this.getEvaluation());
		return this.getEvaluation().compareTo(o.getEvaluation());
	}
	
	/*
	@Override @Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	*/

}