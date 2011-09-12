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

package concretisations.reversi.pieces.types;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.BoardCell;
import abstractions.immutable.context.board.cell.BoardCellInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.Direction;
import abstractions.immutable.context.board.direction.DirectionInterface;

// TODO !! revoir le type().type()
// TODO !! tester sans classe abstraite
public class Abstract implements ReversiPieceTypeInterface {

    @Override
    public final boolean isConnected(final SideInterface side, final BoardInterface board, final PositionInterface position, final DirectionInterface direction) {
        if (board.cell(position).value().side().equals(side))
            return true;
        if (board.cell(position).value().side().equals(side.opposite())) {
            return ((ReversiPieceTypeInterface) board.cell(position.apply(direction)).value().type().value()).isConnected(side, board,
                    position.apply(direction),
                    direction);
        }
        return false;
    }

    @Override
    public final boolean isMutable(final SideInterface side, final BoardInterface board, final PositionInterface position) {
        for (final DirectionInterface direction : Direction.ALL_AROUND) {
            final BoardCellInterface nextCell = board.cell(position.apply(direction));
            if (!nextCell.equals(BoardCell.NULL)
                    && side.opposite().equals(nextCell.value().side())
                    && ((ReversiPieceTypeInterface) board.cell(nextCell.position().apply(direction)).value().type().value())
                            .isConnected(side, board, nextCell.position().apply(direction), direction))
                return true;
        }
        return false;

    }

    @Override
    public ReversiPieceTypeInterface apply() {
        return this;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

}