
package abstractions.immutable.move.type.mocks;

import java.util.HashMap;

import abstractions.immutable.context.board.BoardInterface;
import abstractions.immutable.context.board.cell.piece.Piece;
import abstractions.immutable.context.board.cell.piece.PieceInterface;
import abstractions.immutable.context.board.cell.piece.side.SideInterface;
import abstractions.immutable.context.board.cell.position.Position;
import abstractions.immutable.context.board.cell.position.PositionInterface;
import abstractions.immutable.move.ConcreteMoveTypeInterface;
import abstractions.immutable.move.mutation.BoardMutation;
import abstractions.immutable.move.mutation.BoardMutationInterface;

import com.google.common.collect.Maps;

// TODO utiliser une factory et NULL contient le coup null
public final class SomeConcreteMoveType implements ConcreteMoveTypeInterface {

    public static ConcreteMoveTypeInterface from() {
        return new SomeConcreteMoveType(Position.NULL);
    }

    public static ConcreteMoveTypeInterface from(final PositionInterface position) {
        return new SomeConcreteMoveType(position == null ? Position.NULL : position);
    }

    private final PositionInterface position;

    private SomeConcreteMoveType(final PositionInterface position) {
        this.position = position;
    }

    @Override
    public ConcreteMoveTypeInterface apply() {
        return this;
    }

    @Override
    public PositionInterface position() {
        return this.position;
    }

    @Override
    public ConcreteMoveTypeInterface apply(final PositionInterface position) {
        return from(position);
    }

    @Override
    public BoardMutationInterface computeBoardMutation(final SideInterface side, final BoardInterface board) {
        final HashMap<PositionInterface, PieceInterface> maps = Maps.newHashMap();
        maps.put(this.position(), Piece.NULL);
        return BoardMutation.from(maps);
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof SomeConcreteMoveType)) return false;
        final SomeConcreteMoveType that = (SomeConcreteMoveType) object;
        if (that.hashCode() != this.hashCode()) return false;
        return that.position().equals(this.position());
    }

}
