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

package fr.designpattern.zerosumgames.samples.checkers.pieces;

import java.util.ArrayList;
import java.util.List;

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

// TODO refactoring
// TODO pouvoir additionner les points cardinaux entre eux
public class CheckersPieceMan extends CheckersPiece {

    // ------------------------------------------------------------
    public CheckersPieceMan(final PieceTypeInterface type,
            final OpponentsEnumeration side) {
        super(type, side);
    }

    // ------------------------------------------------------------
    private BoardCardinalPosition getSideDirection() {
        return this.getSide() == OpponentsEnumeration.FIRST_PLAYER ? BoardCardinalPosition.TOP
                : BoardCardinalPosition.BOTTOM;
    }

    // ------------------------------------------------------------
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

    // ------------------------------------------------------------
    public List<BoardCardinalPosition> getJumpOptions(final CellInterface cell) {

        // TODO ? utiliser un EnumSet
        final List<BoardCardinalPosition> options = new ArrayList<BoardCardinalPosition>();

        final BoardCardinalPosition sideDirection = this.getSideDirection();

        final BoardCardinalPosition direction1 = BoardCardinalPosition
                .valueOf(sideDirection + "_"
                        + BoardCardinalPosition.LEFT.toString());
        if (this.canJumpOver(cell, direction1)) {
            options.add(direction1);
        }

        final BoardCardinalPosition direction2 = BoardCardinalPosition
                .valueOf(sideDirection + "_"
                        + BoardCardinalPosition.RIGHT.toString());
        if (this.canJumpOver(cell, direction2)) {
            options.add(direction2);
        }

        return options;
    }

    // ------------------------------------------------------------
    public boolean isPromotable(final CellInterface cell) {
        return cell.getNeighbour(this.getSideDirection()).isNull();
    }
    // ------------------------------------------------------------
}