
package fr.designpattern.zerosumgames.concretisations.reversi.context.moves;

import fr.designpattern.zerosumgames.abstractions.immutable.context.ContextInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.Position;
import fr.designpattern.zerosumgames.abstractions.immutable.context.game.board.cell.position.PositionInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.ConcreteMoveTypeInterface;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutation;
import fr.designpattern.zerosumgames.abstractions.immutable.move.mutation.BoardMutationInterface;

public final class ReversiNullMove implements ReversiMoveTypeInterface {

    private final ContextInterface context;

    public final static ReversiMoveTypeInterface from(final ContextInterface context) {
        return new ReversiNullMove(context);
    }

    private ReversiNullMove(final ContextInterface context) {
        this.context = context;
    }

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
    public BoardMutationInterface boardMutation(/*final SideInterface side, final BoardInterface board*/) {
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

    @Override
    public int compareTo(final ConcreteMoveTypeInterface that) {
        return 1;
    }

    @Override
    public ContextInterface context() {
        return this.context;
    }

    @Override
    public boolean isNull() {
        return true;
    }

}
