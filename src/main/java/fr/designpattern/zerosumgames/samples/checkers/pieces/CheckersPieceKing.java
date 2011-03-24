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

package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public class CheckersPieceKing extends CheckersPiece {
	// ------------------------------------------------------------
	private static final BoardCardinalPosition[] POSITIONS =
	{
		BoardCardinalPosition.TOP_RIGHT,
		BoardCardinalPosition.BOTTOM_RIGHT,
		BoardCardinalPosition.TOP_LEFT,
		BoardCardinalPosition.BOTTOM_LEFT
	};
	// ------------------------------------------------------------
	public CheckersPieceKing(final PieceTypeInterface type, final OpponentsEnumeration side) {
		super(type, side);
	}
	// ------------------------------------------------------------
	public List<BoardCardinalPosition> getWalkOptions(final CellInterface cell) {
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		for (final BoardCardinalPosition direction : CheckersPieceKing.POSITIONS) {
			if (this.canWalkThrough(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------
	public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {
		// TODO ? utiliser un EnumSet
		final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
		for (final BoardCardinalPosition direction : CheckersPieceKing.POSITIONS) {
			if (this.canJumpOver(cell, direction)) {
				options.add(direction);
			}
		}
		return options;
	}
	// ------------------------------------------------------------
	@Override
	public String toString() {
		return super.toString().toUpperCase();
	}
	// ------------------------------------------------------------
	public boolean isPromotable(final CellInterface cell) {
		return false;
	}
}