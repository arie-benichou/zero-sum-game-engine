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

package concretisations.reversi.moves.types;

import java.util.Map;
import java.util.Set;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.piece.type.PieceType;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.context.board.direction.Direction;
import abstractions.immutable.context.board.direction.DirectionInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.BoardMutationInterface;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import concretisations.reversi.pieces.types.Pawn;
import concretisations.reversi.pieces.types.ReversiPieceTypeInterface;

public final class NewPawn implements ReversiMoveTypeInterface {

    public final static ReversiMoveTypeInterface from() {
        return new NewPawn(Position.NULL);
    }

    public final static ReversiMoveTypeInterface from(final PositionInterface position) {
        return new NewPawn(position);
    }

    private final PositionInterface position;

    private NewPawn(final PositionInterface position) {
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

    // TODO si le board était sidé par la piece (side=Side, type=Null), alors le paramètre board suffirait...
    private Set<PositionInterface> computeReversiblePositions(final SideInterface side, final BoardInterface board) {
        final Set<PositionInterface> reversiblePositions = Sets.newHashSet();
        final Set<PositionInterface> reversiblePositionsInOneDirection = Sets.newHashSet();
        for (final DirectionInterface direction : Direction.ALL_AROUND) {
            reversiblePositions.addAll(
                    ((ReversiPieceTypeInterface) board.cell(this.position()).value().type().value())
                            .computeConnectedPositions(side, board, this.position(), direction, reversiblePositionsInOneDirection)
                    );
            reversiblePositionsInOneDirection.clear();
        }
        return reversiblePositions;
    }

    @Override
    // TODO si le board était sidé par la piece (side=Side, type=Null), alors le paramètre board suffirait...
    public BoardMutationInterface computeMutations(final SideInterface side, final BoardInterface board) {
        final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();
        final Set<PositionInterface> reversiblePositions = this.computeReversiblePositions(side, board);
        for (final PositionInterface position : reversiblePositions)
            mutations.put(position, board.cell(position).value().apply(side));
        mutations.put(this.position(), Piece.from(side, PieceType.from(Pawn.class)));
        return BoardMutation.from(mutations);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

}