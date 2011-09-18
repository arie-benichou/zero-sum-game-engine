
package fr.designpattern.zerosumgames.concretisations.reversi.context.moves;

import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.BoardInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.piece.side.SideInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.gameplay.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

public final class ReversiNullMove implements ReversiMoveTypeInterface {

    private final static ReversiMoveTypeInterface INSTANCE = new ReversiNullMove();

    public final static ReversiMoveTypeInterface from() {
        return INSTANCE;
    }

    private ReversiNullMove() {}

    @Override
    public ReversiMoveTypeInterface apply() {
        return this;
    }

    @Override
    public PositionInterface position() {
        return Position.NULL;
    }

    @Override
    public ReversiMoveTypeInterface apply(final PositionInterface position) {
        return this;
    }

    @Override
    public BoardMutationInterface computeBoardMutation(final SideInterface side, final BoardInterface board) {
        return BoardMutation.NULL;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.position() + ")";
    }

    @Override
    public int hashCode() {
        return this.position().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        if (object == this) return true;
        if (object == null) return false;
        if (!(object instanceof ReversiNullMove)) return false;
        return true;
    }

}
