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

import abstractions.cell.API.CellInterface;
import abstractions.piece.AbstractPiece;
import abstractions.side.API.SideInterface;

public abstract class CheckerPiece extends AbstractPiece implements PieceInterface {

    public CheckerPiece(SideInterface side) {
        super(side);
    }

    protected boolean canJumpOver(final CellInterface cell, final BoardCardinalPosition cardinalPosition) {
        final CellInterface neighbourCell = cell.getNeighbour(cardinalPosition);
        return !(neighbourCell.isNull() || neighbourCell.isEmpty() || (neighbourCell.getPiece().getSide() == this.getSide()) || neighbourCell.getNeighbour(
                cardinalPosition).isNull())
                && neighbourCell.getNeighbour(cardinalPosition).isEmpty();
    }

    protected boolean canWalkThrough(final CellInterface cell, final BoardCardinalPosition cardinalPosition) {
        return !cell.getNeighbour(cardinalPosition).isNull() && cell.getNeighbour(cardinalPosition).isEmpty();
    }

}