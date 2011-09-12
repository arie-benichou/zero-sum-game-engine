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

package concretisations.checkers.pieces;

import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;

import com.google.common.collect.ImmutableSet;

// TODO promotion d'un MAN en KING
public final class Man extends CheckerPiece {

    public Man(final SideInterface side, final OldPieceTypeInterface type) {
        super(side, type, ImmutableSet.of(side.isFirst() ? NamedDirection.TOP.value() : NamedDirection.BOTTOM.value()));
    }

    @Override
    public String toString() {
        String consoleView = "";
        if (this.side().isFirst()) {
            consoleView = "x";
        }
        else if (this.side().isSecondSide()) {
            consoleView = "o";
        }
        return consoleView;
    }

}