/*
 * Copyright 2011 Arie Benichou
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package concretisations.checkers.piece;

import java.util.ArrayList;
import java.util.List;

import abstractions.cell.API.CellInterface;
import abstractions.side.API.SideInterface;

public class King extends CheckerPiece {

    private static final BoardCardinalPosition[] POSITIONS = {
        BoardCardinalPosition.TOP_RIGHT,
        BoardCardinalPosition.BOTTOM_RIGHT,
        BoardCardinalPosition.TOP_LEFT,
        BoardCardinalPosition.BOTTOM_LEFT
    };

    public King(SideInterface side) {
        super(side);
    }

    public List<BoardCardinalPosition> getWalkOptions(final CellInterface cell) {
        // TODO ? utiliser un EnumSet
        final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
        for (final BoardCardinalPosition direction : King.POSITIONS) {
            if (this.canWalkThrough(cell, direction)) {
                options.add(direction);
            }
        }
        return options;
    }

    public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {
        // TODO ? utiliser un EnumSet
        final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();
        for (final BoardCardinalPosition direction : King.POSITIONS) {
            if (this.canJumpOver(cell, direction)) {
                options.add(direction);
            }
        }
        return options;
    }

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }

    public boolean isPromotable(final CellInterface cell) {
        return false;
    }
}