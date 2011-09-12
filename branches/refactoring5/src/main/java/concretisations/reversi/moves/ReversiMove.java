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

package concretisations.reversi.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.Direction;
import abstractions.immutable.context.board.direction.DirectionInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.BoardMutationInterface;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public final class ReversiMove implements ReversiMoveTypeInterface {

    public final static ReversiMoveTypeInterface from() {
        return new ReversiMove(Position.NULL);
    }

    public final static ReversiMoveTypeInterface from(final PositionInterface position) {
        return new ReversiMove(position);
    }

    private final PositionInterface position;

    private ReversiMove(final PositionInterface position) {
        this.position = position;
    }

    @Override
    public PositionInterface position() {
        return this.position;
    }

    @Override
    public ReversiMoveTypeInterface apply() {
        return this;
    }

    @Override
    public ReversiMoveTypeInterface apply(final PositionInterface position) {
        return position == null || position.equals(this.position()) ? this.apply() : from(position);
    }

    private List<PositionInterface> computeConnectedPositions(final SideInterface side, final BoardInterface board, final PositionInterface position,
            final DirectionInterface direction, final List<PositionInterface> positions) {
        final SideInterface sideAtThisPosition = board.cell(position.apply(direction)).value().side();
        if (sideAtThisPosition.isNull()) return ImmutableList.of();
        if (sideAtThisPosition.equals(side)) return positions;
        positions.add(position.apply(direction));
        return this.computeConnectedPositions(side, board, position.apply(direction), direction, positions);
    }

    private List<PositionInterface> computeReversiblePositions(final SideInterface side, final BoardInterface board) {
        final List<PositionInterface> reversiblePositions = Lists.newArrayList();
        for (final DirectionInterface direction : Direction.ALL_AROUND)
            reversiblePositions.addAll(this.computeConnectedPositions(side, board, this.position(), direction, new ArrayList<PositionInterface>()));
        return reversiblePositions;
    }

    @Override
    public BoardMutationInterface computeBoardMutation(final SideInterface side, final BoardInterface board) {

        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();

        final List<PositionInterface> reversiblePositions = this.computeReversiblePositions(side, board);
        for (final PositionInterface position : reversiblePositions)
            mutations.put(position, board.cell(position).value().apply(side));

        mutations.put(this.position(), board.cell(reversiblePositions.get(0)).value().apply(side));

        return BoardMutation.from(mutations);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

}