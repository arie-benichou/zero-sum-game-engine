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

import java.util.Set;

import abstractions.direction.DirectionInterface;
import abstractions.direction.DirectionManager.NamedDirection;
import abstractions.immutable.context.gameplay.game.board.cell.piece.OldPieceTypeInterface;
import abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;

import com.google.common.collect.ImmutableSet;

public final class King extends CheckerPiece {

    private static final Set<DirectionInterface> LEGAL_DIRECTIONS = ImmutableSet.of(NamedDirection.TOP.value(), NamedDirection.BOTTOM.value());

    public King(final SideInterface side, final OldPieceTypeInterface type) {
        super(side, type, King.LEGAL_DIRECTIONS);
    }

    @Override
    public String toString() {
        String consoleView = "";
        if (this.side().isFirst()) {
            consoleView = "X";
        }
        else if (this.side().isSecondSide()) {
            consoleView = "O";
        }
        return consoleView;
    }

}