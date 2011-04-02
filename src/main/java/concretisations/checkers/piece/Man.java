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

// TODO refactoring
// TODO pouvoir additionner les points cardinaux entre eux
public class Man extends CheckerPiece {


    public Man(SideInterface side) {
        super(side);
    }

    private BoardCardinalPosition getSideDirection() {
        return this.getSide().isFirstSide() ? BoardCardinalPosition.TOP : BoardCardinalPosition.BOTTOM;
    }

    public List<BoardCardinalPosition> getWalkOptions(final CellInterface cell) {

        // TODO ? utiliser un EnumSet
        final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();

        final BoardCardinalPosition sideDirection = this.getSideDirection();

        final BoardCardinalPosition direction1 = BoardCardinalPosition
                .valueOf(sideDirection + "_"
                        + BoardCardinalPosition.LEFT.toString());
        if (this.canWalkThrough(cell, direction1)) {
            options.add(direction1);
        }

        final BoardCardinalPosition direction2 = BoardCardinalPosition
                .valueOf(sideDirection + "_"
                        + BoardCardinalPosition.RIGHT.toString());
        if (this.canWalkThrough(cell, direction2)) {
            options.add(direction2);
        }

        return options;
    }

    public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {

        // TODO ? utiliser un EnumSet
        final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();

        final BoardCardinalPosition sideDirection = this.getSideDirection();

        final BoardCardinalPosition direction1 = BoardCardinalPosition.valueOf(sideDirection + "_"+ BoardCardinalPosition.LEFT.toString());
        if (this.canJumpOver(cell, direction1)) {
            options.add(direction1);
        }
        final BoardCardinalPosition direction2 = BoardCardinalPosition.valueOf(sideDirection + "_"+ BoardCardinalPosition.RIGHT.toString());
        if (this.canJumpOver(cell, direction2)) {
            options.add(direction2);
        }

        return options;
    }

    public boolean isPromotable(final CellInterface cell) {
        return cell.getNeighbour(this.getSideDirection()).isNull();
    }
}