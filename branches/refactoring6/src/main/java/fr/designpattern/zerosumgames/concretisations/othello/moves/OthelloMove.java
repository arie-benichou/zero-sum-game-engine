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

package fr.designpattern.zerosumgames.concretisations.othello.moves;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fr.designpattern.zerosumgames.abstractions.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.PieceInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.context.game.board.direction.Direction;
import fr.designpattern.zerosumgames.abstractions.context.game.board.direction.DirectionInterface;
import fr.designpattern.zerosumgames.abstractions.move.MoveInterface;
import fr.designpattern.zerosumgames.abstractions.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.move.mutation.BoardMutationInterface;

public final class OthelloMove implements OthelloMoveInterface {

    private BoardMutationInterface mutations = null;

    public final static OthelloMoveInterface from() {
        return new OthelloMove(null, Position.NULL); // TODO ?! Context.Null
    }

    public final static OthelloMoveInterface from(final ContextInterface context, final PositionInterface position) {
        return new OthelloMove(context, position);
    }

    private final ContextInterface context;
    private final PositionInterface position;

    private OthelloMove(final ContextInterface context, final PositionInterface position) {
        this.context = context;
        this.position = position;
    }

    @Override
    public PositionInterface position() {
        return this.position;
    }

    @Override
    public ContextInterface context() {
        return this.context;
    }

    @Override
    public OthelloMoveInterface apply() {
        return this;
    }

    @Override
    public OthelloMoveInterface apply(final PositionInterface position) { // TODO apply context
        return position == null || position.equals(this.position()) ? this.apply() : from(this.context(), position);
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
    public BoardMutationInterface boardMutation(/*final SideInterface side, final BoardInterface board*/) {
        if (this.mutations == null) {
            final Map<PositionInterface, PieceInterface> mutations = Maps.newHashMap();
            final List<PositionInterface> reversiblePositions = this.computeReversiblePositions(this.context.side(), this.context.game().board()); // TODO ? passer le contexte
            for (final PositionInterface position : reversiblePositions)
                mutations.put(position, this.context.game().board().cell(position).value().apply(this.context.side()));
            mutations.put(this.position(), this.context.game().board().cell(reversiblePositions.get(0)).value().apply(this.context.side()));
            this.mutations = BoardMutation.from(mutations);
        }
        return this.mutations;
    }

    @Override
    public int hashCode() {
        return this.position().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof OthelloMove)) return false;
        final OthelloMove that = (OthelloMove) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.position().equals(this.position());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

    @Override
    public int compareTo(final MoveInterface that) {
        // !! NPE si appel chainé juste après le constructeur !!
        final BoardMutationInterface bm1 = this.boardMutation();
        final BoardMutationInterface bm2 = that.boardMutation();
        return bm2.value().size() - bm1.value().size();
    }

    @Override
    public boolean isNull() {
        return this.position().isNull();
    }

    @Override
    public MoveInterface value() {
        return this;
    }

    @Override
    public MoveInterface apply(final MoveInterface value) { // ?? TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public MoveInterface apply(final Class<? extends MoveInterface> valueClass) { // ?? TODO AbstractMoveInterface + ConcreteMoveInterface
        throw new RuntimeException("Not implemented yet");
    }

}