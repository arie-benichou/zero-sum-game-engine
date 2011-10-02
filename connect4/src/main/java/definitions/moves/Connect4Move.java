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

package definitions.moves;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

import context.ContextInterface;
import context.entity.game.board.BoardInterface;
import context.entity.game.board.cell.piece.Piece;
import context.entity.game.board.cell.piece.PieceInterface;
import context.entity.game.board.cell.piece.side.SideInterface;
import context.entity.game.board.cell.piece.type.PieceType;
import context.entity.game.board.cell.position.PositionInterface;
import context.entity.game.board.direction.DirectionInterface;
import context.entity.game.board.mutation.BoardMutation;
import context.entity.game.board.mutation.BoardMutationInterface;
import context.event.MoveInterface;
import definitions.pieces.Connect4Pawn;

public final class Connect4Move implements MoveInterface {

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public ContextInterface context() {
        return this.context;
    }

    private final ContextInterface context;

    /*-------------------------------------8<-------------------------------------*/

    private final PositionInterface position;

    @Override
    public PositionInterface position() {
        return this.position;
    }

    /*-------------------------------------8<-------------------------------------*/

    private BoardMutationInterface mutations = null;

    @Override
    public BoardMutationInterface boardMutation() {
        if (this.mutations == null) {
            final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();
            mutations.put(this.position(), Piece.from(this.context.side(), PieceType.from(Connect4Pawn.class)));
            this.mutations = BoardMutation.from(mutations);
        }
        return this.mutations;
    }

    /*-------------------------------------8<-------------------------------------*/

    public final static Connect4Move from(final ContextInterface context, final PositionInterface position) {
        return new Connect4Move(context, position);
    }

    private Connect4Move(final ContextInterface context, final PositionInterface position) {
        this.context = context;
        this.position = position;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public Connect4Move apply() {
        return this;
    }

    @Override
    public Connect4Move apply(final PositionInterface position) { // TODO apply context
        return position == null || position.equals(this.position()) ? this.apply() : from(this.context(), position);
    }

    /*-------------------------------------8<-------------------------------------*/

    private List<PositionInterface> computeConnectedPositions(final SideInterface side, final BoardInterface board, final PositionInterface position,
            final DirectionInterface direction, final List<PositionInterface> positions) {
        final SideInterface sideAtThisPosition = board.cell(position.apply(direction)).value().side();
        if (sideAtThisPosition.isNull()) return ImmutableList.of();
        if (sideAtThisPosition.equals(side)) return positions;
        positions.add(position.apply(direction));
        return this.computeConnectedPositions(side, board, position.apply(direction), direction, positions);
    }

    @Override
    public int hashCode() {
        return this.position().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof Connect4Move)) return false;
        final Connect4Move that = (Connect4Move) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.position().equals(this.position());
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public int compareTo(final MoveInterface that) {
        /*
        // !! NPE si appel chainé juste après le constructeur !!
        final BoardMutationInterface bm1 = this.boardMutation();
        final BoardMutationInterface bm2 = that.boardMutation();
        return bm2.value().size() - bm1.value().size();
        */
        return 0;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public boolean isNull() {
        return false;
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

    /*-------------------------------------8<-------------------------------------*/

    @Override
    public MoveInterface value() { // !! TODO ConcreteMoveInterface
        return this;
    }

    @Override
    public MoveInterface apply(final MoveInterface value) { // !! TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public MoveInterface apply(final Class<? extends MoveInterface> valueClass) { // !! TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    /*-------------------------------------8<-------------------------------------*/

}