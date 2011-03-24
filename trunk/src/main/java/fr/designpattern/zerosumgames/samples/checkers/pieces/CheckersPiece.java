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

import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.BoardCardinalPosition;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.CellInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.Piece;
import fr.designpattern.zerosumgames.framework.service.gameplay.game.board.dimension.cells.pieces.PieceTypeInterface;
import fr.designpattern.zerosumgames.framework.service.gameplay.opponents.OpponentsEnumeration;

public abstract class CheckersPiece extends Piece implements ICheckersPiece {

    // ------------------------------------------------------------
    public CheckersPiece(final PieceTypeInterface type,
            final OpponentsEnumeration side) {
        super(type, side);
    }

    // ------------------------------------------------------------
    // TODO keept it abstract and implements it in children classes
    protected boolean canJumpOver(final CellInterface cell,
            final BoardCardinalPosition cardinalPosition) {
        final CellInterface neighbourCell = cell.getNeighbour(cardinalPosition);
        return !(neighbourCell.isNull() || neighbourCell.isEmpty()
                || (neighbourCell.getPiece().getSide() == this.getSide()) || neighbourCell
                .getNeighbour(cardinalPosition).isNull())
                && neighbourCell.getNeighbour(cardinalPosition).isEmpty();
    }

    // ------------------------------------------------------------
    protected boolean canWalkThrough(final CellInterface cell,
            final BoardCardinalPosition cardinalPosition) {
        return !cell.getNeighbour(cardinalPosition).isNull()
                && cell.getNeighbour(cardinalPosition).isEmpty();
    }

    // ------------------------------------------------------------
    //public abstract boolean isPromotable(final IGameBoardCell cell);
    // ------------------------------------------------------------
    @Override
    public String toString() {
        // TODO ? utiliser une map dans la factory
        return this.getSide().equals(OpponentsEnumeration.FIRST_PLAYER) ? "x"
                : "o";
    }
    // -----------------------------------------------------------
}